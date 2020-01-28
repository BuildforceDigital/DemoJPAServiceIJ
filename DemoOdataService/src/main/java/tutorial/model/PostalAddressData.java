package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmDescriptionAssociation;

import javax.persistence.*;
import java.util.Collection;

@Embeddable
public class PostalAddressData {

    @Column(name = "\"Address.StreetName\"")
    private String streetName;

    @Column(name = "\"Address.StreetNumber\"")
    private String houseNumber;

    @Column(name = "\"Address.PostOfficeBox\"")
    private String poBox;

    @Column(name = "\"Address.PostalCode\"")
    private String postalCode;

    @Column(name = "\"Address.City\"")
    private String cityName;

    @Column(name = "\"Address.Country\"")
    private String country;

    @Column(name = "\"REGIONCODEPUBLISHER\"", length = 10)
    private String regionCodePublisher = "ISO";

    @Column(name = "\"REGIONCODEID\"", length = 10)
    private String regionCodeID = "3166-2";

    @Column(name = "\"REGION\"")
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "\"REGIONCODEPUBLISHER\"", referencedColumnName = "\"CodePublisher\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"REGIONCODEID\"", referencedColumnName = "\"CodeID\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"REGION\"", referencedColumnName = "\"DivisionCode\"", nullable = false, insertable = false, updatable = false) })
    private AdministrativeDivision administrativeDivision;

    @EdmDescriptionAssociation(languageAttribute = "key/language", descriptionAttribute = "name")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "\"CodePublisher\"", referencedColumnName = "\"REGIONCODEPUBLISHER\"", insertable = false, updatable = false),
            @JoinColumn(name = "\"CodeID\"", referencedColumnName = "\"REGIONCODEID\"", insertable = false, updatable = false),
            @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "\"REGION\"", insertable = false, updatable = false)
    })
    private Collection<AdministrativeDivisionDescription> regionName;

    @Override
    public String toString() {
        return "PostalAddressData [streetName=" + streetName + ", houseNumber=" + houseNumber + ", pOBox=" + poBox
                + ", postalCode=" + postalCode + ", cityName=" + cityName + ", country=" + country + ", region=" + region + "]";
    }

}