package gudmundsson.com.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.dao.RQueryRepository;
import gudmundsson.com.invoice.dto.ResponseObjectDto;
import gudmundsson.com.invoice.dto.core.Data;
import gudmundsson.com.invoice.util.exception.RepositoryException;

@Service
public class ResponseObjectService {

	@Autowired
	private RQueryRepository rQueryRepository;

	@Autowired
	private InvoiceService invoiceService;

	// esto puede estar en otro paquete como invoiceService y llamarlo en el
	// ResponseObject
	public ResponseObjectDto getQueryRecords(Optional<String> customerType, Optional<String> idType,
			Optional<String> clientId, Optional<String> billingPeriod, Optional<String> invoiceId) 
					throws RepositoryException{

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());
		responseObjectDto.getData().setInvoices(rQueryRepository.getInvoicesQuery(invoiceId, billingPeriod, clientId));
		return responseObjectDto;
	}

//	el dato opcional que se requiere es invoiceID
//	hay que analizar esto de que es lo que recibo y que quiero obtener
}
