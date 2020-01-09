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
    private Integer area = 0;
    @Column(name = "\"Population\"", precision = 34)
    private Long population;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "\"CodePublisher\"", name = "\"CodePublisher\"", nullable = false,
                    insertable = false, updatable = false),
            @JoinColumn(referencedColumnName = "\"CodeID\"", name = "\"ParentCodeID\"", nullable = false),
            @JoinColumn(referencedColumnName = "\"DivisionCode\"", name = "\"ParentDivisionCode\"", nullable = false)})
    private AdministrativeDivision parent;

    public AdministrativeDivision getParent() {
        return parent;
    }

    public void setParent(AdministrativeDivision parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<AdministrativeDivision> children;

    public void setChildren(List<AdministrativeDivision> children) {
        this.children = children;
    }

    public String getCodePublisher() {
        return codePublisher;
    }

    public void setCodePublisher(String codePublisher) {
        this.codePublisher = codePublisher;
    }

    public String getCodeID() {
        return codeID;
    }

    public void setCodeID(String codeID) {
        this.codeID = codeID;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getParentCodeID() {
        return parentCodeID;
    }

    public void setParentCodeID(String parentCodeID) {
        this.parentCodeID = parentCodeID;
    }

    public String getParentDivisionCode() {
        return parentDivisionCode;
    }

    public void setParentDivisionCode(String parentDivisionCode) {
        this.parentDivisionCode = parentDivisionCode;
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public void setAlternativeCode(String alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeID == null) ? 0 : codeID.hashCode());
        result = prime * result + ((codePublisher == null) ? 0 : codePublisher.hashCode());
        result = prime * result + ((divisionCode == null) ? 0 : divisionCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AdministrativeDivision other = (AdministrativeDivision) obj;
        if (codeID == null) {
            if (other.codeID != null) return false;
        } else if (!codeID.equals(other.codeID)) return false;
        if (codePublisher == null) {
            if (other.codePublisher != null) return false;
        } else if (!codePublisher.equals(other.codePublisher)) return false;
        if (divisionCode == null) return other.divisionCode == null;
        else return divisionCode.equals(other.divisionCode);
    }

}