package gudmundsson.com.invoice.core;

public class Invoice {

	private String invoiceId;

	private String billingPeriod;

	private Double totalAmount;

	private Client client;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
