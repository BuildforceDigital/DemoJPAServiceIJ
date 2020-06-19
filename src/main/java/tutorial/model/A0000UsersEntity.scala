package tutorial.model

import java.sql.Date
import java.time.OffsetDateTime

import jakarta.persistence.{Basic, Column, Entity, Id, Table}

@Entity
@Table(name = "\"A0000Users\"", schema = "OLINGO")
class A0000UsersEntity {
  @Id
  @Column(name = "\"Id\"", nullable = false) private var id = 0

  def getId: Int = id

  def setId(id: Int): Unit = {
    this.id = id
  }

  @Basic
  @Column(name = "\"Birthday\""/*, nullable = false*/, length = 36) private var birthday: String = _

  def getBirthday: String = birthday

  def setBirthday(projOwner: String): Unit = {
    this.birthday = projOwner
  }

  @Basic
  @Column(name = "\"BusinessEmail\"", /*nullable = false,*/ length = 10) private var businessEmail: String = _

  def getBusinessEmail: String = businessEmail

  def setBusinessEmail(projectCode: String): Unit = {
    this.businessEmail = projectCode
  }

  @Basic
  @Column(name = "\"CitizenServiceNr\"", /*nullable = false,*/ length = 36) private var citizenServiceNr: String = _

  def getCitizenServiceNr: String = citizenServiceNr

  def setCitizenServiceNr(termGuidIn: String): Unit = {
    this.citizenServiceNr = termGuidIn
  }

  @Basic
  @Column(name = "\"FullName\"", nullable = false) private var fullName: String = _

  def getFullName: String = fullName

  def setFullName(checkInDatetime: String): Unit = {
    this.fullName = checkInDatetime
  }

  @Basic
  @Column(name = "\"JobFunction\"", /*nullable = false,*/ length = 40) private var jobFunction: String = _

  def getJobFunction: String = jobFunction

  def setJobFunction(userName: String): Unit = {
    this.jobFunction = userName
  }

  @Basic
  @Column(name = "\"LandlinePhone\"") private var landlinePhone: String = _

  def getLandlinePhone: String = landlinePhone

  def setLandlinePhone(parentGuid: String): Unit = {
    this.landlinePhone = parentGuid
  }

  @Basic
  @Column(name = "\"MobilePhone\"", length = 36) private var mobilePhone: String = _

  def getMobilePhone: String = mobilePhone

  def setMobilePhone(termGuidOut: String): Unit = {
    this.mobilePhone = termGuidOut
  }

  @Basic
  @Column(name = "\"Nationality\"") private var nationality: String = _

  def getNationality: String = nationality

  def setNationality(checkOutDateTime: String): Unit = {
    this.nationality = checkOutDateTime
  }

  @Basic
  @Column(name = "\"Gender\"") private var gender: String = _

  def getGender: String = nationality

  def setGender(checkOutDateTime: String): Unit = {
    this.gender = checkOutDateTime
  }

  @Basic
  @Column(name = "\"Nickname\"") private var nickname: String = _

  def getNickname: String = nickname

  def setNickname(startDate: String): Unit = {
    this.nickname = startDate
  }

  @Basic
  @Column(name = "\"PrivateEmail\"", length = 160) private var PrivateEmail: String = _

  def getPrivateEmail: String = PrivateEmail

  def setPrivateEmail(description: String): Unit = {
    this.PrivateEmail = description
  }

  @Basic
  @Column(name = "\"UserName\"", length = 40) private var userName: String = _

  def getUserName: String = userName

  def setUserName(approvalBy: String): Unit = {
    this.userName = approvalBy
  }

  @Basic
  @Column(name = "\"TillDate\"") private var tillDate: java.sql.Date = _

  def getTillDate: java.sql.Date = tillDate

  def setTillDate(approvalDateTime: java.sql.Date): Unit = {
    this.tillDate = approvalDateTime
  }

/*  @Basic
  @Column(name = "\"Remarks\"", length = 480) private var remarks: String = _

  def getRemarks: String = remarks

  def setRemarks(remarks: String): Unit = {
    this.remarks = remarks
  }*/

  override def hashCode: Int = {
    var result = id
    result = 31 * result + (if (birthday != null) birthday.hashCode
    else 0)
    result = 31 * result + (if (businessEmail != null) businessEmail.hashCode
    else 0)
    result = 31 * result + (if (citizenServiceNr != null) citizenServiceNr.hashCode
    else 0)
    result = 31 * result + (if (fullName != null) fullName.hashCode
    else 0)
    result = 31 * result + (if (jobFunction != null) jobFunction.hashCode
    else 0)
    result = 31 * result + (if (landlinePhone != null) landlinePhone.hashCode
    else 0)
    result = 31 * result + (if (mobilePhone != null) mobilePhone.hashCode
    else 0)
    result = 31 * result + (if (nationality != null) nationality.hashCode
    else 0)
    result = 31 * result + (if (nickname != null) nickname.hashCode
    else 0)
    result = 31 * result + (if (PrivateEmail != null) PrivateEmail.hashCode
    else 0)
    result = 31 * result + (if (userName != null) userName.hashCode
    else 0)
    result = 31 * result + (if (tillDate != null) tillDate.hashCode
    else 0)
    result
  }
}