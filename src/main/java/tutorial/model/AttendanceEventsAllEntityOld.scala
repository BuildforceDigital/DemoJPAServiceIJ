package tutorial.model

import java.time.OffsetDateTime

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

import scala.annotation.meta.field

/*@EdmFunctions ( {
  @EdmFunction(
    name = "SiblingsBound",
    functionName = "\"OLINGO\".\"Siblings\"",
    returnType = @EdmFunction.ReturnType(isCollection = true),
  parameter = {
    @EdmParameter(name = "CodePublisher", parameterName = "\"Publisher\"",
    type = String.class, maxLength = 10),
    @EdmParameter(name = "CodeID", parameterName = "\"ID\"", type = String.class, maxLength = 10)
     })

})*/
@Entity
@Table(name = "\"AttendanceEventsAllOld\"", schema = "OLINGO")
class AttendanceEventsAllEntityOld {

  @(Id@field)
  @Column(name = "\"Id\"")
  private var id = 0

  def getId: Int = id

  def setId(id: Int): Unit = {
    this.id = id
  }

  @Basic
  @Column(name = "\"ProjOwner\"", nullable = false, length = 36) private var projOwner: String = _

  def getProjOwner: String = projOwner

  def setProjOwner(projOwner: String): Unit = {
    this.projOwner = projOwner
  }

  @Basic
  @Column(name = "\"ProjectCode\"", nullable = false, length = 10) private var projectCode: String = _

  def getProjectCode: String = projectCode

  def setProjectCode(projectCode: String): Unit = {
    this.projectCode = projectCode
  }

  @Basic
  @Column(name = "\"TermGuidIn\"", nullable = false, length = 36) private var termGuidIn: String = _

  def getTermGuidIn: String = termGuidIn

  def setTermGuidIn(termGuidIn: String): Unit = {
    this.termGuidIn = termGuidIn
  }

  @Basic
  @Column(name = "\"CheckInDateTime\"", nullable = false) private var checkInDateTime: OffsetDateTime = _

  def getCheckInDateTime: OffsetDateTime = checkInDateTime

  def setCheckInDateTime(checkInDatetime: OffsetDateTime): Unit = {
    this.checkInDateTime = checkInDatetime
  }

  @Basic
  @Column(name = "\"UserName\"", nullable = false, length = 40) private var userName: String = _

  def getUserName: String = userName

  def setUserName(userName: String): Unit = {
    this.userName = userName
  }

  @Basic
  @Column(name = "\"ParentGuid\"") private var parentGuid: Integer = _

  def getParentGuid: Integer = parentGuid

  def setParentGuid(parentGuid: Integer): Unit = {
    this.parentGuid = parentGuid
  }

  @Basic
  @Column(name = "\"TermGuidOut\"", length = 36) private var termGuidOut: String = _

  def getTermGuidOut: String = termGuidOut

  def setTermGuidOut(termGuidOut: String): Unit = {
    this.termGuidOut = termGuidOut
  }

  @Basic
  @Column(name = "\"CheckOutDateTime\"") private var checkOutDateTime: OffsetDateTime = _

  def getCheckOutDateTime: OffsetDateTime = checkOutDateTime

  def setCheckOutDateTime(checkOutDateTime: OffsetDateTime): Unit = {
    this.checkOutDateTime = checkOutDateTime
  }

  @Basic
  @Column(name = "\"StartDate\"") private var startDate: OffsetDateTime = _

  def getStartDate: OffsetDateTime = startDate

  def setStartDate(startDate: OffsetDateTime): Unit = {
    this.startDate = startDate
  }

  @Basic
  @Column(name = "\"Description\"", length = 160) private var description: String = _

  def getDescription: String = description

  def setDescription(description: String): Unit = {
    this.description = description
  }

  @Basic
  @Column(name = "\"ApprovalBy\"", length = 40) private var approvalBy: String = _

  def getApprovalBy: String = approvalBy

  def setApprovalBy(approvalBy: String): Unit = {
    this.approvalBy = approvalBy
  }

  @Basic
  @Column(name = "\"ApprovalDateTime\"") private var approvalDateTime: OffsetDateTime = _

  def getApprovalDateTime: OffsetDateTime = approvalDateTime

  def setApprovalDateTime(approvalDateTime: OffsetDateTime): Unit = {
    this.approvalDateTime = approvalDateTime
  }

  @Basic
  @Column(name = "\"Remarks\"", length = 480) private var remarks: String = _

  def getRemarks: String = remarks

  def setRemarks(remarks: String): Unit = {
    this.remarks = remarks
  }

}