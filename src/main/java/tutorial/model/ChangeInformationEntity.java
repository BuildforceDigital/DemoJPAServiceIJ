package tutorial.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.Objects;

@Embeddable
public class ChangeInformationEntity {
    @Basic
    @Column(nullable = false, length = 32)
    private String by;

    public String getBy() { return by; }

    public void setBy(String by) { this.by = by; }

    @Basic
    @Column(precision = 6)
    private Timestamp at;

    public Timestamp getAt() { return at; }

    public void setAt(Timestamp at) { this.at = at; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeInformationEntity that = (ChangeInformationEntity) o;

        if (!Objects.equals(by, that.by)) return false;
        return Objects.equals(at, that.at);
    }

    @Override
    public int hashCode() {
        int result = by != null ? by.hashCode() : 0;
        result = 31 * result + (at != null ? at.hashCode() : 0);
        return result;
    }

}