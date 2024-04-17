package gudmundsson.com.invoice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Invoice;
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
			Optional<String> clientId, Optional<String> billingPeriod, Optional<String> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		Invoice invoice = invoiceService.getById(invoiceId);
		customerType = Optional.of(invoice.getClient().getCustomerType());
		idType = Optional.of(invoice.getClient().getIdType());
		clientId = Optional.of(invoice.getClient().getClientId());
		
		System.out.println("#####################Este es el valor de customerType: " + customerType);
		System.out.println("#####################Este es el valor de idType: " + idType);
		System.out.println("#####################Este es el valor de clientId: " + clientId);
		responseObjectDto.setData(new Data());
		responseObjectDto.getData()
				.setInvoices(rQueryRepository.getInvoicesQuery(customerType, idType, clientId, billingPeriod, invoiceId));

//		responseObjectDto.getData().getInvoices().

		return responseObjectDto;
	}

}
