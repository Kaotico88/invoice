package gudmundsson.com.invoice.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.InvoiceService;
import gudmundsson.com.invoice.core.Service;
import gudmundsson.com.invoice.util.exception.RepositoryException;

@Mapper
public interface MQueryMapper {

	public Client getClientById(@Param("objectId") String objectId) throws RepositoryException;

	public Invoice getInvoiceById(@Param("objectId") String objectId) throws RepositoryException;

	public Service getServiceById(@Param("objectId") String objectId) throws RepositoryException;

	public InvoiceService getInvSerById(@Param("objectId") String objectId) throws RepositoryException;

	public List<Client> getAllClients(@Param("recordCustomerType") String recordCustomerType,
			@Param("recordIdType") String recordIdType, @Param("recordMsisdn") String recordMsisdn,
			@Param("recordActivationDate") Date recordActivationDate,
			@Param("recordTotalDiscount") Double recordTotalDiscount, @Param("limit") Integer limit,
			@Param("offset") Integer offset) throws RepositoryException;

	public List<Invoice> getAllInvoices(@Param("recordBillingPeriod") String recordBillingPeriod,
			@Param("recordTotalAmount") Double recordTotalAmount, @Param("recordClientId") String recordClientId,
			@Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

	public List<Service> getAllServices(@Param("recordName") String recordName,
			@Param("recordAmount") Integer recordAmount, @Param("recordClientId") String recordClientId,
			@Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

	public List<InvoiceService> getAllInvoServ(@Param("recordInvoiceId") String recordInvoiceId,
			@Param("recordServiceId") String recordServiceId, @Param("limit") Integer limit,
			@Param("offset") Integer offset) throws RepositoryException;

//Aca empieza los querys que realmente pueden ayudar con el objetivo /v1/tigo/BO/invoices/:customerType/:idType/:id/:billingPeriod?invoiceId	

	public Client getClientQuery(@Param("recordCustomerType") String recordCustomerType,
			@Param("recordIdType") String recordIdType, @Param("recordClientId") String recordClientId)
			throws RepositoryException;

// esto es lo que se modifica para traer todos los parametros requeridos	
	public List<Invoice> getInvoicesQuery(@Param("recordCustomerType") String recordCustomerType,
			@Param("recordIdType") String recordIdType, @Param("recordClientId") String recordClientId,
			@Param("recordBillingPeriod") String recordBillingPeriod, @Param("recordInvoiceId") String recordInvoiceId)
			throws RepositoryException;
	
// Este es el query que no require del InvoiceId	
		public List<Invoice> getInvoicesWithoutInvoiceId(@Param("recordCustomerType") String recordCustomerType,
				@Param("recordIdType") String recordIdType, @Param("recordClientId") String recordClientId,
				@Param("recordBillingPeriod") String recordBillingPeriod)
				throws RepositoryException;
		
// Este es el query que filtra por customerTyp e invoiceId	
		public List<Invoice> getInvByCustomerInvoiceId(@Param("recordCustomerType") String recordCustomerType,
				@Param("recordInvoiceId") String recordInvoiceId)
				throws RepositoryException;
}
