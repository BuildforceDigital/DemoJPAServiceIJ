package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmDescriptionAssociation;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class PostalDataEntity {
    @Basic
    @Column(name ="\"Address.StreetName\"", length = 200)
    private String streetName;

    public String getStreetName() { return streetName; }

    public void setStreetName(String addressStreetName) { this.streetName = addressStreetName; }

    @Basic
    @Column(name ="\"Address.StreetNumber\"", length = 60)
    private String houseNumber;

    public String getHouseNumber() { return houseNumber; }

    public void setHouseNumber(String addressStreetNumber) { this.houseNumber = addressStreetNumber; }

    @Basic
    @Column(name ="\"Address.PostOfficeBox\"", length = 60)
    private String pOBox;

    public String getPOBox() { return pOBox; }

    public void setPOBox(String addressPostOfficeBox) { this.pOBox = addressPostOfficeBox; }

    @Basic
    @Column(name ="\"Address.City\"", length = 100)
    private String cityName;

    public String getCityName() { return cityName; }

    public void setCityName(String addressCity) { this.cityName = addressCity; }

    @Basic
    @Column(name ="\"Address.PostalCode\"", length = 60)
    private String postalCode;

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String addressPostalCode) { this.postalCode = addressPostalCode; }

    @Basic
    @Column(name ="ADDRESS_REGIONCODEPUBLISHER", nullable = false, length = 10)
    private String regionCodePublisher = "ISO";

    public String getRegionCodePublisher() { return regionCodePublisher; }

    public void setRegionCodePublisher(String addressRegionCodePublisher)
    { this.regionCodePublisher = addressRegionCodePublisher; }

    @Basic
    @Column(name ="ADDRESS_REGIONCODEID", nullable = false, length = 10)
    private String regionCodeId = "3166-2";
    public String getRegionCodeId() { return regionCodeId; }

    public void setRegionCodeId(String addressRegionCodeId) { this.regionCodeId = addressRegionCodeId; }

    @Basic
    @Column(name ="ADDRESS_REGION", length = 10)
    private String region;

    public String getRegion() { return region; }

    public void setRegion(String addressRegion) { this.region = addressRegion; }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ADDRESS_REGIONCODEPUBLISHER", referencedColumnName = "\"CodePublisher\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "ADDRESS_REGIONCODEID", referencedColumnName = "\"CodeID\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "ADDRESS_REGION", referencedColumnName = "\"DivisionCode\"", nullable = false, insertable = false, updatable = false) })
    private AdministrativeDivisionEntity administrativeDivision;

    @Basic
    @Column(name ="\"Address.Country\"", length = 100)
    private String country;
    public String getCountry() { return country; }

    public void setCountry(String addressCountry) { this.country = addressCountry; }

/*
    @EdmDescriptionAssociation(languageAttribute = "key/languageIso", descriptionAttribute = "description")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "\"CodePublisher\"", referencedColumnName = "\"ADDRESS_REGIONCODEPUBLISHER\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"CodeID\"", referencedColumnName = "\"ADDRESS_REGIONCODEID\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "\"ADDRESS_REGION\"", nullable = false, insertable = false, updatable = false)
    })
    private Collection<AdministrativeDivisionDescriptionEntity> regionDescriptions;
*/

    @Override
    public String toString() {
        return "PostalAddressData [streetName=" + streetName + ", houseNumber=" + houseNumber + ", pOBox=" + pOBox
                + ", postalCode=" + postalCode + ", cityName=" + cityName + ", country=" + country + ", region=" + region + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostalDataEntity that = (PostalDataEntity) o;

        if (!Objects.equals(streetName, that.streetName)) return false;
        if (!Objects.equals(houseNumber, that.houseNumber)) return false;
        if (!Objects.equals(pOBox, that.pOBox)) return false;
        if (!Objects.equals(cityName, that.cityName)) return false;
        if (!Objects.equals(postalCode, that.postalCode)) return false;
        if (!Objects.equals(regionCodePublisher, that.regionCodePublisher)) return false;
        if (!Objects.equals(regionCodeId, that.regionCodeId)) return false;
        if (!Objects.equals(region, that.region)) return false;
        return Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        int result = streetName != null ? streetName.hashCode() : 0;
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (pOBox != null ? pOBox.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (regionCodePublisher != null ? regionCodePublisher.hashCode() : 0);
        result = 31 * result + (regionCodeId != null ? regionCodeId.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

}