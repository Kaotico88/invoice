package gudmundsson.com.invoice.dto.core;

import java.util.ArrayList;
import java.util.List;

import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.ItemService;

/**
 * Data
 *
 * @author Rene Gudmundsson
 * @since 1.0
 */
public class Data {

	private List<Invoice> invoices;

	private List<ItemService> itemServices;

	public Data() {
		this.invoices = new ArrayList<>();
		this.itemServices = new ArrayList<>();
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<ItemService> getItemServices() {
		return itemServices;
	}

	public void setItemServices(List<ItemService> itemServices) {
		this.itemServices = itemServices;
	}

	

}
