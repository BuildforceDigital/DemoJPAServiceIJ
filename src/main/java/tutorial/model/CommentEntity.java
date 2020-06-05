package tutorial.model;

import java.sql.Clob;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import nl.buildforce.sequoia.jpa.metadata.core.edm.annotation.EdmIgnore;

@EdmIgnore
@Entity
@Table(name = "\"Comment\"", schema = "OLINGO")
public class CommentEntity {
    @Id
    @Column(name = "\"BusinessPartnerID\"", nullable = false, length = 32)
    private String businessPartnerId;

    public String getBusinessPartnerId() { return businessPartnerId; }

    public void setBusinessPartnerId(String businessPartnerId) { this.businessPartnerId = businessPartnerId; }

    /*@Id*/
    @Column(name = "\"Order\"", nullable = false)
    private int order;

    public int getOrder() { return order; }

    public void setOrder(int order) { this.order = order; }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "\"Text\"")
    @Lob
    private Clob text;

    public Clob getText() { return text; }

    public void setText(Clob text) { this.text = text; }

    public CommentEntity() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (order != that.order) return false;
        if (!Objects.equals(businessPartnerId, that.businessPartnerId)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = businessPartnerId != null ? businessPartnerId.hashCode() : 0;
        result = 31 * result + order;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

}