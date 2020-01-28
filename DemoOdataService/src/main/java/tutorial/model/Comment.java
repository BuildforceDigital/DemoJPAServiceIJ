package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

import javax.persistence.*;
import java.sql.Clob;

@EdmIgnore
@Entity
@Table(schema = "\"OLINGO\"", name = "\"Comment\"")
public class Comment {

    @Id
    @Column(name = "\"BusinessPartnerID\"")
    private String businessPartnerID;

    @Column(name = "\"Order\"")
    private String order;

    @Lob
    @Column(name = "\"Text\"")
    @Basic(fetch = FetchType.LAZY)
    private Clob text;

    public Comment() {
        super();
    }

    public String getBusinessPartnerID() {
        return this.businessPartnerID;
    }

    public void setID(String ID) {
        this.businessPartnerID = ID;
    }
}