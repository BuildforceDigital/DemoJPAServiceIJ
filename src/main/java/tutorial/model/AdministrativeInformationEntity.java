package tutorial.model;

import javax.persistence.*;

@Embeddable
public class AdministrativeInformationEntity {

    @Embedded
    @AttributeOverrides({

            @AttributeOverride(name = "by", column = @Column(name = "\"CreatedBy\"")),
            @AttributeOverride(name = "at", column = @Column(name = "\"CreatedAt\""))})
    private ChangeInformationEntity created;

    @Embedded
    @AttributeOverrides({

            @AttributeOverride(name = "by", column = @Column(name = "\"UpdatedBy\"")),
            @AttributeOverride(name = "at", column = @Column(name = "\"UpdatedAt\""))})
    private ChangeInformationEntity updated;

}