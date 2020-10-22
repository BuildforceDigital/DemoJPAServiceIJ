package realdeal.model

import java.{util => ju}

import jakarta.persistence.{CascadeType, Column, Convert, Entity, FetchType, Id, OneToMany, Table}
import realdeal.model.A0000UsersEntity._Column
import tutorial.model.{AttendanceEventsAllEntity, UUIDAttributeConverter}

import scala.annotation.meta.{beanGetter, field}
import scala.beans.BeanProperty

@Entity
@Table(name = A0000UsersEntity.tableName, schema = A0000UsersEntity.schemaName)
class A0000UsersEntity {

  @(Id@field)
  @Convert(converter = classOf[UUIDAttributeConverter])
  @(_Column@field)(name = "ID")
  @BeanProperty
  var id: ju.UUID = _

  // Use Java collection types instead of Scala ones to make JPA happy
  //@OneToMany(mappedBy = A0000UsersEntity.tableName, cascade = Array(CascadeType.ALL), orphanRemoval = true)
  //@BeanProperty
  //final val children: ju.List[AttendanceEventsAllEntity] = new ju.ArrayList[AttendanceEventsAllEntity]

  @(_Column@field)(name = "\"BirthDay\"", length = 36)
  @BeanProperty
  var birthDay: String = _

  @(_Column@field)(name = "\"BusinessEmail\"", length = 160)
  @BeanProperty
  var businessEmail: String = _

  @(_Column@field)(name = "\"CitizenServiceNr\"", length = 36)
  @BeanProperty
  var citizenServiceNr: String = _

  @(_Column@field)(name = "\"FullName\"", nullable = false)
  @BeanProperty
  var fullName: String = _

  @(_Column@field)(name = "\"Gender\"")
  @BeanProperty
  var gender: String = _

  @(_Column@field)(name = "\"JobFunction\"", length = 40)
  @BeanProperty
  var jobFunction: String = _

  @(_Column@field)(name = "\"LandlinePhone\"")
  @BeanProperty
  var landlinePhone: String = _

  @(_Column@field)(name = "\"MobilePhone\"", length = 36)
  @BeanProperty
  var mobilePhone: String = _

  @(_Column@field)(name = "\"Nationality\"")
  @BeanProperty
  var nationality: String = _

  @(_Column@field)(name = "\"Nickname\"")
  @BeanProperty
  var nickname: String = _

  @(_Column@field)(name = "\"PrivateEmail\"", length = 160)
  @BeanProperty
  var privateEmail: String = _

  @(_Column@field)(name = "\"TillDate\"")
  @BeanProperty
  var tillDate: java.time.OffsetDateTime = _

  @(_Column@field)(name = "\"UserImage\"", length = 40)
  @BeanProperty
  var userImage: String = _

  @(_Column@field)(name = "\"UserName\"", length = 40)
  @BeanProperty
  var userName: String = _


  @BeanProperty
  @(OneToMany@field)(mappedBy = "hourWriter", fetch = FetchType.LAZY, cascade = Array(CascadeType.ALL))
  var regHours: ju.ArrayList[AttendanceEventsAllEntity] = new ju.ArrayList


}

object A0000UsersEntity {
  type _Column = Column@beanGetter
  final val schemaName= "DEV_GREENTRAK00"
  final val tableName = "A0000USERS"
}