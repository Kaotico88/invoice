package gudmundsson.com.invoice.dto;

import java.util.List;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.ItemService;

public class ResponseInvoiceDto {

	private Invoice invoice;
	
	private Client client;
	
	private List<ItemService> itemServices;
	
	private double discountYears;
	
	private double discountNumberServices;
	
	private double total;
	
	
	public ResponseInvoiceDto() {
	}

	public ResponseInvoiceDto(Invoice invoice, Client client, List<ItemService> itemServices, double discountYears,
			double discountNumberServices, double total) {
		this.invoice = invoice;
		this.client = client;
		this.itemServices = itemServices;
		this.discountYears = discountYears;
		this.discountNumberServices = discountNumberServices;
		this.total = total;
	}


	public Invoice getInvoice() {
		return invoice;
	}


	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public List<ItemService> getItemServices() {
		return itemServices;
	}


	public void setItemServices(List<ItemService> itemServices) {
		this.itemServices = itemServices;
	}


	public double getDiscountYears() {
		return discountYears;
	}


	public void setDiscountYears(double discountYears) {
		this.discountYears = discountYears;
	}


	public double getDiscountNumberServices() {
		return discountNumberServices;
	}


	public void setDiscountNumberServices(double discountNumberServices) {
		this.discountNumberServices = discountNumberServices;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
		
}
