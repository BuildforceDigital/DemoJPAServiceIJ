package tutorial.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "\"BusinessPartner\"", schema = "OLINGO")
public class BusinessPartnerEntity {
    private String id;
    private Long eTag;
    private String customString1;
    private String customString2;
    private BigDecimal customNum1;
    private Long customNum2;
    private String languageIso;
    private String country;
    private Collection<AdministrativeDivisionDescriptionEntity> administrativeDivDesciption;

    @Id
    @Column(name = "ID", length = 32, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"ETag\"")
    public Long geteTag() {
        return eTag;
    }

    public void seteTag(Long eTag) {
        this.eTag = eTag;
    }

    @Basic
    @Column(name = "\"CustomString1\"", length = 250)
    public String getCustomString1() {
        return customString1;
    }

    public void setCustomString1(String customString1) {
        this.customString1 = customString1;
    }

    @Basic
    @Column(name = "\"CustomString2\"", length = 250)
    public String getCustomString2() {
        return customString2;
    }

    public void setCustomString2(String customString2) {
        this.customString2 = customString2;
    }

    @Basic
    @Column(name = "\"CustomNum1\"", precision = 10, scale = 5)
    public BigDecimal getCustomNum1() {
        return customNum1;
    }

    public void setCustomNum1(BigDecimal customNum1) {
        this.customNum1 = customNum1;
    }

    @Basic
    @Column(name = "\"CustomNum2\"", scale = 5)
    public Long getCustomNum2() {
        return customNum2;
    }

    public void setCustomNum2(Long customNum2) {
        this.customNum2 = customNum2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessPartnerEntity that = (BusinessPartnerEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(eTag, that.eTag)) return false;
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

    @Basic
    @Column(name = "\"LanguageISO\"", length = 4)
    public String getLanguageIso() {
        return languageIso;
    }

    public void setLanguageIso(String languageIso) {
        this.languageIso = languageIso;
    }

    @Basic
    @Column(name = "\"Country\"", length = 4)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) { this.country = country; }


/*
    @OneToMany(mappedBy = "businessPartner")
    public Collection<AdministrativeDivisionDescriptionEntity> getAdministrativeDivDesciption() {
        return administrativeDivDesciption;
    }
*/

    public void setAdministrativeDivDesciption(Collection<AdministrativeDivisionDescriptionEntity> administrativeDivDesciption) {
        this.administrativeDivDesciption = administrativeDivDesciption;
    }

}