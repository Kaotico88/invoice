package gudmundsson.com.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.dao.RQueryRepository;

@Service
public class ClientService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	public String getByCustomerIdType(Optional<String> customerType, Optional<String> idType) {
		Client client;
		client = rQueryRepository.getClientByCustomerTIdType(customerType, idType);
		return client.getClientId();	
	}
}
