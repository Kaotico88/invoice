package gudmundsson.com.invoice.dto;

import gudmundsson.com.invoice.core.Invoice;

public class ResponseObject {

	private Invoice invoice;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
}
