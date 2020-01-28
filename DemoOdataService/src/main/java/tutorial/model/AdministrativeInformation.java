package tutorial.model;

import javax.persistence.*;

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