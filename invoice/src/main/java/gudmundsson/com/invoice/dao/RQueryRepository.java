package gudmundsson.com.invoice.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.InvoiceService;
import gudmundsson.com.invoice.core.Service;
import gudmundsson.com.invoice.util.exception.RepositoryException;

@Repository
public class RQueryRepository {

	public static final int MAX_PAGE_SIZE = 11000;

	@Autowired
	private MQueryMapper mQueryMapper;

	public Client getClientById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getClientById(id.orElse(null));
	}

	public Invoice getInvoiceById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getInvoiceById(id.orElse(null));
	}

	public Service getServiceById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getServiceById(id.orElse(null));
	}

	public InvoiceService getInvSerById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getInvSerById(id.orElse(null));
	}

	public List<Client> getAllClients(Optional<String> recordCustomerType, Optional<String> recordIdType,
			Optional<String> recordMsisdn, Optional<Date> recordActivationDate, Optional<Double> recordTotalDiscount,
			Integer limit, Integer offset) throws RepositoryException {
		return mQueryMapper.getAllClients(recordCustomerType.orElse(null), recordIdType.orElse(null),
				recordMsisdn.orElse(null), recordActivationDate.orElse(null), recordTotalDiscount.orElse(null), limit,
				offset);
	}

	public List<Invoice> getAllInvoices(Optional<String> recordBillingPeriod, Optional<Double> recordTotalAmount,
			Optional<String> recordClientId, Integer limit, Integer offset) throws RepositoryException {
		return mQueryMapper.getAllInvoices(recordBillingPeriod.orElse(null), recordTotalAmount.orElse(null),
				recordClientId.orElse(null), limit, offset);
	}

	public List<Service> getAllServices(Optional<String> recordName, Optional<Integer> recordAmount,
			Optional<String> recordClientId, Integer limit, Integer offset) throws RepositoryException {
		return mQueryMapper.getAllServices(recordName.orElse(null), recordAmount.orElse(null),
				recordClientId.orElse(null), limit, offset);
	}

	public List<InvoiceService> getAllInvoiceServices(Optional<String> recordInvoiceId,
			Optional<String> recordServiceId, Integer limit, Integer offset) throws RepositoryException {
		return mQueryMapper.getAllInvoServ(recordInvoiceId.orElse(null), recordServiceId.orElse(null), limit, offset);
	}
}