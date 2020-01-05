package tutorial.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public abstract class BusinessPartner {
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

	@Column(name = "\"Country\"", length = 4)
	private String country;

	@OneToMany(mappedBy = "businessPartner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<BusinessPartnerRole> roles;

	@Embedded
	private PostalAddressData address = new PostalAddressData();

	@Embedded
	private AdministrativeInformation administrativeInformation = new AdministrativeInformation();

	public BusinessPartner() {
		super();
	}

	public PostalAddressData getAddress() {
		return address;
	}

	public AdministrativeInformation getAdministrativeInformation() {
		return administrativeInformation;
	}

	public String getCountry() {
		return country;
	}

	public BigDecimal getCustomNum1() {
		return customNum1;
	}

	public BigDecimal getCustomNum2() {
		return customNum2;
	}

	public String getCustomString1() {
		return customString1;
	}

	public String getCustomString2() {
		return customString2;
	}

	public long getETag() {
		return eTag;
	}

	public String getID() {
		return iD;
	}

	public Collection<BusinessPartnerRole> getRoles() {
		if (roles == null)
			roles = new ArrayList<>();
		return roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iD == null) ? 0 : iD.hashCode());
		return result;
	}

	public void setAddress(final PostalAddressData address) {
		this.address = address;
	}

	public void setAdministrativeInformation(final AdministrativeInformation administrativeInformation) {
		this.administrativeInformation = administrativeInformation;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setCustomNum1(final BigDecimal customNum1) {
		this.customNum1 = customNum1;
	}

	public void setCustomNum2(final BigDecimal customNum2) {
		this.customNum2 = customNum2;
	}

	public void setCustomString1(final String customString1) {
		this.customString1 = customString1;
	}

	public void setCustomString2(final String customString2) {
		this.customString2 = customString2;
	}

	public void setETag(final long eTag) {
		this.eTag = eTag;
	}

	public void setID(final String iD) {
		this.iD = iD;
	}

	public void setRoles(final Collection<BusinessPartnerRole> roles) {
		this.roles = roles;
	}


}