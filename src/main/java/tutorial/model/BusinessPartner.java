package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BusinessPartner
 *
 */
@Inheritance
@DiscriminatorColumn(name = "\"Type\"")
@Entity(name = "BusinessPartner")
@Table(schema = "\"OLINGO\"", name = "\"BusinessPartner\"")
public abstract class BusinessPartner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 32)
	private String iD;

	@Version
	@Column(name = "\"ETag\"")
	private long eTag;

	@EdmIgnore
	@Column(name = "\"CustomString1\"", length = 250)
	private String customString1;

	@EdmIgnore
	@Column(name = "\"CustomString2\"", length = 250)
	private String customString2;

	@EdmIgnore
	@Column(name = "\"CustomNum1\"", precision = 30, scale = 5)
	private BigDecimal customNum1;

	@EdmIgnore
	@Column(name = "\"CustomNum2\"", precision = 30, scale = 5)
	private BigDecimal customNum2;

	@OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<BusinessPartnerRole> roles;

	@Embedded
	private PostalAddressData address = new PostalAddressData();

	@Embedded
	private AdministrativeInformation administrativeInformation = new AdministrativeInformation();

	public BusinessPartner() {
		super();
	}
}