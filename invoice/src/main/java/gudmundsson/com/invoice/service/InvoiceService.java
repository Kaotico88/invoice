package gudmundsson.com.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.dao.RQueryRepository;

@Service
public class InvoiceService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	public Invoice getInvoiceByIdImprove(Optional<String> id) {
		Invoice improveInvo = new Invoice();
		Client client;
		improveInvo = rQueryRepository.getInvoiceById(id);
		
		Optional<String> clientId = Optional.of(improveInvo.getClient().getClientId());
		client = rQueryRepository.getClientById(clientId);
		
		improveInvo.getClient().setCustomerType(client.getCustomerType());
		improveInvo.getClient().setIdType(client.getIdType());
		improveInvo.getClient().setMsisdn(client.getMsisdn());
		improveInvo.getClient().setClientId(clientId.orElse(null));
		
		return improveInvo;
	}
}
