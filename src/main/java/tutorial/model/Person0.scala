package tutorial.model

import jakarta.persistence.{Column, Entity, Id, Table}
import tutorial.model.Person0._Column

import scala.annotation.meta.{beanGetter, field}
import scala.beans.BeanProperty

@Entity
@Table(name = Person0.tableName, schema = Person0.schemaName)
case class Person0(
                    @_Column(name = "firstName", nullable = false, length = 20)
                    @BeanProperty val firstName: String,
                    @BeanProperty val lastName: String) {
  @(Id@field)
  private val userName = ""

  override def toString: String = s"$userName $firstName $lastName."

  // Default constructor for persistence providers
  // No public
  // visibility required
  private def this() = this(null, null)

}

object Person0 {
  type _Column = Column @beanGetter
  final val tableName = "person0"
  final val schemaName ="OLINGO"
}