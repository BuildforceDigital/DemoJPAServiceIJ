package tutorial.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;
import java.util.Objects;

@Entity(name = "Person")
@DiscriminatorValue(value = "1")
public class PersonEntity extends BusinessPartnerEntity {

    @Convert(converter = AccessRightsConverter.class)
    @Column(name = "\"AccessRights\"")
    private AccessRights[] accessRights;

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

    @Basic
    @Column(name = "\"BirthDay\"")
    private Date birthDay;
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity personEntity = (PersonEntity) o;

        if (!Objects.equals(nameLine1, personEntity.nameLine1)) return false;
        if (!Objects.equals(nameLine2, personEntity.nameLine2)) return false;
        return Objects.equals(birthDay, personEntity.birthDay);
    }

    @Override
    public int hashCode() {
        int result = nameLine1 != null ? nameLine1.hashCode() : 0;
        result = 31 * result + (nameLine2 != null ? nameLine2.hashCode() : 0);
        result = 31 * result + (birthDay != null ? birthDay.hashCode() : 0);
        return result;
    }

}