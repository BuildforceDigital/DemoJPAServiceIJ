package tutorial.model;

import java.sql.Date;

import javax.persistence.*;

@Entity(name = "Person")
@DiscriminatorValue(value = "1")
@Table(schema = "\"OLINGO\"", name = "\"BusinessPartner\"")
public class Person extends BusinessPartner {

    private static final long serialVersionUID = 1L;

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