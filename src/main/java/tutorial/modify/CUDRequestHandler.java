package tutorial.modify;

import java.util.Map;

import javax.persistence.EntityManager;

import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAEntityType;
import com.sap.olingo.jpa.processor.core.api.JPAAbstractCUDRequestHandler;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessException;
import com.sap.olingo.jpa.processor.core.processor.JPARequestEntity;
import tutorial.model.AdministrativeDivision;

public class CUDRequestHandler extends JPAAbstractCUDRequestHandler {

    @Override
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
    }

}