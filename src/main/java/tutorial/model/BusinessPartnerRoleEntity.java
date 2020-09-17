package tutorial.model;

//import org.eclipse.persistence.annotations.ReadOnly;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
// @ReadOnly
@Table(name = "\"BusinessPartnerRole\"", schema = "OLINGO")
@IdClass(BusinessPartnerRoleEntityPK.class)
public class BusinessPartnerRoleEntity {
    @Id
    @Column(name = "\"BusinessPartnerID\"", length = 32)
    private String businessPartnerId;
    public String getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(String businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    @Id
    @Column(name = "\"BusinessPartnerRole\"", length = 10)
    private String businessPartnerRole;
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

        BusinessPartnerRoleEntity that = (BusinessPartnerRoleEntity) o;

        if (!Objects.equals(businessPartnerId, that.businessPartnerId)) return false;
        return Objects.equals(businessPartnerRole, that.businessPartnerRole);
    }

    @Override
    public int hashCode() {
        int result = businessPartnerId != null ? businessPartnerId.hashCode() : 0;
        result = 31 * result + (businessPartnerRole != null ? businessPartnerRole.hashCode() : 0);
        return result;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "\"BusinessPartnerID\"", referencedColumnName = "ID", insertable = false, updatable = false)
    private BusinessPartnerEntity businessPartnerByBusinessPartnerId;

    public BusinessPartnerEntity getBusinessPartnerByBusinessPartnerId() {
        return businessPartnerByBusinessPartnerId;
    }

    public void setBusinessPartnerByBusinessPartnerId(BusinessPartnerEntity businessPartnerByBusinessPartnerId) {
        this.businessPartnerByBusinessPartnerId = businessPartnerByBusinessPartnerId;
    }

}