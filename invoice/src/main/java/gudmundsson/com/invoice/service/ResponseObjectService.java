package gudmundsson.com.invoice.service;

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

	public ResponseObjectDto getQueryRecordsB(Optional<String> customerType, Optional<String> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());
		responseObjectDto.getData().setInvoices(
					rQueryRepository.getInvByCustomerInvoiceId(customerType, invoiceId));
		
		List<Invoice> invoices = responseObjectDto.getData().getInvoices();

		for (Invoice invoice : invoices) {
			Client client = rQueryRepository.getClientById(Optional.of(invoice.getClient().getClientId()));
			invoice.setClient(client);
		}

		return responseObjectDto;
	}
}
