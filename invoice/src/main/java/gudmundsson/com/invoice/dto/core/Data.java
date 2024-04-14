package gudmundsson.com.invoice.dto.core;

import java.util.List;

import gudmundsson.com.invoice.core.Invoice;

public class Data {

	private List<Invoice> invoices;

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
