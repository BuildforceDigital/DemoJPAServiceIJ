package tutorial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import org.eclipse.persistence.annotations.IdValidation;
import org.eclipse.persistence.annotations.PrimaryKey;
import realdeal.model.A0000UsersEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = "\"AttendanceEventsAll\"", schema = "OLINGO")
public class AttendanceEventsAllEntity {

    @Id
    @Column(name = "ID") private
    Integer id;

    @Column(name = "\"ApprovalBy\"", length = 40)

    private String approvalBy;

    public A0000UsersEntity getHourWriter() {
        return hourWriter;
    }

    public void setHourWriter(A0000UsersEntity hourWriter) {
        this.hourWriter = hourWriter;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(/*referencedColumnName = "ID",*/ name = "\"UserID\"", nullable = false, insertable = false, updatable = false)
    private A0000UsersEntity hourWriter;

    @Column(name = "\"UserID\"", nullable = false)
    @Convert(converter = UUIDAttributeConverter.class)
    private java.util.UUID userID;

    @Column(name = "\"ApprovalDateTime\"") private OffsetDateTime approvalDateTime;
    @Column(name = "\"CheckInDateTime\"", nullable = false) private OffsetDateTime checkInDateTime;
    @Column(name = "\"CheckOutDateTime\"") private OffsetDateTime checkOutDateTime;
    @Column(name = "\"Description\"", length = 160) private String description;
    @Column(name = "\"ProjectCode\"", nullable = false, length = 10) private String projectCode;
    @Column(name = "\"ProjOwner\"", nullable = false, length = 36) private String projOwner;
    @Column(name = "\"Remarks\"", length = 480) private String remarks;
    @Column(name = "\"StartDate\"") private OffsetDateTime startDate;
    @Column(name = "\"TermGuidIn\"", nullable = false, length = 36) private String termGuidIn;
    @Column(name = "\"TermGuidOut\"", length = 36) private String termGuidOut;

    @Column(name = "\"UserName\"", nullable = false, length = 40)
    private String userName;

/*
    public AttendanceEventsAllEntity(final Integer key) {
        id = key;
    }
*/

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public AttendanceEventsAllEntity() {
        // required for JPA
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
    }

    public OffsetDateTime getApprovalDateTime() {
        return approvalDateTime;
    }

    public void setApprovalDateTime(OffsetDateTime approvalDateTime) {
        this.approvalDateTime = approvalDateTime;
    }

    public OffsetDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(OffsetDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public OffsetDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(OffsetDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjOwner() {
        return projOwner;
    }

    public void setProjOwner(String projOwner) {
        this.projOwner = projOwner;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTermGuidIn() {
        return termGuidIn;
    }

    public void setTermGuidIn(String termGuidIn) {
        this.termGuidIn = termGuidIn;
    }

    public String getTermGuidOut() {
        return termGuidOut;
    }

    public void setTermGuidOut(String termGuidOut) {
        this.termGuidOut = termGuidOut;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}