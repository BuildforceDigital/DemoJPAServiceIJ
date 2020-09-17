package de.mirkosertic.scala

import java.{util => ju}

import jakarta.persistence.{CascadeType, Entity, GeneratedValue, GenerationType, Id, OneToMany, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.beans.BeanProperty

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = Parent.tableName, schema = "OLINGO")
class Parent(@BeanProperty val name1: String, @BeanProperty val name2: String) {
  @OneToMany(mappedBy = Parent.tableName, cascade = Array(CascadeType.ALL), orphanRemoval = true)
  // Use Java collection types instead of Scala ones to make JPA happy
  final private val children: ju.List[Child] = new ju.ArrayList[Child]

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private val id = 0L

  /**
   * Method to add a child to the parent.
   *
   * @param aChild the child to add
   */
  def addChild(aChild: Child) {
    children.add(aChild)
    aChild.setParent(this)
  }

  def removeChild(aChild: Child): Unit = {
    children.remove(aChild: Child)
    aChild.setParent(null)
  }

  /**
   * Overridden toString() implementation.
   *
   * @return id + name1 + name2 + children as a String.
   */
  override def toString: String = s"$id $name1 $name2 $children."

  // Default constructor for persistence providers
  // No public visibility required
  private def this() = this(null, null)

}

object Parent {
  final val tableName = "parent"
}