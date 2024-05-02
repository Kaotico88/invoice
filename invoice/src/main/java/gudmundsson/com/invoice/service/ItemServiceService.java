package gudmundsson.com.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.ItemService;
import gudmundsson.com.invoice.dao.RQueryRepository;

@Service
public class ItemServiceService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	public ItemService getItemServiceByIdImprove(Optional<String> id) {
		ItemService itemServiceImprove = new ItemService();
		Client client;
		itemServiceImprove = rQueryRepository.getItemServiceById(id);
		
		Optional<String> clientId = Optional.of(itemServiceImprove.getClient().getClientId());
		client = rQueryRepository.getClientById(clientId);
		
		itemServiceImprove.getClient().setCustomerType(client.getCustomerType());
		itemServiceImprove.getClient().setIdType(client.getIdType());
		itemServiceImprove.getClient().setMsisdn(client.getMsisdn());
		itemServiceImprove.getClient().setClientId(clientId.orElse(null));
		
		return itemServiceImprove;
	}
}
