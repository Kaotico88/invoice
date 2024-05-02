package gudmundsson.com.invoice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.ItemService;
import gudmundsson.com.invoice.util.exception.RepositoryException;

@Mapper
public interface MQueryMapper {
//***
	public Client getClientById(@Param("objectId") String objectId) throws RepositoryException;
//***
	public Invoice getInvoiceById(@Param("objectId") String objectId) throws RepositoryException;
	
	public ItemService getItemServiceById(@Param("objectId") String objectId) throws RepositoryException;

//***	
	public List<Invoice> getInvoicesQueryA1(@Param("recordIdType") String recordIdType,
			@Param("recordClientId") String recordClientId, @Param("recordBillingPeriod") String recordBillingPeriod,
			@Param("recordInvoiceId") String recordInvoiceId) throws RepositoryException;

//***	
	public List<Invoice> getInvoicesQueryA2(@Param("recordIdType") String recordIdType,
			@Param("recordClientId") String recordClientId, @Param("recordBillingPeriod") String recordBillingPeriod)
			throws RepositoryException;

//***
	public Invoice getInvoiceByIdB(@Param("recordInvoiceId") String recordInvoiceId)
			throws RepositoryException;

//***
	public List<Client> getClientsByCustomerTypeIdTypeMOBILE(@Param("recordIdType") String recordIdType)
			throws RepositoryException;
//***
	public List<Client> getClientsByHOMEIdType(@Param("recordIdType") String recordIdType)
			throws RepositoryException;

//***
	public List<Invoice> getInvoicesByClient(@Param("recordClientId") String recordClientId,
			@Param("recordBillingPeriod") String recordBillingPeriod) throws RepositoryException;

//***		
	public Invoice getInvByHOMEIdTypeBillingInvoiceId(@Param("recordIdType") String recordIdType,
			@Param("recordBillingPeriod") String recordBillingPeriod, @Param("recordInvoiceId") String recordInvoiceId)
			throws RepositoryException;
	
//***	
	public List<ItemService> getServicesByClientId(@Param("recordClientId") String recordClientId) throws RepositoryException;
	
	
				
}
