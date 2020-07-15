package tutorial.model

import java.time.OffsetDateTime

import jakarta.persistence._

@Entity
@Table(name = "\"Invoices\"", schema = "OLINGO")
class WalktroughInvoiceEntity {
  @Id
  @Column(name = "\"ProductName\"", nullable = false) private var productName: String = _

  def getProductName: String = productName

  def setProductName(productName: String): Unit = {
    this.productName = productName
  }

  @Id
  @Column(name = "\"Quantity\"", nullable = false) private var quantity: Short = _

  def getQuantity = quantity

  def setQuantity(quantity: Short): Unit = {
    this.quantity = quantity
  }

  @Id
  @Column(name = "\"ShipperName\"", nullable = false) private var shipperName: String = _

  def getShipperName: String = shipperName

  def setShipperName(shipperName: String): Unit = {
    this.shipperName = shipperName
  }

  @Basic
  @Column(name = "\"ExtendedPrice\""/*, nullable = false*/, precision = 19) private var extendedPrice: Double =  _

  def getExtendedPrice: Double = extendedPrice

  def setExtendedPrice(extendedPrice: Double): Unit = {
    this.extendedPrice = extendedPrice
  }

  @Basic
  @Column(name = "\"Status\"", /*nullable = false,*/ length = 1) private var status: String = _

  def getStatus: String = status

  def setStatus(status: String): Unit = {
    this.status = status
  }

  @Basic
  @Column(name = "\"ShippingDate\"", nullable = true)
  private var shippedDate: OffsetDateTime = _

  def getCheckInDateTime: OffsetDateTime = shippedDate

  def setCheckInDateTime(checkInDatetime: OffsetDateTime): Unit = {
    this.shippedDate = checkInDatetime
  }

}