package gudmundsson.com.invoice.core;

public class InvoiceService {

	private String invservId;

	private Invoice invoice;

	private Service service;

	public String getInvservId() {
		return invservId;
	}

	public void setInvservId(String invservId) {
		this.invservId = invservId;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
