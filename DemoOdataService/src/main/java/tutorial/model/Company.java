package tutorial.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Company")
@DiscriminatorValue(value = "2")
public class Company extends BusinessPartner {

    @Column(name = "\"NameLine1\"")
    private String name1;

    @Column(name = "\"NameLine2\"")
    private String name2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "\"ABCClass\"")
    private ABCClassification abcClass;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(schema = "\"OLINGO\"", name = "\"Comment\"", joinColumns = @JoinColumn(name = "\"BusinessPartnerID\""))
    @Column(name = "\"Text\"")
    private List<String> comment = new ArrayList<>();

}