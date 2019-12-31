package tutorial.modify;

import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAEntityType;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAStructuredType;
import com.sap.olingo.jpa.processor.core.api.JPAAbstractCUDRequestHandler;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAInvocationTargetException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException;
import com.sap.olingo.jpa.processor.core.modify.JPAUpdateResult;
import com.sap.olingo.jpa.processor.core.processor.JPARequestEntity;
import com.sap.olingo.jpa.processor.core.processor.JPARequestLink;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.commons.api.http.HttpStatusCode;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CUDRequestHandler extends JPAAbstractCUDRequestHandler {

    private Object createInstance(final Constructor<?> cons) throws ODataJPAProcessorException {
        Object instance;
        try {
            instance = cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR);
        }
        return instance;
    }

    private Constructor<?> getConstructor(final JPAStructuredType st) {
        Constructor<?> cons = null;
        Constructor<?>[] constructors = st.getTypeClass().getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            cons = constructors[i];
            if (cons.getParameterCount() == 0) {
                break;
            }
        }
        return cons;
    }

    /*
    private Constructor<?> getConstructor(final JPAStructuredType st) {
        Constructor<?>[] constructors = st.getTypeClass().getConstructors();

        return Arrays.stream(constructors).findFirst().orElse(null);
    }
    */

    public Object createEntity(final JPARequestEntity requestEntity, final EntityManager em) throws ODataJPAProcessException {
        final JPAEntityType jpaEt = requestEntity.getEntityType();
        final Object instance = createInstance(getConstructor(jpaEt));

        requestEntity.getModifyUtil().setAttributesDeep(requestEntity.getData(), instance, jpaEt);
        em.persist(instance);
        return instance;
    }

    @Override
    public JPAUpdateResult updateEntity(final JPARequestEntity requestEntity, final EntityManager em,
                                        final HttpMethod method) throws ODataJPAProcessException {

        if (method == HttpMethod.PATCH || method == HttpMethod.DELETE) {
            final JPAEntityType jpaEt = requestEntity.getEntityType();
            final Object instance = em.find(jpaEt.getTypeClass(), requestEntity.getModifyUtil()
                            .createPrimaryKey(jpaEt, requestEntity.getKeys(), jpaEt));

            requestEntity.getModifyUtil().setAttributesDeep(requestEntity.getData(), instance, jpaEt);
            updateLinks(requestEntity, em, instance);
            return new JPAUpdateResult(false, instance);
        }
        return super.updateEntity(requestEntity, em, method);
    }

    private void updateLinks(final JPARequestEntity requestEntity, final EntityManager em, final Object instance)
            throws ODataJPAProcessorException, ODataJPAInvocationTargetException {
        if (requestEntity.getRelationLinks() != null) {
            for (Map.Entry<JPAAssociationPath, List<JPARequestLink>> links : requestEntity.getRelationLinks().entrySet()) {
                for (JPARequestLink link : links.getValue()) {
                    final Object related = em.find(link.getEntityType().getTypeClass(), requestEntity.getModifyUtil()
                            .createPrimaryKey(link.getEntityType(), link.getRelatedKeys(), link.getEntityType()));
                    requestEntity.getModifyUtil().linkEntities(instance, related, links.getKey());
                }
            }
        }
    }

    @Override
    public void deleteEntity(JPARequestEntity requestEntity, EntityManager em) throws ODataJPAProcessException {

        final Object instance = em.find(requestEntity.getEntityType().getTypeClass(),
                requestEntity.getModifyUtil().createPrimaryKey(requestEntity.getEntityType(), requestEntity.getKeys(),
                        requestEntity.getEntityType()));
        if (instance != null)
            em.remove(instance);
    }

}