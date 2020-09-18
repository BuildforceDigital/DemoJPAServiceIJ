package realdeal.model

import jakarta.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}
import org.eclipse.persistence.annotations.{IdValidation, PrimaryKey}

import scala.beans.BeanProperty

@Entity
@PrimaryKey(validation = IdValidation.NULL)
@Table(name = A0000UsersEntity.tableName, schema = A0000UsersEntity.schemaName)
class A0000UsersEntity( @Column(name = "\"BirthDay00\"", length = 36) @BeanProperty val birthDay: String,
                        @Column(name = "\"BusinessEmail\"", length = 10)    @BeanProperty val businessEmail: String,
                        @Column(name = "\"CitizenServiceNr\"", length = 36) @BeanProperty val citizenServiceNr: String,
                        @Column(name = "\"FullName\"", nullable = false) @BeanProperty val fullName: String,
                        @Column(name = "\"Gender\"") @BeanProperty val gender: String,
                        @Column(name = "\"JobFunction\"", length = 40) @BeanProperty val jobFunction: String,
                        @Column(name = "\"LandlinePhone\"") @BeanProperty val landlinePhone: String,
                        @Column(name = "\"MobilePhone\"", length = 36) @BeanProperty val mobilePhone: String,
                        @Column(name = "\"Nationality\"") @BeanProperty val nationality: String,
                        @Column(name = "\"Nickname\"") @BeanProperty val nickname: String,
                        @Column(name = "\"PrivateEmail\"", length = 160) @BeanProperty val privateEmail: String,
                        @Column(name = "\"TillDate\"") @BeanProperty val tillDate: java.time.OffsetDateTime,
                        @Column(name = "\"UserImage\"", length = 40) @BeanProperty val userImage: String,
                        @Column(name = "\"UserName\"", length = 40) @BeanProperty val userName: String) {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private var id = 0L

  // Default constructor for persistence providers
  // No public visibility required
  private def this() = this(null, null, null, null, null, null, null, null, null, null, null, null, null, null)
}

object A0000UsersEntity {
  final val tableName = "A0000USERS"
  final val schemaName ="DEV_GREENTRAK00"

}