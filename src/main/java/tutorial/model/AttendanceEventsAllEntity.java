package tutorial.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"AttendanceEventsAll\"", schema = "OLINGO")
public class AttendanceEventsAllEntity {

    @Id
    @Column(name = "\"Id\"", nullable = false) private int id;
    @Column(name = "\"ApprovalBy\"", length = 40) private String approvalBy;
    @Column(name = "\"ApprovalDateTime\"") private OffsetDateTime approvalDateTime;
    @Column(name = "\"CheckInDateTime\"", nullable = false) private OffsetDateTime checkInDateTime;
    @Column(name = "\"CheckOutDateTime\"") private OffsetDateTime checkOutDateTime;
    @Column(name = "\"Description\"", length = 160) private String description;
    @Column(name = "\"ParentGuid\"") private int parentGuid;
    @Column(name = "\"ProjectCode\"", nullable = false, length = 10) private String projectCode;
    @Column(name = "\"ProjOwner\"", nullable = false, length = 36) private String projOwner;
    @Column(name = "\"Remarks\"", length = 480) private String remarks;
    @Column(name = "\"StartDate\"") private OffsetDateTime startDate;
    @Column(name = "\"TermGuidIn\"", nullable = false, length = 36) private String termGuidIn;
    @Column(name = "\"TermGuidOut\"", length = 36) private String termGuidOut;
    @Column(name = "\"UserName\"", nullable = false, length = 40) private String userName;


    public AttendanceEventsAllEntity(final int key) {
        id = key;
    }
    

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "\"Id\"", name = "\"Id\"", nullable = false,
            insertable = false, updatable = false)
    private AttendanceEventsAllEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttendanceEventsAllEntity> children = new ArrayList<>();

    public AttendanceEventsAllEntity() {
        // required for JPA
    }

    @PostPersist
    @PostUpdate
    public void adjustParent() {
        for (AttendanceEventsAllEntity child : children) {
            child.setParent(this);
        }
    }

    public AttendanceEventsAllEntity getParent() {
        return parent;
    }

    public void setChildren(List<AttendanceEventsAllEntity> children) {
        this.children = children;
    }

    public void setParentKey(int parentKey) {
        this.parentGuid = parentKey;
    }

    public void setParent(AttendanceEventsAllEntity parent) {
        this.parent = parent;
        this.parentGuid = parent.getParentGuid();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(int parentGuid) {
        this.parentGuid = parentGuid;
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

    public List<AttendanceEventsAllEntity> getChildren() {
        return children;
    }
}