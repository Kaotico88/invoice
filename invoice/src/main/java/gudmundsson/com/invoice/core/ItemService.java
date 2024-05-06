package gudmundsson.com.invoice.core;

/**
 * ItemService
 *
 * @author Rene Gudmundsson
 * @since 1.0
 */
public class ItemService {

	private String serviceId;

	private String serviceName;

	private Integer serviceAmount;

	private Client client;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceAmount() {
		return serviceAmount;
	}

	public void setServiceAmount(Integer serviceAmount) {
		this.serviceAmount = serviceAmount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
