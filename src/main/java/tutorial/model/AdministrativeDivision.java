package tutorial.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@IdClass(AdministrativeDivisionKey.class)
@Entity(name = "AdministrativeDivision")
@Table(schema = "\"OLINGO\"", name = "\"AdministrativeDivision\"")
public class AdministrativeDivision {

    @Id
    @Column(name = "\"CodePublisher\"", length = 10)
    private String codePublisher;
    @Id
    @Column(name = "\"CodeID\"", length = 10)
    private String codeID;
    @Id
    @Column(name = "\"DivisionCode\"", length = 10)
    private String divisionCode;

    @Column(name = "\"CountryISOCode\"", length = 4)
    private String countryCode;
    @Column(name = "\"ParentCodeID\"", length = 10, insertable = false, updatable = false)
    private String parentCodeID;
    @Column(name = "\"ParentDivisionCode\"", length = 10, insertable = false, updatable = false)
    private String parentDivisionCode;
    @Column(name = "\"AlternativeCode\"", length = 10)
    private String alternativeCode;
    @Column(name = "\"Area\"")
    private Integer area;
    @Column(name = "\"Population\"")
    private Long population;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "\"CodePublisher\"", name = "\"CodePublisher\"", nullable = false,
                    insertable = false, updatable = false),
            @JoinColumn(referencedColumnName = "\"CodeID\"", name = "\"ParentCodeID\"", nullable = false),
            @JoinColumn(referencedColumnName = "\"DivisionCode\"", name = "\"ParentDivisionCode\"", nullable = false) })
    private AdministrativeDivision parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<AdministrativeDivision> children;
}