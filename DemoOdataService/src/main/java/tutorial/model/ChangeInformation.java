package tutorial.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Embeddable
public class ChangeInformation {

    @Column
    private String by;

   @Column(precision = 6)
    private Timestamp at;

    @ManyToOne
    @JoinColumn(name = "\"by\"", referencedColumnName = "\"iD\"", insertable = false, updatable = false)
    Person user;
}