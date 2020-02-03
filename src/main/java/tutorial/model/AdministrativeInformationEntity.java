package tutorial.model;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class AdministrativeInformationEntity {

    @Embedded
    @AttributeOverrides({

            @AttributeOverride(name = "by", column = @Column(name = "\"CreatedBy\"")),
            @AttributeOverride(name = "at", column = @Column(name = "\"CreatedAt\""))})
    private ChangeInformationEntity created;

    public ChangeInformationEntity getCreated() {
        return created;
    }

    public void setCreated(ChangeInformationEntity created) {
        this.created = created;
    }

    @Embedded
    @AttributeOverrides({

            @AttributeOverride(name = "by", column = @Column(name = "\"UpdatedBy\"")),
            @AttributeOverride(name = "at", column = @Column(name = "\"UpdatedAt\""))})
    private ChangeInformationEntity updated;

    public ChangeInformationEntity getUpdated() {
        return updated;
    }

    public void setUpdated(ChangeInformationEntity updated) {
        this.updated = updated;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministrativeInformationEntity that = (AdministrativeInformationEntity) o;

        if (!Objects.equals(created, that.created)) return false;
        return updated != null ? updated.equals(that.updated) : that.updated == null;
    }

    @Override
    public int hashCode() {
        int result = created != null ? created.hashCode() : 0;
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }

}