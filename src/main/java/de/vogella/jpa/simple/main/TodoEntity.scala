package de.vogella.jpa.simple.main

import jakarta.persistence.{Basic, Column, Entity, Id, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

@Entity
@Table(name = "\"Todos\"", schema = "OLINGO")
@PrimaryKey(validation = IdValidation.NULL)
class TodoEntity(_id : Int ) {
  def this() = this(0)

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"Id\"", nullable = false)
  private var id: Int = _id

  def setId(_id : Int): Unit = id = _id

  def getId: Int = id

  @Basic
  @Column(name = "\"Summary\"", nullable = false, length = 100)
  private var summary: String = _

  @Basic
  @Column(name = "\"Description\"", nullable = false, length = 100)
  private var description: String = _

  def getSummary: String = summary

  def setSummary(_summary: String): Unit = {
    summary = _summary
  }

  def getDescription: String = description

  def setDescription(_description: String): Unit = {
    description = _description
  }

  override def toString: String = s"Todo [id=$id, summary=$summary, description=$description]"

}