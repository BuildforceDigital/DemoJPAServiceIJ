package tutorial.model

import java.time.OffsetDateTime

import jakarta.persistence.{Basic, Column, Entity, Id, Table}

@Entity
@Table(name = "\"Invoices\"", schema = "OLINGO")
class WalkthroughInvoiceEntity {
  @Id
  @Column(name = "\"ProductName\"", nullable = false)
  private var productName: String = _
  @Id
  @Column(name = "\"Quantity\"", nullable = false)
  private var quantity: Short = _
  @Id
  @Column(name = "\"ShipperName\"", nullable = false)
  private var shipperName: String = _

  @Basic
  @Column(name = "\"ExtendedPrice\"" /*, nullable = false*/ , precision = 19)
  private var extendedPrice: Double = _

  @Basic
  @Column(name = "\"Status\"", /*nullable = false,*/ length = 1)
  private var status: String = _

  @Basic
  @Column(name = "\"ShippingDate\"", nullable = true)
  private var shippedDate: OffsetDateTime = _

  def getProductName: String = productName

  def setProductName(productName: String): Unit = {
    this.productName = productName
  }

  def getQuantity = quantity

  def setQuantity(quantity: Short): Unit = {
    this.quantity = quantity
  }

  def getShipperName: String = shipperName

  def setShipperName(shipperName: String): Unit = {
    this.shipperName = shipperName
  }

  def getExtendedPrice: Double = extendedPrice

  def setExtendedPrice(extendedPrice: Double): Unit = {
    this.extendedPrice = extendedPrice
  }

  def getStatus: String = status

  def setStatus(status: String): Unit = {
    this.status = status
  }

  def getCheckInDateTime: OffsetDateTime = shippedDate

  def setCheckInDateTime(checkInDatetime: OffsetDateTime): Unit = {
    this.shippedDate = checkInDatetime
  }

}