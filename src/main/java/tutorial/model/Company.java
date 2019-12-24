package tutorial.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Company")
@DiscriminatorValue(value = "2")
@Table(schema = "\"OLINGO\"", name = "\"BusinessPartner\"")
public class Company extends BusinessPartner {

    private static final long serialVersionUID = 1L;

    @Column(name = "\"NameLine1\"")
    private String name1;

    @Column(name = "\"NameLine2\"")
    private String name2;
}