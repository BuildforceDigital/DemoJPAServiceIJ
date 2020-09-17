package de.mirkosertic.scala

import jakarta.persistence.{CascadeType, Column, Entity, FetchType, GeneratedValue, GenerationType, Id, JoinColumn, OneToMany, Table}
import java.{util => ju}

import scala.beans.BeanProperty
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

@Entity(name = "Parent")
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = "PARENT", schema = "OLINGO")
class Parent(@BeanProperty val name1: String, @BeanProperty val name2: String) {

  @OneToMany(mappedBy = "Parent", cascade = Array(CascadeType.ALL), orphanRemoval = true)
  // Use Java collection types instead of Scala ones to make JPA happy
  private var children: ju.List[Child] = new ju.ArrayList[Child]()

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id: Int = _
  //@Column(name = "\"Id\"")

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
    aChild.setParent(this)
  }

  /**
   * Overridden toString() implementation.
   *
   * @return id + name1 + name2 + children as a String.
   */
  override def toString: String = s"$id $name1 $name2 $children."
}