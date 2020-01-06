package tutorial.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;

@Embeddable
public class AdministrativeInformation {

    @Embedded
    @AttributeOverride(name = "by", column = @Column(name = "\"CreatedBy\""))
    @AttributeOverride(name = "at", column = @Column(name = "\"CreatedAt\""))
    @AssociationOverride(name = "user", joinColumns = @JoinColumn(referencedColumnName = "\"ID\"", name = "\"CreatedBy\"", insertable = false, updatable = false))
    private ChangeInformation created;

    @Embedded
    @AttributeOverride(name = "by", column = @Column(name = "\"UpdatedBy\""))
    @AttributeOverride(name = "at", column = @Column(name = "\"UpdatedAt\""))
    @AssociationOverride(name = "user", joinColumns = @JoinColumn(referencedColumnName = "\"ID\"", name = "\"UpdatedBy\"", insertable = false, updatable = false))
    private ChangeInformation updated;
}