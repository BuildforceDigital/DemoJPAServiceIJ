package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmDescriptionAssociation;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Inheritance
@DiscriminatorColumn(name = "\"Type\"")
@Entity(name = "BusinessPartner")
@Table(name = "\"BusinessPartner\"", schema = "OLINGO")
public abstract class BusinessPartnerEntity {
    @Id
    @Column(name = "ID", length = 32, nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"ETag\"")
    private Long eTag;

    public Long geteTag() {
        return eTag;
    }

    public void seteTag(Long eTag) {
        this.eTag = eTag;
    }

    @EdmIgnore
    @Column(name = "\"CustomString1\"", length = 250)
    private String customString1;

    public String getCustomString1() { return customString1; }

    public void setCustomString1(String customString1) { this.customString1 = customString1; }

    @EdmIgnore
    @Column(name = "\"CustomString2\"", length = 250)
    private String customString2;

    public String getCustomString2() { return customString2; }

    public void setCustomString2(String customString2) { this.customString2 = customString2; }

    @EdmIgnore
    @Column(name = "\"CustomNum1\"", precision = 10, scale = 5)
    private BigDecimal customNum1;

    public BigDecimal getCustomNum1() { return customNum1; }

    public void setCustomNum1(BigDecimal customNum1) { this.customNum1 = customNum1; }

    @EdmIgnore
    @Column(name = "\"CustomNum2\"", scale = 5)
    private Long customNum2;

    public Long getCustomNum2() { return customNum2; }

    public void setCustomNum2(Long customNum2) { this.customNum2 = customNum2; }

    @Basic
    @Column(name = "\"Country\"", length = 4)
    private String country;

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    @OneToMany(mappedBy = "businessPartnerByBusinessPartnerId", cascade = CascadeType.REMOVE)
    private Collection<BusinessPartnerRoleEntity> roles;

    public Collection<BusinessPartnerRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<BusinessPartnerRoleEntity> roles) {
        this.roles = roles;
    }

    @Embedded
    private PostalDataEntity address = new PostalDataEntity();

    public PostalDataEntity getAddress() { return address; }

    public void setAddress(PostalDataEntity address) { this.address = address; }

    @Embedded
    private AdministrativeInformationEntity administrativeInformation = new AdministrativeInformationEntity();

    public AdministrativeInformationEntity getAdministrativeInformation() { return administrativeInformation; }

    public void setAdministrativeInformation(AdministrativeInformationEntity administrativeInformation)
        { this.administrativeInformation = administrativeInformation; }

    @EdmDescriptionAssociation(languageAttribute = "key/languageIso", descriptionAttribute = "description", valueAssignments = {
            @EdmDescriptionAssociation.valueAssignment(attribute = "key/codePublisher", value = "ISO"),
            @EdmDescriptionAssociation.valueAssignment(attribute = "key/codeId", value = "3166-1")})
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "\"Country\"", insertable = false, updatable = false)
    private Collection<AdministrativeDivisionDescriptionEntity> countryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessPartnerEntity that = (BusinessPartnerEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(eTag, that.eTag)) return false;

        if (!Objects.equals(administrativeInformation, that.administrativeInformation)) return false;
        if (!Objects.equals(address, that.address)) return false;
        if (!Objects.equals(country, that.country)) return false;

        if (!Objects.equals(customString1, that.customString1)) return false;
        if (!Objects.equals(customString2, that.customString2)) return false;
        if (!Objects.equals(customNum1, that.customNum1)) return false;
        return Objects.equals(customNum2, that.customNum2);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (eTag != null ? eTag.hashCode() : 0);
        result = 31 * result + (customString1 != null ? customString1.hashCode() : 0);
        result = 31 * result + (customString2 != null ? customString2.hashCode() : 0);
        result = 31 * result + (customNum1 != null ? customNum1.hashCode() : 0);
        result = 31 * result + (customNum2 != null ? customNum2.hashCode() : 0);
        return result;
    }

}