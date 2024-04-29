package gudmundsson.com.invoice.dto.core;

import java.util.ArrayList;
import java.util.List;

import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.Service;

public class Data {

	private List<Invoice> invoices;
	
	private List<Service> services;
	
	public Data() {
		this.invoices = new ArrayList<>();
		this.services = new ArrayList<>();
	}
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	
	
}
