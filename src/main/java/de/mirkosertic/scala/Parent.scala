package de.mirkosertic.scala

import jakarta.persistence.{CascadeType, Column, Entity, FetchType, GeneratedValue, GenerationType, Id, JoinColumn, OneToMany, Table}
import java.{util => ju}

import scala.beans.BeanProperty


@Entity
@Table(name = "PARENT", schema = "OLINGO")
class Parent(@BeanProperty val name1: String, @BeanProperty val name2: String) {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"Id\"", nullable = false)
  private var id: Int = _

  @OneToMany(cascade = Array(CascadeType.ALL), orphanRemoval = true, fetch=FetchType.LAZY)
  @JoinColumn(name = "PARENT_ID")
  @BeanProperty
  // Use Java collection types instead of Scala ones to make JPA happy
  var children: ju.List[Child] = new ju.ArrayList[Child]()

  // Default constructor for persistence providers
  // No public visibility required
  private def this() = this(null, null)

  /**
   * Method to add a child to the parent.
   *
   * @param aChild the child to add
   */
  def addChild(aChild: Child) {
    children.add(aChild)
  }

  /**
   * Overridden toString() implementation.
   *
   * @return id + name1 + name2 + children as a String.
   */
  override def toString: String = s"$id $name1 $name2 $children."
}