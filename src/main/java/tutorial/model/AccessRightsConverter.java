package tutorial.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter(autoApply = false)
public class AccessRightsConverter implements AttributeConverter<AccessRights[], Short> {

    @Override
    public Short convertToDatabaseColumn(AccessRights[] attributes) {
        if (attributes == null)
            return null;
        short value = 0;
        for (AccessRights attribute : attributes)
            if (attribute != null)
                value += attribute.getValue();
        return value;
    }

    @Override
    public AccessRights[] convertToEntityAttribute(Short dbData) {
        if (dbData == null)
            return null;
        List<AccessRights> members = new ArrayList<>(4);
        if ((dbData & AccessRights.Read.getValue()) > 0) members.add(AccessRights.Read);
        if ((dbData & AccessRights.Create.getValue()) > 0) members.add(AccessRights.Create);
        if ((dbData & AccessRights.Write.getValue()) > 0) members.add(AccessRights.Write);
        if ((dbData & AccessRights.Delete.getValue()) > 0) members.add(AccessRights.Delete);
        return members.toArray(new AccessRights[]{});
    }

}