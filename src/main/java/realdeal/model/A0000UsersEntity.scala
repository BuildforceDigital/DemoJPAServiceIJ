package realdeal.model

import java.{util => ju}

import jakarta.persistence.{Basic, CascadeType, Column, Entity, GeneratedValue, GenerationType, Id, OneToMany, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.beans.BeanProperty

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = A0000UsersEntity.tableName, schema = A0000UsersEntity.schemaName)
class A0000UsersEntity(@BeanProperty val birthday: String) {
  // Use Java collection types instead of Scala ones to make JPA happy

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id = 0L

/*
  @Basic
  @Column(name = "\"Birthday\"", length = 36)
  private var birthday: String = _


  @Basic
  @Column(name = "\"BusinessEmail\"", length = 10)
  private var businessEmail: String = _

  @Basic
  @Column(name = "\"CitizenServiceNr\"", length = 36)
  private var citizenServiceNr: String = _

  @Basic
  @Column(name = "\"FullName\"", nullable = false)
  private var fullName: String = _

  @Basic
  @Column(name = "\"JobFunction\"", length = 40)
  private var jobFunction: String = _

  @Basic
  @Column(name = "\"LandlinePhone\"")
  private var landlinePhone: String = _

  @Basic
  @Column(name = "\"MobilePhone\"", length = 36)
  private var mobilePhone: String = _

  @Basic
  @Column(name = "\"Nationality\"")
  private var nationality: String = _

  @Basic
  @Column(name = "\"Gender\"")
  private var gender: String = _

  @Basic
  @Column(name = "\"Nickname\"")
  private var nickname: String = _

  @Basic
  @Column(name = "\"PrivateEmail\"", length = 160)
  private var PrivateEmail: String = _

  @Basic
  @Column(name = "\"UserName\"", length = 40)
  private var userName: String = _

  @Basic
  @Column(name = "\"TillDate\"")
  private var tillDate: java.time.OffsetDateTime = _

  @Basic
  @Column(name = "\"UserImage\"", length = 40)
  private var userImage: String = _

  def getBirthday: String = birthday

  def setBirthday(projOwner: String): Unit = {
    this.birthday = projOwner
  }

  def getBusinessEmail: String = businessEmail

  def setBusinessEmail(projectCode: String): Unit = {
    this.businessEmail = projectCode
  }

  def getCitizenServiceNr: String = citizenServiceNr

  def setCitizenServiceNr(termGuidIn: String): Unit = {
    this.citizenServiceNr = termGuidIn
  }

  def getFullName: String = fullName

  def setFullName(checkInDatetime: String): Unit = {
    this.fullName = checkInDatetime
  }

  def getJobFunction: String = jobFunction

  def setJobFunction(userName: String): Unit = {
    this.jobFunction = userName
  }

  def getLandlinePhone: String = landlinePhone

  def setLandlinePhone(parentGuid: String): Unit = {
    this.landlinePhone = parentGuid
  }

  def getMobilePhone: String = mobilePhone

  def setMobilePhone(termGuidOut: String): Unit = {
    this.mobilePhone = termGuidOut
  }

  def getNationality: String = nationality

  def setNationality(checkOutDateTime: String): Unit = {
    this.nationality = checkOutDateTime
  }

  def getGender: String = nationality

  def setGender(checkOutDateTime: String): Unit = {
    this.gender = checkOutDateTime
  }

  def getNickname: String = nickname

  def setNickname(startDate: String): Unit = {
    this.nickname = startDate
  }

  def getPrivateEmail: String = PrivateEmail

  def setPrivateEmail(description: String): Unit = {
    this.PrivateEmail = description
  }

  def getUserName: String = userName

  def setUserName(approvalBy: String): Unit = {
    this.userName = approvalBy
  }

  def getUserImage: String = userImage

  def setUserImage(approvalBy: String): Unit = {
    this.userImage = approvalBy
  }

  def getTillDate: java.time.OffsetDateTime = tillDate

  def setTillDate(approvalDateTime: java.time.OffsetDateTime): Unit = {
    this.tillDate = approvalDateTime
  }
*/
  // Default constructor for persistence providers
  // No public visibility required
  private def this() = this(null)

}

object A0000UsersEntity {
  final val tableName = "A0000USERS"
  final val schemaName ="DEV_GREENTRAK00"
}
