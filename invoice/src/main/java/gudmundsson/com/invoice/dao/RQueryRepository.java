package gudmundsson.com.invoice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.ItemService;
import gudmundsson.com.invoice.util.exception.RepositoryException;

@Repository
public class RQueryRepository {

	public static final int MAX_PAGE_SIZE = 11000;

	@Autowired
	private MQueryMapper mQueryMapper;
//***
	public Client getClientById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getClientById(id.orElse(null));
	}
//***
	public Invoice getInvoiceById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getInvoiceById(id.orElse(null));
	}
	
	public ItemService getItemServiceById(Optional<String> id) throws RepositoryException {
		return mQueryMapper.getItemServiceById(id.orElse(null));
	}
//***
	public List<Invoice> getInvoicesQueryA1(Optional<String> idType,
			Optional<String> clientId, Optional<String> billingPeriod, Optional<String> invoiceId)
			throws RepositoryException {
		return mQueryMapper.getInvoicesQueryA1(idType.orElse(null), clientId.orElse(null), 
				billingPeriod.orElse(null), invoiceId.orElse(null));
	}
//***
	public List<Invoice> getInvoicesQueryA2(Optional<String> idType,
			Optional<String> clientId, Optional<String> billingPeriod)
			throws RepositoryException {
		return mQueryMapper.getInvoicesQueryA2(idType.orElse(null), clientId.orElse(null), 
				billingPeriod.orElse(null));
	}
//***	
	public Invoice getInvoiceByIdB(Optional<String> invoiceId)
			throws RepositoryException {
		return mQueryMapper.getInvoiceByIdB(invoiceId.orElse(null));
	}
//***
	public List<Client> getClientsByCustomerTypeIdTypeMOBILE(Optional<String> idType) 
			throws RepositoryException {
		return mQueryMapper.getClientsByCustomerTypeIdTypeMOBILE(idType.orElse(null));
	}
//***
	public List<Client> getClientsByHOMEIdType(Optional<String> idType) 
			throws RepositoryException {
		return mQueryMapper.getClientsByHOMEIdType(idType.orElse(null));
	}
//***
	public List<Invoice> getInvoicesByClient(Optional<String> clientId, Optional<String> billingPeriod)
			throws RepositoryException {
		return mQueryMapper.getInvoicesByClient(clientId.orElse(null), billingPeriod.orElse(null));
	}
//***	
	public Invoice getInvByHOMEIdTypeBillingInvoiceId(Optional<String> idType,
			Optional<String> billingPeriod, Optional<String> invoiceId )throws RepositoryException {
		return mQueryMapper.getInvByHOMEIdTypeBillingInvoiceId(idType.orElse(null),
				billingPeriod.orElse(null), invoiceId.orElse(null));
	}
//***
	public List<ItemService> getServicesByClientId(Optional<String> clientId) throws RepositoryException {
		return mQueryMapper.getServicesByClientId(clientId.orElse(null));
	}
}
