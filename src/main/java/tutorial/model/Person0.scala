package tutorial.model

import jakarta.persistence.{Column, Convert, Entity, Id, Table}
import java.util.UUID

import tutorial.model.Person0._Column

import scala.annotation.meta.{beanGetter, field}
import scala.beans.BeanProperty

@Entity
@Table(name = "person0", schema = "OLINGO")
class Person0 {
  @(Id@field)
  @Column(name = "userName")
  @Convert(converter = classOf[UUIDAttributeConverter])
  @BeanProperty
  var userName: UUID = _

  @(_Column@field)(name = "firstName", nullable = false, length = 20)
  @BeanProperty
  var firstName: String = _

  @(_Column@field)(name = "lastName", nullable = false, length = 20)
  @BeanProperty
  var lastName: String = _

}

object Person0 {
  type _Column = Column@beanGetter
}