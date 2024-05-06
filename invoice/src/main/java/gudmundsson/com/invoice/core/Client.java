package gudmundsson.com.invoice.core;

import java.sql.Date;

/**
 * Client
 *
 * @author Rene Gudmundsson
 * @since 1.0
 */

public class Client {

	private String clientId;

	private String customerType;

	private String idType;
	
	private String msisdn;

	private Date activationDate;

	private Double totalDiscount;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", customerType=" + customerType + ", idType=" + idType + ", msisdn="
				+ msisdn + ", activationDate=" + activationDate + ", totalDiscount=" + totalDiscount + "]";
	}

	
}
