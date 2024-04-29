package gudmundsson.com.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.dao.RQueryRepository;

@Service
public class ServiceService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	
}
