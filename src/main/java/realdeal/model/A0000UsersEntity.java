package realdeal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tutorial.model.AttendanceEventsAllEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "A0000USERS", schema = "DEV_GREENTRAK00")
public class A0000UsersEntity {
    public A0000UsersEntity() {
    }

    @Id
    @Convert(converter = tutorial.model.UUIDAttributeConverter.class)
    @Column(name = "ID")
    private java.util.UUID id;

    @Column(name = "\"BirthDay\"", length = 36)
    private String birthDay;

    @Column(name = "\"BusinessEmail\"", length = 160)
    private String businessEmail;

    @Column(name = "\"CitizenServiceNr\"", length = 36)
    private String citizenServiceNr;

    @Column(name = "\"FullName\"", nullable = false)
    private String fullName;

    @Column(name = "\"Gender\"")
    private String gender;

    @Column(name = "\"JobFunction\"", length = 40)
    private String jobFunction;

    @Column(name = "\"LandlinePhone\"")
    private String landlinePhone;

    @Column(name = "\"MobilePhone\"", length = 36)
    private String mobilePhone;

    @Column(name = "\"Nationality\"")
    private String nationality;

    @Column(name = "\"Nickname\"")
    private String nickname;

    @Column(name = "\"PrivateEmail\"", length = 160)
    private String privateEmail;

    @Column(name = "\"TillDate\"")
    private java.time.OffsetDateTime tillDate;

    @Column(name = "\"UserImage\"", length = 40)
    private String userImage;

    @Column(name = "\"UserName\"", length = 40)
    private String userName;


    //@BeanProperty
    @OneToMany(mappedBy = "hourWriter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttendanceEventsAllEntity> regHours = new ArrayList();


}