package tutorial.model

import jakarta.persistence._
import javax.persistence.{GeneratedValue, GenerationType}

@Entity
@Table(name = "\"Todos\"", schema = "OLINGO")
class TodoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"Id\"", nullable = false)
  private var id: Int = _

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

  override def toString: String = "Todo [summary=" + summary + ", description=" + description + "]"

}