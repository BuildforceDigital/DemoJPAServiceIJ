package de.mirkosertic.scala

import beans.BeanProperty
import jakarta.persistence.{Entity, GeneratedValue, GenerationType, Id, FetchType, ManyToOne, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = "CHILD", schema = Child.schemaName)
class Child(@BeanProperty val name: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private val id = 0L
  //@Column(name = "\"Id\"")

  @BeanProperty
  @ManyToOne(fetch = FetchType.LAZY)
  var parent: Parent = _

  // Default constructor for hibernate
  // No public visibility required
  private def this() = this(null)

  override def toString: String = s"Child: $id, $name"

}

object Child {
  final val schemaName ="OLINGO"
}