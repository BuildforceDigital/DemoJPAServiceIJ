package tutorial.persistence

import java.lang.reflect.{Constructor, InvocationTargetException}
import java.{util => ju}

import jakarta.persistence.EntityManager
import nl.buildforce.olingo.commons.api.http.{HttpMethod, HttpStatusCode}
import nl.buildforce.sequoia.metadata.core.edm.mapper.api.{JPAAssociationPath, JPAEntityType, JPAStructuredType}
import nl.buildforce.sequoia.metadata.core.edm.mapper.exception.ODataJPAModelException
import nl.buildforce.sequoia.processor.core.api.JPAAbstractCUDRequestHandler
import nl.buildforce.sequoia.processor.core.api.example.JPAExampleModifyException
import nl.buildforce.sequoia.processor.core.api.example.JPAExampleModifyException.MessageKeys.{ENTITY_ALREADY_EXISTS, ENTITY_NOT_FOUND}
import nl.buildforce.sequoia.processor.core.exception.{ODataJPAInvocationTargetException, ODataJPAProcessException, ODataJPAProcessorException}
import nl.buildforce.sequoia.processor.core.modify.JPAUpdateResult
import nl.buildforce.sequoia.processor.core.processor.{JPAModifyUtil, JPARequestEntity, JPARequestLink}

import scala.collection.mutable
import scala.jdk.CollectionConverters.{ListHasAsScala, SetHasAsScala, MapHasAsScala}

class ExampleCUDRequestHandler() extends JPAAbstractCUDRequestHandler {
  final private val entityBuffer = mutable.Map[Any, JPARequestEntity]()

  @throws[ODataJPAProcessException]
  override def createEntity(requestEntity: JPARequestEntity, em: EntityManager): Any = {
    @throws[ODataJPAProcessorException]
    @throws[ODataJPAInvocationTargetException]
    def findEntity(requestEntity: JPARequestEntity, em: EntityManager) = {
      val key = requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, requestEntity.getKeys, requestEntity.getEntityType)
      em.getReference(requestEntity.getEntityType.getTypeClass, key)
    }

    @throws[ODataJPAProcessException]
    def processRelatedEntities(relatedEntities: ju.Map[JPAAssociationPath, ju.List[JPARequestEntity]],
                               parentInstance: Any, modifyUtil: JPAModifyUtil, em: EntityManager): Unit = {
      for { entity: ju.Map.Entry[JPAAssociationPath, ju.List[JPARequestEntity]] <- relatedEntities.entrySet.asScala
        pathInfo: JPAAssociationPath = entity.getKey
        requestEntity: JPARequestEntity <- entity.getValue.asScala
      } {
        val newInstance = createOneEntity(requestEntity, parentInstance)

        modifyUtil.linkEntities(parentInstance, newInstance, pathInfo)
        if (Option(pathInfo.getPartner).isDefined) try modifyUtil.linkEntities(newInstance, parentInstance, pathInfo.getPartner.getPath)
        catch {
          case e: ODataJPAModelException =>
            throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
        }
        processRelatedEntities(requestEntity.getRelatedEntities, newInstance, modifyUtil, em)
      }
    }

    val instance = if (requestEntity.getKeys.isEmpty) { // POST an Entity
      val instance0 = createOneEntity(requestEntity, /*em,*/ null)
      if (Option(em.find(requestEntity.getEntityType.getTypeClass,
        requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, instance0))).isDefined)
        throw new JPAExampleModifyException(ENTITY_ALREADY_EXISTS, HttpStatusCode.BAD_REQUEST)
      instance0
    }
    else // POST on Link only // https://issues.oasis-open.org/browse/ODATA-1294
      findEntity(requestEntity, em)

    processRelatedEntities(requestEntity.getRelatedEntities, instance, requestEntity.getModifyUtil, em)
    em.persist(instance)
    instance
  }

  @throws[ODataJPAProcessException]
  override def deleteEntity(requestEntity: JPARequestEntity, em: EntityManager): Unit = {
    val instance = em.find(requestEntity.getEntityType.getTypeClass,
      requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType,
        requestEntity.getKeys, requestEntity.getEntityType))
    if (Option(instance).isDefined) em.remove(instance)
  }

  @throws[ODataJPAProcessException]
  override def updateEntity(requestEntity: JPARequestEntity, em: EntityManager, method: HttpMethod): JPAUpdateResult = {

    @throws[ODataJPAProcessorException]
    @throws[ODataJPAInvocationTargetException]
    def updateLinks(requestEntity: JPARequestEntity, em: EntityManager, instance: Any): Unit = {
      if (Option(requestEntity.getRelationLinks).isDefined)
        for {links <- requestEntity.getRelationLinks.entrySet.asScala
             link <- links.getValue.asScala} {
          requestEntity.getModifyUtil.linkEntities(instance,
            em.find(link.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(link.getEntityType, link.getRelatedKeys, link.getEntityType)),
            links.getKey)
        }
    }

    if ((method eq HttpMethod.PATCH) || (method eq HttpMethod.DELETE)) {
      val instance = em.find(requestEntity.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, requestEntity.getKeys, requestEntity.getEntityType))
      if (Option(instance).isEmpty) throw new JPAExampleModifyException(ENTITY_NOT_FOUND, HttpStatusCode.NOT_FOUND)
      requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, requestEntity.getEntityType)
      updateLinks(requestEntity, em, instance)
      return new JPAUpdateResult(false, instance)
    }
    super.updateEntity(requestEntity, em, method)
  }

  @throws[ODataJPAProcessException]
  override def validateChanges(em: EntityManager): Unit = {
    entityBuffer.foreach { case (instance: Any, requestEntity: JPARequestEntity) =>
      @throws[ODataJPAProcessException]
      def processBindingLinks()/*(relationLinks: ju.Map[JPAAssociationPath, ju.List[JPARequestLink]], instance: Any, modifyUtil: JPAModifyUtil, em: EntityManager)*/: Unit = {
        for {
          entity: (JPAAssociationPath, ju.List[JPARequestLink]) <- requestEntity.getRelationLinks.asScala
          pathInfo = entity._1
          requestLink: JPARequestLink <- entity._2.asScala
        } {
          def target = {
            def targetKey = requestEntity.getModifyUtil.createPrimaryKey(pathInfo.getTargetType.asInstanceOf[JPAEntityType], requestLink.getRelatedKeys, pathInfo.getSourceType)

            em.find(pathInfo.getTargetType.getTypeClass, targetKey)
          }

          requestEntity.getModifyUtil.linkEntities(instance, target, pathInfo)
        }
      }

      processBindingLinks()/*(value.getRelationLinks, instance, value.getModifyUtil, em)*/
    }
  }

  @throws[ODataJPAProcessException]
  private def createOneEntity(requestEntity: JPARequestEntity, /*final EntityManager em,*/ parent: Any) = {

    @throws[ODataJPAProcessorException]
    def createInstance(cons: Constructor[_], parent: Any): Any = try {
      if (cons.getParameterCount == 1) return cons.newInstance(parent)
      cons.newInstance()
    } catch {
      case e@(_: InstantiationException | _: IllegalAccessException | _: InvocationTargetException) =>
        throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
    }

    @throws[ODataJPAProcessorException]
    def getConstructor(st: JPAStructuredType, parentInstance: Any): Constructor[_] = {
      // If a parent exists, try to use a constructor that accepts the parent
      if (Option(parentInstance).isDefined) try return st.getTypeClass.getConstructor(parentInstance.getClass)
      catch {
        case _: NoSuchMethodException | _: SecurityException =>

      }
      try st.getTypeClass.getConstructor()
      catch {
        case e@(_: NoSuchMethodException | _: SecurityException) =>
          throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
      }
    }

    val instance: Any = createInstance(getConstructor(requestEntity.getEntityType, parent), parent)

    requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, requestEntity.getEntityType)
    entityBuffer += (instance -> requestEntity)
    instance
  }

}