package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmDescriptionAssociation;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Embeddable
public class PostalDataEntity {
    @Basic
    @Column(name ="\"Address.StreetName\"", length = 200)
    private String addressStreetName;

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public void setAddressStreetName(String addressStreetName) {
        this.addressStreetName = addressStreetName;
    }

    @Basic
    @Column(name ="\"Address.StreetNumber\"", length = 60)
    private String addressStreetNumber;

    public String getAddressStreetNumber() {
        return addressStreetNumber;
    }

    public void setAddressStreetNumber(String addressStreetNumber) {
        this.addressStreetNumber = addressStreetNumber;
    }

    @Basic
    @Column(name ="\"Address.PostOfficeBox\"", length = 60)
    private String addressPostOfficeBox;

    public String getAddressPostOfficeBox() {
        return addressPostOfficeBox;
    }

    public void setAddressPostOfficeBox(String addressPostOfficeBox) {
        this.addressPostOfficeBox = addressPostOfficeBox;
    }

    @Basic
    @Column(name ="\"Address.City\"", length = 100)
    private String addressCity;

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    @Basic
    @Column(name ="\"Address.PostalCode\"", length = 60)
    private String addressPostalCode;

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    @Basic
    @Column(name ="ADDRESS_REGIONCODEPUBLISHER", nullable = false, length = 10)
    private String addressRegionCodePublisher;

    public String getAddressRegionCodePublisher() {
        return addressRegionCodePublisher;
    }

    public void setAddressRegionCodePublisher(String addressRegionCodePublisher) {
        this.addressRegionCodePublisher = addressRegionCodePublisher;
    }

    @Basic
    @Column(name ="ADDRESS_REGIONCODEID", nullable = false, length = 10)
    private String addressRegionCodeId;
    public String getAddressRegionCodeId() {
        return addressRegionCodeId;
    }

    public void setAddressRegionCodeId(String addressRegionCodeId) {
        this.addressRegionCodeId = addressRegionCodeId;
    }

    @Basic
    @Column(name ="ADDRESS_REGION", length = 100)
    private String addressRegion;

    public String getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(String addressRegion) {
        this.addressRegion = addressRegion;
    }

    @Basic
    @Column(name ="\"Address.Country\"", length = 100)
    private String addressCountry;
    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }


    @EdmDescriptionAssociation(languageAttribute = "key/languageIso", descriptionAttribute = "description")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "\"CodePublisher\"", referencedColumnName = "ADDRESS_REGIONCODEPUBLISHER", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"CodeID\"", referencedColumnName = "ADDRESS_REGIONCODEID", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "ADDRESS_REGION", nullable = false, insertable = false, updatable = false)
    })
    private Collection<AdministrativeDivisionDescriptionEntity> regionDescriptions;

    @Override
    public String toString() {
        return "PostalAddressData [streetName=" + addressStreetName + ", houseNumber=" + addressStreetNumber + ", pOBox=" + addressPostOfficeBox
                + ", postalCode=" + addressPostalCode + ", cityName=" + addressCity + ", country=" + addressCountry + ", region=" + addressRegion + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostalDataEntity that = (PostalDataEntity) o;

        if (!Objects.equals(addressStreetName, that.addressStreetName)) return false;
        if (!Objects.equals(addressStreetNumber, that.addressStreetNumber)) return false;
        if (!Objects.equals(addressPostOfficeBox, that.addressPostOfficeBox)) return false;
        if (!Objects.equals(addressCity, that.addressCity)) return false;
        if (!Objects.equals(addressPostalCode, that.addressPostalCode)) return false;
        if (!Objects.equals(addressRegionCodePublisher, that.addressRegionCodePublisher)) return false;
        if (!Objects.equals(addressRegionCodeId, that.addressRegionCodeId)) return false;
        if (!Objects.equals(addressRegion, that.addressRegion)) return false;
        return Objects.equals(addressCountry, that.addressCountry);
    }

    @Override
    public int hashCode() {
        int result = addressStreetName != null ? addressStreetName.hashCode() : 0;
        result = 31 * result + (addressStreetNumber != null ? addressStreetNumber.hashCode() : 0);
        result = 31 * result + (addressPostOfficeBox != null ? addressPostOfficeBox.hashCode() : 0);
        result = 31 * result + (addressCity != null ? addressCity.hashCode() : 0);
        result = 31 * result + (addressPostalCode != null ? addressPostalCode.hashCode() : 0);
        result = 31 * result + (addressRegionCodePublisher != null ? addressRegionCodePublisher.hashCode() : 0);
        result = 31 * result + (addressRegionCodeId != null ? addressRegionCodeId.hashCode() : 0);
        result = 31 * result + (addressRegion != null ? addressRegion.hashCode() : 0);
        result = 31 * result + (addressCountry != null ? addressCountry.hashCode() : 0);
        return result;
    }

}