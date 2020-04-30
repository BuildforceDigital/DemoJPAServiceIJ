package tutorial.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

@Entity(name = "AdministrativeDivision")
@IdClass(AdministrativeDivisionEntityPK.class)
@Table(name = "\"AdministrativeDivision\"", schema = "OLINGO")
public class AdministrativeDivisionEntity {

    @Id
    @Column(name = "\"CodePublisher\"", length = 10)
    private String codePublisher;
    @Id
    @Column(name = "\"CodeID\"", length = 10)
    private String codeId;
    @Id
    @Column(name = "\"DivisionCode\"", length = 10)
    private String divisionCode;

    @Column(name = "\"CountryISOCode\"", length = 4)
    private String countryIsoCode;
    @Column(name = "\"ParentCodeID\"", length = 10)
    private String parentCodeId;
    @Column(name = "\"ParentDivisionCode\"", length = 10)
    private String parentDivisionCode;
    @Column(name = "\"AlternativeCode\"", length = 10)
    private String alternativeCode;
    @Column(name = "\"Area\"") // , precision = 34, scale = 0)
    private Integer area = 0;
    @Column(name = "\"Population\"", precision = 34)
    private long population;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "\"CodePublisher\"", name = "\"CodePublisher\"", nullable = false,
            insertable = false, updatable = false)
    @JoinColumn(referencedColumnName = "\"CodeID\"", name = "\"ParentCodeID\"", nullable = false,
            insertable = false, updatable = false)
    @JoinColumn(referencedColumnName = "\"DivisionCode\"", name = "\"ParentDivisionCode\"", nullable = false,
            insertable = false, updatable = false)
    private AdministrativeDivisionEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AdministrativeDivisionEntity> children = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"CodePublisher\"", referencedColumnName = "\"CodePublisher\"", insertable = false,
            updatable = false)
    @JoinColumn(name = "\"CodeID\"", referencedColumnName = "\"CodeID\"", insertable = false, updatable = false)
    @JoinColumn(name = "\"DivisionCode\"", referencedColumnName = "\"DivisionCode\"", insertable = false,
            updatable = false)
    private List<AdministrativeDivisionDescriptionEntity> allDescriptions;

    public AdministrativeDivisionEntity() {
        // required for JPA
    }

    public AdministrativeDivisionEntity(final AdministrativeDivisionEntityPK key) {
        codePublisher = key.getCodePublisher();
        codeId = key.getCodeId();
        divisionCode = key.getDivisionCode();
    }

    @PostPersist
    @PostUpdate
    public void adjustParent() {
        for (AdministrativeDivisionEntity child : children) {
            child.setParent(this);
        }
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public int getArea() {
        return area;
    }

    public List<AdministrativeDivisionEntity> getChildren() {
        return children;
    }

    public String getCodeId() {
        return codeId;
    }

    public String getCodePublisher() {
        return codePublisher;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public Object getKey() {
        return new AdministrativeDivisionEntityPK(codePublisher, codeId, divisionCode);
    }

    public AdministrativeDivisionEntity getParent() {
        return parent;
    }

    public String getParentCodeId() {
        return parentCodeId;
    }

    public String getParentDivisionCode() {
        return parentDivisionCode;
    }

    public long getPopulation() {
        return population;
    }

    public void setAlternativeCode(String alternativeCode) {
        this.alternativeCode = alternativeCode;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public void setChildren(List<AdministrativeDivisionEntity> children) {
        this.children = children;
    }

    public void setCodeId(String codeID) {
        this.codeId = codeID;
    }

    public void setCodePublisher(String codePublisher) {
        this.codePublisher = codePublisher;
    }

    public void setCountryIsoCode(String countryCode) {
        this.countryIsoCode = countryCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public void setParent(AdministrativeDivisionEntity parent) {
        this.parent = parent;
        this.parentCodeId = parent.getCodeId();
        this.parentDivisionCode = parent.getDivisionCode();

    }

    public void setParentCodeId(String parentCodeID) {
        this.parentCodeId = parentCodeID;
    }

    public void setParentDivisionCode(String parentDivisionCode) {
        this.parentDivisionCode = parentDivisionCode;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeId == null) ? 0 : codeId.hashCode());
        result = prime * result + ((codePublisher == null) ? 0 : codePublisher.hashCode());
        result = prime * result + ((divisionCode == null) ? 0 : divisionCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AdministrativeDivisionEntity other = (AdministrativeDivisionEntity) obj;
        if (codeId == null) {
            if (other.codeId != null) return false;
        } else if (!codeId.equals(other.codeId)) return false;
        if (codePublisher == null) {
            if (other.codePublisher != null) return false;
        } else if (!codePublisher.equals(other.codePublisher)) return false;
        if (divisionCode == null) {
            return other.divisionCode == null;
        } else return divisionCode.equals(other.divisionCode);
    }
}