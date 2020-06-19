package tutorial.persistence

import java.lang.reflect.{Constructor, InvocationTargetException}
import java.util

import nl.buildforce.sequoia.metadata.core.edm.mapper.api.{JPAAssociationPath, JPAEntityType, JPAStructuredType}
import nl.buildforce.sequoia.metadata.core.edm.mapper.exception.ODataJPAModelException
import nl.buildforce.sequoia.processor.core.api.JPAAbstractCUDRequestHandler
import nl.buildforce.sequoia.processor.core.api.example.JPAExampleModifyException
import nl.buildforce.sequoia.processor.core.api.example.JPAExampleModifyException.MessageKeys.{ENTITY_ALREADY_EXISTS, ENTITY_NOT_FOUND}
import nl.buildforce.sequoia.processor.core.exception.{ODataJPAInvocationTargetException, ODataJPAProcessException, ODataJPAProcessorException}
import nl.buildforce.sequoia.processor.core.modify.JPAUpdateResult
import nl.buildforce.sequoia.processor.core.processor.{JPAModifyUtil, JPARequestEntity, JPARequestLink}
import jakarta.persistence.EntityManager
import nl.buildforce.olingo.commons.api.http.{HttpMethod, HttpStatusCode}

import java.{util => ju}

import scala.jdk.CollectionConverters.{ListHasAsScala, SetHasAsScala}

/**
 * Example implementation at a CUD handler. The main purpose is rapid prototyping.<p/>
 * The implementation requires Getter and Setter. This includes getter for collection properties and collection
 * navigation properties that return at least empty collections.<br/>
 * To link entities constructor injection is used. So each dependent entity needs a constructor that takes a entity type
 * it depends on as parameter.
 *
 * @author Oliver Grande
 *
 */
class ExampleCUDRequestHandler() extends JPAAbstractCUDRequestHandler {
  final private val entityBuffer = new util.HashMap[Any, JPARequestEntity]

  @throws[ODataJPAProcessException]
  override def createEntity(requestEntity: JPARequestEntity, em: EntityManager): Any = {
    var instance: Any = null
    if (requestEntity.getKeys.isEmpty) { // POST an Entity
      instance = createOneEntity(requestEntity, /*em,*/ null)
      val old = em.find(requestEntity.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, instance))
      if (old != null) throw new JPAExampleModifyException(ENTITY_ALREADY_EXISTS, HttpStatusCode.BAD_REQUEST)
    }
    else { // POST on Link only // https://issues.oasis-open.org/browse/ODATA-1294
      instance = findEntity(requestEntity, em)
    }
    processRelatedEntities(requestEntity.getRelatedEntities, instance, requestEntity.getModifyUtil, em)
    em.persist(instance)
    instance
  }

  @throws[ODataJPAProcessException]
  override def deleteEntity(requestEntity: JPARequestEntity, em: EntityManager): Unit = {
    val instance = em.find(requestEntity.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, requestEntity.getKeys, requestEntity.getEntityType))
    if (instance != null) em.remove(instance)
  }

  @throws[ODataJPAProcessException]
  override def updateEntity(requestEntity: JPARequestEntity, em: EntityManager, method: HttpMethod): JPAUpdateResult = {
    if ((method eq HttpMethod.PATCH) || (method eq HttpMethod.DELETE)) {
      val instance = em.find(requestEntity.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, requestEntity.getKeys, requestEntity.getEntityType))
      if (instance == null) throw new JPAExampleModifyException(ENTITY_NOT_FOUND, HttpStatusCode.NOT_FOUND)
      requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, requestEntity.getEntityType)
      updateLinks(requestEntity, em, instance)
      return new JPAUpdateResult(false, instance)
    }
    super.updateEntity(requestEntity, em, method)
  }

  @throws[ODataJPAProcessorException]
  @throws[ODataJPAInvocationTargetException]
  private def updateLinks(requestEntity: JPARequestEntity, em: EntityManager, instance: Any): Unit = {
    if (requestEntity.getRelationLinks != null) {
      for (links <- requestEntity.getRelationLinks.entrySet.asScala) {
        for (link <- links.getValue.asScala) {
          val related = em.find(link.getEntityType.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(link.getEntityType, link.getRelatedKeys, link.getEntityType))
          requestEntity.getModifyUtil.linkEntities(instance, related, links.getKey)
        }
      }
    }
  }

  @throws[ODataJPAProcessException]
  override def validateChanges(em: EntityManager): Unit = {
    for (entity <- entityBuffer.entrySet.asScala) {
      processBindingLinks(entity.getValue.getRelationLinks, entity.getKey, entity.getValue.getModifyUtil, em)
    }
  }

  @throws[ODataJPAProcessException]
  private def processBindingLinks(relationLinks: ju.Map[JPAAssociationPath, util.List[JPARequestLink]], instance: Any, util0: JPAModifyUtil, em: EntityManager): Unit = {
    for (entity <- relationLinks.entrySet.asScala) {
      val pathInfo = entity.getKey
      for (requestLink <- entity.getValue.asScala) {
        val targetKey = util0.createPrimaryKey(pathInfo.getTargetType.asInstanceOf[JPAEntityType], requestLink.getRelatedKeys, pathInfo.getSourceType)
        val target = em.find(pathInfo.getTargetType.getTypeClass, targetKey)
        util0.linkEntities(instance, target, pathInfo)
      }
    }
  }

  @throws[ODataJPAProcessorException]
  private def createInstance(cons: Constructor[_], parent: Any): Any = try {
    if (cons.getParameterCount == 1) return cons.newInstance(parent)
    cons.newInstance()
  } catch {
    case e@(_: InstantiationException | _: IllegalAccessException | _: InvocationTargetException) =>
      throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
  }

  @throws[ODataJPAProcessException]
  private def createOneEntity(requestEntity: JPARequestEntity, /*final EntityManager em,*/ parent: Any) = {
    val instance: Any = createInstance(getConstructor(requestEntity.getEntityType, parent), parent)
    requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, requestEntity.getEntityType)
    entityBuffer.put(instance, requestEntity)
    instance
  }

  @throws[ODataJPAProcessorException]
  @throws[ODataJPAInvocationTargetException]
  private def findEntity(requestEntity: JPARequestEntity, em: EntityManager) = {
    val key = requestEntity.getModifyUtil.createPrimaryKey(requestEntity.getEntityType, requestEntity.getKeys, requestEntity.getEntityType)
    em.getReference(requestEntity.getEntityType.getTypeClass, key)
  }

  @throws[ODataJPAProcessorException]
  private def getConstructor(st: JPAStructuredType, parentInstance: Any): Constructor[_] = { // If a parent exists, try to use a constructor that accepts the parent
    if (parentInstance != null) try return st.getTypeClass.getConstructor(parentInstance.getClass)
    catch {
      case _: NoSuchMethodException | _: SecurityException =>

    }
    try st.getTypeClass.getConstructor()
    catch {
      case e@(_: NoSuchMethodException | _: SecurityException) =>
        throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
    }
  }

  @throws[ODataJPAProcessException]
  private def processRelatedEntities(relatedEntities: ju.Map[JPAAssociationPath, ju.List[JPARequestEntity]], parentInstance: Any, util: JPAModifyUtil, em: EntityManager): Unit = {
    for (entity <- relatedEntities.entrySet.asScala) {
      val pathInfo = entity.getKey
      for (requestEntity <- entity.getValue.asScala) {
        val newInstance = createOneEntity(requestEntity, parentInstance)
        util.linkEntities(parentInstance, newInstance, pathInfo)
        if (pathInfo.getPartner != null) try util.linkEntities(newInstance, parentInstance, pathInfo.getPartner.getPath)
        catch {
          case e: ODataJPAModelException =>
            throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
        }
        processRelatedEntities(requestEntity.getRelatedEntities, newInstance, util, em)
      }
    }
  }
}