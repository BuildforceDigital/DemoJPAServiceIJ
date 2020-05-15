package tutorial.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter(/*autoApply = false*/)
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
        if (dbData == null) return null;
        final List<AccessRights> accesses = new ArrayList<>();
        for (AccessRights e : AccessRights.values()) {
            if ((e.getValue() & dbData) != 0)
                accesses.add(e);
        }
        return accesses.toArray(new AccessRights[] {});
    }

}