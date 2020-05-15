package tutorial.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}