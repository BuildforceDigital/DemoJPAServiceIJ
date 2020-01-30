package tutorial.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BusinessPartnerRoleEntityPK implements Serializable {
    private String businessPartnerId;
    private String businessPartnerRole;

    @Column(name = "BusinessPartnerID", nullable = false, length = 32)
    @Id
    public String getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(String businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    @Column(name = "BusinessPartnerRole", nullable = false, length = 10)
    @Id
    public String getBusinessPartnerRole() {
        return businessPartnerRole;
    }

    public void setBusinessPartnerRole(String businessPartnerRole) {
        this.businessPartnerRole = businessPartnerRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessPartnerRoleEntityPK that = (BusinessPartnerRoleEntityPK) o;

        if (!Objects.equals(businessPartnerId, that.businessPartnerId)) return false;
        return Objects.equals(businessPartnerRole, that.businessPartnerRole);
    }

    @Override
    public int hashCode() {
        int result = businessPartnerId != null ? businessPartnerId.hashCode() : 0;
        result = 31 * result + (businessPartnerRole != null ? businessPartnerRole.hashCode() : 0);
        return result;
    }
}