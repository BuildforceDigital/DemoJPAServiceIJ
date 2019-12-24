package tutorial.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChangeInformation {

    @Column(name = "\"by\"")
    private String by;

   @Column(precision = 6)
    private Timestamp at;
}