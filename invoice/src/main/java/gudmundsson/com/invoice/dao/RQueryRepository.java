package gudmundsson.com.invoice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gudmundsson.com.invoice.core.Client;

@Repository
public class RQueryRepository {

	public static final int MAX_PAGE_SIZE = 11000;
	
	@Autowired
	private MQueryMapper mQueryMapper;
	
	public Client findClientById(Optional<String> String id) {
		
	}
}
