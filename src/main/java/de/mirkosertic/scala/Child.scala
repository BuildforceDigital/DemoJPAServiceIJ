package de.mirkosertic.scala

import beans.BeanProperty
import jakarta.persistence.{Entity, FetchType, GeneratedValue, GenerationType, Id, JoinColumn, ManyToOne, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.annotation.meta.field

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = "CHILD", schema = Child.schemaName)
class Child(@BeanProperty val name: String) {
  @(Id@field)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private val id = 0L
  //@Column(name = "\"Id\"")

  @BeanProperty
  @(ManyToOne@field)(fetch = FetchType.LAZY)
  //@(JoinColumn@field)(name="ID", nullable=false)
  var parent: Parent = _

  // Default constructor for hibernate
  // No public visibility required
  private def this() = this(null)

  override def toString: String = s"Child: $id, $name"

}

object Child {
  final val schemaName ="OLINGO"
}