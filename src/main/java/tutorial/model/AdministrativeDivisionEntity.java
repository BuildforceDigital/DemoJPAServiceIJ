package tutorial.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "AdministrativeDivision")
@Table(name = "\"AdministrativeDivision\"", schema = "OLINGO")
@IdClass(AdministrativeDivisionEntityPK.class)
public class AdministrativeDivisionEntity {
    @Id
    @Column(name = "\"CodePublisher\"", length = 10)
    private String codePublisher;

    public String getCodePublisher() { return codePublisher; }

    public void setCodePublisher(String codePublisher) { this.codePublisher = codePublisher; }

    @Id
    @Column(name = "\"CodeID\"", length = 10)
    private String codeId;

    public String getCodeId() { return codeId; }

    public void setCodeId(String codeId) { this.codeId = codeId; }

    @Id
    @Column(name = "\"DivisionCode\"", length = 10)
    private String divisionCode;

    public String getDivisionCode() { return divisionCode; }

    public void setDivisionCode(String divisionCode) { this.divisionCode = divisionCode; }

    @Basic
    @Column(name = "\"CountryISOCode\"", length = 4)
    private String countryIsoCode;

    public String getCountryIsoCode() { return countryIsoCode; }

    public void setCountryIsoCode(String countryIsoCode) { this.countryIsoCode = countryIsoCode; }

    @Basic
    @Column(name = "\"ParentCodeID\"", length = 10, insertable = false, updatable = false)
    private String parentCodeId;

    public String getParentCodeId() { return parentCodeId; }

    public void setParentCodeId(String parentCodeId) { this.parentCodeId = parentCodeId; }

    @Basic
    @Column(name = "\"ParentDivisionCode\"", length = 10, insertable = false, updatable = false)
    private String parentDivisionCode;

    public String getParentDivisionCode() { return parentDivisionCode; }

    public void setParentDivisionCode(String parentDivisionCode) { this.parentDivisionCode = parentDivisionCode; }

    @Basic
    @Column(name = "\"AlternativeCode\"", length = 10)
    private String alternativeCode;

    public String getAlternativeCode() { return alternativeCode; }

    public void setAlternativeCode(String alternativeCode) { this.alternativeCode = alternativeCode; }

    @Basic
    @Column(name = "\"Area\"")
    private Integer area;

    public Integer getArea() { return area; }

    public void setArea(Integer area) { this.area = area; }

    @Basic
    @Column(name = "\"Population\"")
    private Long population;

    public Long getPopulation() { return population; }

    public void setPopulation(Long population) { this.population = population; }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(referencedColumnName = "\"CodePublisher\"", name = "\"CodePublisher\"", nullable = false, insertable = false, updatable = false),
            @JoinColumn(referencedColumnName = "\"CodeID\"", name = "\"ParentCodeID\"", nullable = false),
            @JoinColumn(referencedColumnName = "\"DivisionCode\"", name = "\"ParentDivisionCode\"", nullable = false)
    })
    private AdministrativeDivisionEntity parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<AdministrativeDivisionEntity> children = new ArrayList<>();

    public List<AdministrativeDivisionEntity> getChildren() { return children; }

    public void setChildren(List<AdministrativeDivisionEntity> children) { this.children = children; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrativeDivisionEntity that = (AdministrativeDivisionEntity) o;

        if (!Objects.equals(codePublisher, that.codePublisher)) return false;
        if (!Objects.equals(codeId, that.codeId)) return false;
        if (!Objects.equals(divisionCode, that.divisionCode)) return false;
        if (!Objects.equals(countryIsoCode, that.countryIsoCode)) return false;
        if (!Objects.equals(parentCodeId, that.parentCodeId)) return false;
        if (!Objects.equals(parentDivisionCode, that.parentDivisionCode)) return false;
        if (!Objects.equals(alternativeCode, that.alternativeCode)) return false;
        if (!Objects.equals(area, that.area)) return false;
        return Objects.equals(population, that.population);
    }

    @Override
    public int hashCode() {
        int result = codePublisher != null ? codePublisher.hashCode() : 0;
        result = 31 * result + (codeId != null ? codeId.hashCode() : 0);
        result = 31 * result + (divisionCode != null ? divisionCode.hashCode() : 0);
        result = 31 * result + (countryIsoCode != null ? countryIsoCode.hashCode() : 0);
        result = 31 * result + (parentCodeId != null ? parentCodeId.hashCode() : 0);
        result = 31 * result + (parentDivisionCode != null ? parentDivisionCode.hashCode() : 0);
        result = 31 * result + (alternativeCode != null ? alternativeCode.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        return result;
    }

}