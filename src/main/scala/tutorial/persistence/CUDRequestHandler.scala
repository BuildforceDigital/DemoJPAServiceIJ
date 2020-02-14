package tutorial.persistence

import java.lang.reflect.{Constructor, InvocationTargetException}
import java.util

import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAStructuredType
import com.sap.olingo.jpa.processor.core.api.JPAAbstractCUDRequestHandler
import com.sap.olingo.jpa.processor.core.exception.{ODataJPAInvocationTargetException, ODataJPAProcessException, ODataJPAProcessorException}
import com.sap.olingo.jpa.processor.core.modify.JPAUpdateResult
import com.sap.olingo.jpa.processor.core.processor.JPARequestEntity
import javax.persistence.EntityManager
import org.apache.olingo.commons.api.http.{HttpMethod, HttpStatusCode}

import scala.jdk.CollectionConverters._

class CUDRequestHandler extends JPAAbstractCUDRequestHandler {
  @throws[ODataJPAProcessorException]
   def createInstance(cons: Constructor[_]) = {
    var instance: Any = null
    try instance = cons.newInstance()
    catch {
      case e@(_: InstantiationException | _: IllegalAccessException | _: IllegalArgumentException | _: InvocationTargetException) =>
        throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR)
    }
    instance
  }

  @throws[ODataJPAProcessException]
  override def createEntity(requestEntity: JPARequestEntity, em: EntityManager): Any = {

    def getConstructor(st: JPAStructuredType) = {
      val constructors = st.getTypeClass.getConstructors
      util.Arrays.stream(constructors).findFirst.orElse(null)
    }

    val jpaEt = requestEntity.getEntityType
    val instance = createInstance(getConstructor(jpaEt))
    requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, jpaEt)
    em.persist(instance)
    instance
  }


  @throws[ODataJPAProcessException]
  override def updateEntity(requestEntity: JPARequestEntity, em: EntityManager, method: HttpMethod): JPAUpdateResult = {
    if ((method eq HttpMethod.PATCH) || (method eq HttpMethod.DELETE)) {
      val jpaEt = requestEntity.getEntityType
      val primaryKey = requestEntity.getModifyUtil.createPrimaryKey(jpaEt, requestEntity.getKeys, jpaEt)
      val instance = em.find(jpaEt.getTypeClass, primaryKey)
      requestEntity.getModifyUtil.setAttributesDeep(requestEntity.getData, instance, jpaEt)
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
          val lnkEt = link.getEntityType
          val related = em.find(lnkEt.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(lnkEt, link.getRelatedKeys, lnkEt))
          requestEntity.getModifyUtil.linkEntities(instance, related, links.getKey)
        }
      }
    }
  }

  @throws[ODataJPAProcessException]
  override def deleteEntity(requestEntity: JPARequestEntity, em: EntityManager): Unit = {
    val reqEt = requestEntity.getEntityType
    val instance = em.find(reqEt.getTypeClass, requestEntity.getModifyUtil.createPrimaryKey(reqEt, requestEntity.getKeys, reqEt))
    if (instance != null) em.remove(instance)
  }
}