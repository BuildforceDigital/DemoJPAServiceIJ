package de.mirkosertic.scala

import beans.BeanProperty
import jakarta.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}
@Entity
@Table(name = "CHILD", schema = "OLINGO")
class Child(@BeanProperty val name: String) {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"Id\"", nullable = false)
  var id: Int = _

  // Default constructor for hibernate
  // No public visibility required
  private def this() = this(null)

  override def toString: String = s"$id, $name"
}