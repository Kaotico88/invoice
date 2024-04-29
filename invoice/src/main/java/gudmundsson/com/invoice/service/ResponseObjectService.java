package gudmundsson.com.invoice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Autowired
	private ClientService clientService;

	public ResponseObjectDto getQueryRecordsA(Optional<String> idType, Optional<String> clientId,
			Optional<String> billingPeriod, Optional<String> invoiceId, String sessionLogId)
			throws RepositoryException {

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());

		if (invoiceId.isPresent()) {
			responseObjectDto.getData()
					.setInvoices(rQueryRepository.getInvoicesQueryA1(idType, clientId, billingPeriod, invoiceId));
			responseObjectDto.getData()
					.setServices(rQueryRepository.getServiceByClientId(clientId));
		} else {
			responseObjectDto.getData()
					.setInvoices(rQueryRepository.getInvoicesQueryA2(idType, clientId, billingPeriod));
			responseObjectDto.getData()
			.setServices(rQueryRepository.getServiceByClientId(clientId));
		}

		List<Invoice> invoices = responseObjectDto.getData().getInvoices();

		for (Invoice invoice : invoices) {
			Client client = rQueryRepository.getClientById(clientId);
			invoice.setClient(client);
		}
		
		return responseObjectDto;
	}

	public ResponseObjectDto getQueryRecordsB(Optional<String> invoiceId, String sessionLogId)
			throws RepositoryException {

		Invoice invoice = rQueryRepository.getInvoiceByIdB(invoiceId);

		invoice = invoiceService.getInvoiceByIdImprove(invoiceId);
		String clientId = invoice.getClient().getClientId();
		Client client = rQueryRepository.getClientById(Optional.of(clientId));

		if (!"MOBILE".equalsIgnoreCase(client.getCustomerType())) {
			String inv = invoiceId.get();
			throw new IllegalArgumentException("El customerType del: " + inv + " no es: 'MOBILE'");
		}
		invoice.setClient(client);

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());

		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice);

		responseObjectDto.getData().setInvoices(invoices);

		return responseObjectDto;
	}

	public ResponseObjectDto getQueryRecordsC(Optional<String> idType, Optional<String> billingPeriod,
			String sessionLogId) throws RepositoryException {

		List<Client> clients = clientService.getByCustomerIdTypeMOBILE(idType);
		List<String> clientIds = clients.stream().map(Client::getClientId).collect(Collectors.toList());

		List<Invoice> invoices = new ArrayList<>();
		for (String clientId : clientIds) {
			List<Invoice> clientInvoices = rQueryRepository.getInvoicesByClient(Optional.of(clientId), billingPeriod);
			for (Invoice clientInvoice : clientInvoices) {
				Invoice invoice = invoiceService.getInvoiceByIdImprove(Optional.of(clientInvoice.getInvoiceId()));
				String id = invoice.getClient().getClientId();
				Client client = rQueryRepository.getClientById(Optional.of(id));
				clientInvoice.setClient(client);
			}
			invoices.addAll(clientInvoices);
		}

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());
		responseObjectDto.getData().setInvoices(invoices);

		return responseObjectDto;

	}

	public ResponseObjectDto getQueryRecordsD(Optional<String> idType, Optional<String> billingPeriod,
			Optional<String> invoiceId, String sessionLogId) throws RepositoryException {

		Invoice invoice = new Invoice();

		if (invoiceId.isPresent()) {
			invoice = rQueryRepository.getInvByHOMEIdTypeBillingInvoiceId(idType, billingPeriod, invoiceId);
			invoice = invoiceService.getInvoiceByIdImprove(invoiceId);
		} else {
			List<Client> clients = clientService.getClientByHOMEIdType(idType);
			List<String> clientIds = clients.stream().map(Client::getClientId).collect(Collectors.toList());

			List<Invoice> allInvoices = new ArrayList<>();
			for (String clientId : clientIds) {
				List<Invoice> clientInvoices = rQueryRepository.getInvoicesByClient(Optional.of(clientId),
						billingPeriod);
				for (Invoice clientInvoice : clientInvoices) {
					invoice = invoiceService.getInvoiceByIdImprove(Optional.of(clientInvoice.getInvoiceId()));
					String id = invoice.getClient().getClientId();
					Client client = rQueryRepository.getClientById(Optional.of(id));
					clientInvoice.setClient(client);
				}
				allInvoices.addAll(clientInvoices);
			}
		}

		ResponseObjectDto responseObjectDto = new ResponseObjectDto();
		responseObjectDto.setData(new Data());

		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice);
		responseObjectDto.getData().setInvoices(invoices);

		return responseObjectDto;

	}
}
