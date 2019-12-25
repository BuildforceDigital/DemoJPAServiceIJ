package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmDescriptionAssoziation;

import javax.persistence.*;
import java.util.Collection;

@Embeddable
public class PostalAddressData {

    @Column(name = "\"Address.StreetName\"", nullable = true)
    private String streetName;

    @Column(name = "\"Address.StreetNumber\"", nullable = true)
    private String houseNumber;

    @Column(name = "\"Address.PostOfficeBox\"", nullable = true)
    private String POBox;

    @Column(name = "\"Address.PostalCode\"")
    private String postalCode;

    @Column(name = "\"Address.City\"")
    private String cityName;

    @Column(name = "\"Address.Country\"")
    private String country;

    @Column(name = "\"Address.RegionCodePublisher\"", length = 10)
    private String regionCodePublisher = "ISO";

    @Column(name = "\"Address.RegionCodeID\"", length = 10)
    private String regionCodeID = "3166-2";

    @Column(name = "\"Address.Region\"")
    private String region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "\"Address.RegionCodePublisher\"", referencedColumnName = "\"CodePublisher\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"Address.RegionCodeID\"", referencedColumnName = "\"CodeID\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"Address.Region\"", referencedColumnName = "\"DivisionCode\"", nullable = false, insertable = false, updatable = false) })
    private AdministrativeDivision administrativeDivision;

    @EdmDescriptionAssoziation(languageAttribute = "key/language", descriptionAttribute = "name")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "\"CodePublisher\"", referencedColumnName = "\"Address.RegionCodePublisher\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"CodeID\"", referencedColumnName = "\"Address.RegionCodeID\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "\"Address.Region\"", nullable = false, insertable = false, updatable = false)
    })
    private Collection<AdministrativeDivisionDescription> regionName;
}