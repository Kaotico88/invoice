package gudmundsson.com.invoice.core;
/**
 * InvoiceService
 *
 * @author Rene Gudmundsson
 * @since 1.0
 */
public class InvoiceService {

	private String invservId;

	private Invoice invoice;

	private ItemService service;

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

	public ItemService getService() {
		return service;
	}

	public void setService(ItemService service) {
		this.service = service;
	}

}
