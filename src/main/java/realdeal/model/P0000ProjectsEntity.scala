package realdeal.model

import java.time.OffsetDateTime

import jakarta.persistence.{CascadeType, Column, Convert, Entity, Id, OneToMany, Table}
import realdeal.model.P0000ProjectsEntity._Column
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.annotation.meta.{beanGetter, field}
import scala.beans.BeanProperty

@Entity
@PrimaryKey (validation = IdValidation.NULL)
@Table(name = P0000ProjectsEntity.tableName, schema = P0000ProjectsEntity.schemaName)
class P0000ProjectsEntity {
  @(Id@field)
  @(_Column@field)(name = "\"Id\"")
  @BeanProperty
  var id: Integer = _

  @(_Column@field)(name = "\"ProjOwner\"", length = 36)
  @BeanProperty
  var projOwner: String = _

  @(_Column@field)(name = "\"ProjectCode\"", length = 10)
  @BeanProperty
  var projectCode: String = _

  @(_Column@field)(name = "\"ProjectName\"", length = 36)
  @BeanProperty
  var projectName: String = _

  @(_Column@field)(name = "\"StartDate\"", nullable = false)
  @BeanProperty
  var startDate: java.time.OffsetDateTime = _

  @(_Column@field)(name = "\"Description\"",  length = 320, nullable = false)
  @BeanProperty
  var description: String = _
}

object P0000ProjectsEntity {
  type _Column = Column@beanGetter
  final val schemaName= "DEV_GREENTRAK00"
  final val tableName = "P0000PROJECTS"
}