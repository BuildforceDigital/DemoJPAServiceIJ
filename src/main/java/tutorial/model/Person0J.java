package tutorial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "person0", schema = "OLINGO")
public class Person0J {
    public Person0J() {// Needed to be used as IdClass for java.lang.reflect.Constructor.newInstance
    }

    public Person0J(UUID _Id){
        userName = _Id;
    }

    @Id
    @Column(name = "userName")
    @Convert(converter = UUIDAttributeConverter.class)
    private UUID userName;

    @Column(name = "firstName", nullable = false, length = 20)
    String firstName;

    @Column(name = "lastName", nullable = false, length = 20)
    String lastName;

    public UUID getUserName() {
        return userName;
    }

    public void setUserName(UUID userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
