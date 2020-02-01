package tutorial.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"AdministrativeDivisionDescription\"", schema = "OLINGO")
public class AdministrativeDivisionDescriptionEntity {

    @EmbeddedId
    private AdministrativeDivisionDescriptionEntityPK key;

    @Basic
    @Column(name = "\"Description\"", nullable = false, length = 100)
    private String description;

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrativeDivisionDescriptionEntity that = (AdministrativeDivisionDescriptionEntity) o;

        if (!Objects.equals(key, that.key)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    /*@ManyToOne
    @JoinColumns({@JoinColumn(name = "LanguageISO", referencedColumnName = "LanguageISO",
            nullable = false), @JoinColumn(name = "DivisionCode", referencedColumnName = "Country", nullable = false)})
    private BusinessPartnerEntity businessPartner;

    public BusinessPartnerEntity getBusinessPartner() {
        return businessPartner;
    }

    public void setBusinessPartner(BusinessPartnerEntity businessPartner) {
        this.businessPartner = businessPartner;
    }*/
}