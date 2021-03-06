package tutorial.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "\"AttendanceEventsAll\"", schema = "OLINGO")
public class AttendanceEventsAllEntity {

    @Id
    @Column(name = "\"Id\"", nullable = false)
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"ProjOwner\"", nullable = false, length = 36)
    private String projOwner;
    public String getProjOwner() {
        return projOwner;
    }

    public void setProjOwner(String projOwner) {
        this.projOwner = projOwner;
    }

    @Basic
    @Column(name = "\"ProjectCode\"", nullable = false, length = 10)
    private String projectCode;
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "\"TermGuidIn\"", nullable = false, length = 36)
    private String termGuidIn;
    public String getTermGuidIn() {
        return termGuidIn;
    }

    public void setTermGuidIn(String termGuidIn) {
        this.termGuidIn = termGuidIn;
    }

    @Basic
    @Column(name = "\"CheckInDateTime\"", nullable = false)
    private OffsetDateTime checkInDateTime;
    public OffsetDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(OffsetDateTime checkInDatetime) {
        this.checkInDateTime = checkInDatetime;
    }

    @Basic
    @Column(name = "\"UserName\"", nullable = false, length = 40)
    private String userName;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "\"ParentGuid\"")
    private Integer parentGuid;
    public Integer getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(Integer parentGuid) {
        this.parentGuid = parentGuid;
    }

    @Basic
    @Column(name = "\"TermGuidOut\"", length = 36)
    private String termGuidOut;
    public String getTermGuidOut() {
        return termGuidOut;
    }

    public void setTermGuidOut(String termGuidOut) {
        this.termGuidOut = termGuidOut;
    }

    @Basic
    @Column(name = "\"CheckOutDateTime\"")
    private OffsetDateTime checkOutDateTime;
    public OffsetDateTime getCheckOutDateTime() { return checkOutDateTime; }

    public void setCheckOutDateTime(OffsetDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    @Basic
    @Column(name = "\"StartDate\"")
    private Date startDate;
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "\"Description\"", length = 160)
    private String description;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "\"ApprovalBy\"", length = 40)
    private String approvalBy;
    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
    }

    @Basic
    @Column(name = "\"ApprovalDateTime\"")
    private OffsetDateTime approvalDateTime;
    public OffsetDateTime getApprovalDateTime() {
        return approvalDateTime;
    }

    public void setApprovalDateTime(OffsetDateTime approvalDateTime) {
        this.approvalDateTime = approvalDateTime;
    }

    @Basic
    @Column(name = "\"Remarks\"", length = 480)
    private String remarks;
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceEventsAllEntity that = (AttendanceEventsAllEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(projOwner, that.projOwner)) return false;
        if (!Objects.equals(projectCode, that.projectCode)) return false;
        if (!Objects.equals(termGuidIn, that.termGuidIn)) return false;
        if (!Objects.equals(checkInDateTime, that.checkInDateTime))
            return false;
        if (!Objects.equals(userName, that.userName)) return false;
        if (!Objects.equals(parentGuid, that.parentGuid)) return false;
        if (!Objects.equals(termGuidOut, that.termGuidOut)) return false;
        if (!Objects.equals(checkOutDateTime, that.checkOutDateTime))
            return false;
        if (!Objects.equals(startDate, that.startDate)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(approvalBy, that.approvalBy)) return false;
        if (!Objects.equals(approvalDateTime, that.approvalDateTime))
            return false;
        if (!Objects.equals(remarks, that.remarks)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projOwner != null ? projOwner.hashCode() : 0);
        result = 31 * result + (projectCode != null ? projectCode.hashCode() : 0);
        result = 31 * result + (termGuidIn != null ? termGuidIn.hashCode() : 0);
        result = 31 * result + (checkInDateTime != null ? checkInDateTime.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (parentGuid != null ? parentGuid.hashCode() : 0);
        result = 31 * result + (termGuidOut != null ? termGuidOut.hashCode() : 0);
        result = 31 * result + (checkOutDateTime != null ? checkOutDateTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (approvalBy != null ? approvalBy.hashCode() : 0);
        result = 31 * result + (approvalDateTime != null ? approvalDateTime.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        return result;
    }
}