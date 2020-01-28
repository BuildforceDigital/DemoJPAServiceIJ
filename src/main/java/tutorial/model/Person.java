package tutorial.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.sql.Date;

@Entity(name = "Person")
@DiscriminatorValue(value = "1")
public class Person extends BusinessPartner {

    @Column(name = "\"NameLine1\"")
    private String firstName;

    @Column(name = "\"NameLine2\"")
    private String lastName;

    @Column(name = "\"BirthDay\"")
    private Date birthDay;

    @Convert(converter = AccessRightsConverter.class)
    @Column(name = "\"AccessRights\"")
    private AccessRights[] accessRights;
}