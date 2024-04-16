package gudmundsson.com.invoice.dto.core;

import java.util.ArrayList;
import java.util.List;

import gudmundsson.com.invoice.core.Invoice;

public class Data {

	private List<Invoice> invoices;
	
	public Data() {
		this.invoices = new ArrayList<>();
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
