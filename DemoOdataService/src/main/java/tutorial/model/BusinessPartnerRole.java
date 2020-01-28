package tutorial.model;

import org.eclipse.persistence.annotations.ReadOnly;

import javax.persistence.*;

@IdClass(BusinessPartnerRoleKey.class)
@ReadOnly
@Entity(name = "BusinessPartnerRole")
@Table(schema = "\"OLINGO\"", name = "\"BusinessPartnerRole\"")
public class BusinessPartnerRole {
    @Id
    @Column(name = "\"BusinessPartnerID\"")
    private String businessPartnerID;

    @Id
    @Column(name = "\"BusinessPartnerRole\"")
    private String roleCategory;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "\"BusinessPartnerID\"", insertable = false, updatable = false)
    private BusinessPartner businessPartner;

    public String getBusinessPartnerID() {
        return businessPartnerID;
    }

    public String getRoleCategory() {
        return roleCategory;
    }

    public BusinessPartner getBusinessPartner() {
        return businessPartner;
    }
}