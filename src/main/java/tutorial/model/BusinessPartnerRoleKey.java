package tutorial.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class BusinessPartnerRoleKey /*implements Serializable*/ {
    @Id
    @Column(name = "\"BusinessPartnerID\"")
    private String businessPartnerID;
    @Id
    @Column(name = "\"BusinessPartnerRole\"")
    private String roleCategory;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BusinessPartnerRoleKey other = (BusinessPartnerRoleKey) obj;
        if (businessPartnerID == null) {
            if (other.businessPartnerID != null)
                return false;
        } else if (!businessPartnerID.equals(other.businessPartnerID))
            return false;
        if (roleCategory == null) return other.roleCategory == null;
        else return roleCategory.equals(other.roleCategory);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((businessPartnerID == null) ? 0 : businessPartnerID.hashCode());
        result = prime * result + ((roleCategory == null) ? 0 : roleCategory.hashCode());
        return result;
    }
}