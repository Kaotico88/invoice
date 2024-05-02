package gudmundsson.com.invoice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.dao.RQueryRepository;

@Service
public class ClientService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	public List<Client> getClientsByCustomerIdTypeMOBILE(Optional<String> idType) {
		List<Client> clients = new ArrayList<>();
		clients = rQueryRepository.getClientsByCustomerTypeIdTypeMOBILE(idType);
		return clients;	
	}
	
	public List<Client> getClientsByHOMEIdType(Optional<String> idType) {
		List<Client> clients = new ArrayList<>();
		clients = rQueryRepository.getClientsByHOMEIdType(idType);
		return clients;	
	}
}
