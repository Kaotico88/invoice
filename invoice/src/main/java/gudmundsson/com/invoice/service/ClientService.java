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
	
	public List<Client> getByCustomerIdTypeMOBILE(Optional<String> idType) {
		List<Client> clients = new ArrayList<>();
		clients = rQueryRepository.getClientByCustomerTypeIdTypeMOBILE(idType);
		return clients;	
	}
	
	public List<Client> getClientByHOMEIdType(Optional<String> idType) {
		List<Client> clients = new ArrayList<>();
		clients = rQueryRepository.getClientByHOMEIdType(idType);
		return clients;	
	}
}
