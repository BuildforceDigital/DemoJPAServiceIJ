package tutorial.model

import jakarta.persistence.{Column, Convert, Entity, Id, Table}
import java.util.UUID

import tutorial.model.Person0Old._Column

import scala.annotation.meta.{beanGetter, field}
import scala.beans.BeanProperty

@Entity
@Table(name = "person0", schema = "OLINGO")
class Person0Old( @(_Column@field)(name = "firstName", nullable = false, length = 20)
               @BeanProperty val firstName: String,
               @(_Column@field)(name = "lastName", nullable = false, length = 20)
               @BeanProperty val lastName: String)
// Needed to be used as IdClass for java.lang.reflect.Constructor.newInstance
{
  @(Id@field)
  @Column(name = "userName")
  @Convert(converter = classOf[UUIDAttributeConverter])
  private var userName: UUID = _

  /*@Column(name = "firstName", nullable = false, length = 20)
  private[model] var firstName: String = _

  @Column(name = "lastName", nullable = false, length = 20)
  private[model] var lastName: String = _*/

  def this() = {
    this(null, null)
  }


  def this(_Id: UUID) = {
    this(null, null)
    userName = _Id
  }

  /*  def getUserName: UUID = userName

    def setUserName(userName: UUID): Unit = this.userName = userName

    def getFirstName: String = firstName

    def setFirstName(firstName: String): Unit = this.firstName = firstName

    def getLastName: String = lastName

    def setLastName(lastName: String): Unit = this.lastName = lastName*/
}

object Person0Old {
  type _Column = Column@beanGetter
  final val tableName = "person0"
  final val schemaName = "OLINGO"
}