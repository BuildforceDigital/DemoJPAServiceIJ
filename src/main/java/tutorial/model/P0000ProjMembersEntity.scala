package tutorial.model

import jakarta.persistence.{Basic, Column, Entity, Id, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.annotation.meta.field

@Entity
@PrimaryKey (validation = IdValidation.NULL)
@Table(name = "\"P0000ProjMembersView\"", schema = "OLINGO")
class P0000ProjMembersEntity {
  @(Id@field)
  @Column(name = "\"P0000ProjectId\"", nullable = false)
  private var p0000ProjectId: Integer = _

  @(Id@field)
  @Column(name = "\"A0000UserId\"", nullable = false)
  private var a0000UserId: Integer = _

  @Basic
  @Column(name = "\"FullName\"", length = 36)
  private var fullName: String = _

  @Basic
  @Column(name = "\"Nickname\"", length = 36)
  private var nickname: String = _

  @Basic
  @Column(name = "\"UserName\"", length = 36)
  private var userName: String = _

  @Basic
  @Column(name = "\"Gender\"", length = 1)
  private var gender: String = _

  def getP0000ProjectId: Integer = p0000ProjectId

  def setP0000ProjectId(id: Integer): Unit = {
    this.p0000ProjectId = id
  }

  def getA0000UserId: Integer = a0000UserId

  def setA0000UserId(id: Integer): Unit = {
    this.a0000UserId = id
  }

  def getFullName: String = fullName

  def setFullName(id: String): Unit = {
    this.fullName = id
  }

  def getNickname: String = nickname

  def setNickname(nickname: String): Unit = {
    this.nickname = nickname
  }

  def getUserName: String = userName

  def setUserName(id: String): Unit = {
    this.userName = id
  }

  def getGender: String = gender

  def setGender(id: String): Unit = {
    this.gender = id
  }


}