package tutorial.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "OLINGO", name = "\"AdministrativeDivisionDescription\"")
public class AdministrativeDivisionDescription {

    @EmbeddedId
    private AdministrativeDivisionDescriptionKey key;

    @Column(name = "\"Description\"", length = 100)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}