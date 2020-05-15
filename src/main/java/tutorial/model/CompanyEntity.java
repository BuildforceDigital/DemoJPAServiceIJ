package tutorial.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Company")
@DiscriminatorValue(value = "2")
public class CompanyEntity extends BusinessPartnerEntity {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "\"AbcClass\"")
    private ABCClassification abcClass;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "OLINGO", name = "\"Comment\"",
            joinColumns = @JoinColumn(name = "\"BusinessPartnerID\""))
    @Column(name = "\"Text\"")
    private List<String> comment = new ArrayList<>();

    @Basic
    @Column(name = "\"NameLine1\"", length = 250)
    private String nameLine1;

    public String getNameLine1() {
        return nameLine1;
    }

    public void setNameLine1(String nameLine1) {
        this.nameLine1 = nameLine1;
    }

    @Basic
    @Column(name = "\"NameLine2\"", length = 250)
    private String nameLine2;

    public String getNameLine2() {
        return nameLine2;
    }

    public void setNameLine2(String nameLine2) {
        this.nameLine2 = nameLine2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (!Objects.equals(nameLine1, that.nameLine1)) return false;
        return Objects.equals(nameLine2, that.nameLine2);
    }

    @Override
    public int hashCode() {
        int result = nameLine1 != null ? nameLine1.hashCode() : 0;
        result = 31 * result + (nameLine2 != null ? nameLine2.hashCode() : 0);
        return result;
    }

}