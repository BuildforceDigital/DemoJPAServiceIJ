package tutorial.model

import java.time.OffsetDateTime

import jakarta.persistence.{Basic, Column, Entity, Id, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.annotation.meta.field

@Entity
@PrimaryKey (validation = IdValidation.NULL)
@Table(name = "\"P0000Projects\"", schema = "OLINGO")
class P0000ProjectsEntity {
  @(Id@field)
  @Column(name = "\"Id\"")
  private var id: Integer = _

  @Basic
  @Column(name = "\"ProjOwner\"", length = 36)
  private var projOwner: String = _

  @Basic
  @Column(name = "\"ProjectCode\"", length = 10)
  private var projectCode: String = _

  @Basic
  @Column(name = "\"ProjectName\"", length = 36)
  private var projectName: String = _

  @Basic
  @Column(name = "\"StartDate\"", nullable = false)
  private var startDate: java.time.OffsetDateTime = _

  @Basic
  @Column(name = "\"Description\"",  length = 320, nullable = false)
  private var description: String = _

  def getId: Integer = id

  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getProjOwner: String = projOwner

  def setProjOwner(projOwner: String): Unit = {
    this.projOwner = projOwner
  }

  def getProjectCode: String = projectCode

  def setProjectCode(projectCode: String): Unit = {
    this.projectCode = projectCode
  }

  def getProjectName: String = projectName

  def setProjectName(projectName: String): Unit = {
    this.projectName = projectName
  }

  def getStartDate: OffsetDateTime = startDate

  def setStartDate(startDate: OffsetDateTime): Unit = {
    this.startDate = startDate
  }

  def getDescription: String = description

  def setDescription(description: String): Unit = {
    this.description = description
  }

}