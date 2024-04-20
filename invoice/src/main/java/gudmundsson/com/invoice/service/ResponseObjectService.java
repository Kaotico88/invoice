package gudmundsson.com.invoice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
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

	public ResponseObjectDto getQueryRecords(Optional<String> customerType, Optional<String> idType,
			Optional<String> clientId, Optional<String> billingPeriod, Optional<String> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());

		// Comprueba si invoiceId está presente antes de usarlo
		if (invoiceId.isPresent()) {
			responseObjectDto.getData().setInvoices(
					rQueryRepository.getInvoicesQuery(customerType, idType, clientId, billingPeriod, invoiceId));
		} else {
			responseObjectDto.getData().setInvoices(
					rQueryRepository.getInvoicesWithoutInvoiceId(customerType, idType, clientId, billingPeriod));
		}

		List<Invoice> invoices = responseObjectDto.getData().getInvoices();

		for (Invoice invoice : invoices) {
			Client client = rQueryRepository.getClientById(clientId);
			invoice.setClient(client);
		}

		return responseObjectDto;
	}

	public ResponseObjectDto getQueryRecordsB(Optional<String> customerType, Optional<String> invoiceId,
			String sessionLogId) throws RepositoryException {

		Invoice invoice = rQueryRepository.getInvByCustomerInvoiceId(customerType, invoiceId);

		invoice = invoiceService.getById(invoiceId);
		String clientId = invoice.getClient().getClientId();
		Client client = rQueryRepository.getClientById(Optional.of(clientId));

		invoice.setClient(client);

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());

		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice);
		
		responseObjectDto.getData().setInvoices(invoices);

		return responseObjectDto;
	}
	
	public ResponseObjectDto getQueryRecordsC(Optional<String> customerType, Optional<String> idType, Optional<String> billingPeriod, 
			String sessionLogId) throws RepositoryException {

//		Invoice invoice = rQueryRepository.getInvByCustomerIdTypeBillingP(customerType, idType, billingPeriod);
//
//		invoice = invoiceService.getById(invoiceId);
//		String clientId = invoice.getClient().getClientId();
//		Client client = rQueryRepository.getClientById(Optional.of(clientId));
//
//		invoice.setClient(client);
//
		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
//		responseObjectDto.setData(new Data());
//
//		List<Invoice> invoices = new ArrayList<>();
//		invoices.add(invoice);
//		
//		responseObjectDto.getData().setInvoices(invoices);

		return responseObjectDto;
	}
}
