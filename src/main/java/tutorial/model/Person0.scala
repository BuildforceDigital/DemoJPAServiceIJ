package tutorial.model

import jakarta.persistence.{Column,Convert, Entity, Id, Table}
import java.util.UUID

@Entity
@Table(name = "person0", schema = "OLINGO")
class Person0() // Needed to be used as IdClass for java.lang.reflect.Constructor.newInstance
{
  @Id
  @Column(name = "userName")
  @Convert(converter = classOf[UUIDAttributeConverter])
  private var userName: UUID = _

  @Column(name = "firstName", nullable = false, length = 20)
  private[model] var firstName: String = _

  @Column(name = "lastName", nullable = false, length = 20)
  private[model] var lastName: String = _

  def this(_Id: UUID) = {
    this()
    userName = _Id
  }

  def getUserName: UUID = userName

  def setUserName(userName: UUID): Unit = this.userName = userName

  def getFirstName: String = firstName

  def setFirstName(firstName: String): Unit = this.firstName = firstName

  def getLastName: String = lastName

  def setLastName(lastName: String): Unit = this.lastName = lastName
}