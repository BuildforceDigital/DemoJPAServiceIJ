package tutorial.modify;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;

import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAStructuredType;
import com.sap.olingo.jpa.processor.core.api.JPAAbstractCUDRequestHandler;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException;
import com.sap.olingo.jpa.processor.core.processor.JPARequestEntity;
import org.apache.olingo.commons.api.http.HttpStatusCode;

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

    public Object createEntity(final JPARequestEntity requestEntity, final EntityManager em)
            throws ODataJPAProcessException {

        final Object instance = createInstance(getConstructor(requestEntity.getEntityType()));
        requestEntity.getModifyUtil().setAttributesDeep(requestEntity.getData(), instance, requestEntity.getEntityType());
        em.persist(instance);
        return instance;

    }

/*    @Override
    public Object createEntity(final JPARequestEntity requestEntity, final EntityManager em)
            throws ODataJPAProcessException {

        final JPAEntityType et = requestEntity.getEntityType();

        if (et.getExternalName().equals("AdministrativeDivision")) {
            final Map<String, Object> jpaAttributes = requestEntity.getData();
            AdministrativeDivision result = new AdministrativeDivision();

            result.setCodeID((String) jpaAttributes.get("codeID"));
            result.setCodePublisher((String) jpaAttributes.get("codePublisher"));
            result.setDivisionCode((String) jpaAttributes.get("divisionCode"));

            result.setCountryCode((String) jpaAttributes.get("countryCode"));
            result.setParentCodeID((String) jpaAttributes.get("parentCodeID"));
            result.setParentDivisionCode((String) jpaAttributes.get("parentDivisionCode"));

            result.setAlternativeCode((String) jpaAttributes.get("alternativeCode"));
            result.setArea((Integer) jpaAttributes.get("area"));
            result.setPopulation((Long) jpaAttributes.get("population"));

            em.persist(result);
            return result;
        }
        return super.createEntity(requestEntity, em);
    }*/

}