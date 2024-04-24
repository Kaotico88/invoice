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
	public List<Invoice> getInvoicesQuery(
			@Param("recordIdType") String recordIdType, @Param("recordClientId") String recordClientId,
			@Param("recordBillingPeriod") String recordBillingPeriod, @Param("recordInvoiceId") String recordInvoiceId)
			throws RepositoryException;
	
// Este es el query que no require del InvoiceId	
		public List<Invoice> getInvoicesWithoutInvoiceId(
				@Param("recordIdType") String recordIdType, @Param("recordClientId") String recordClientId,
				@Param("recordBillingPeriod") String recordBillingPeriod)
				throws RepositoryException;
//Aca 		
// Este es el query que filtra por customerType e invoiceId	
		public Invoice getInvByCustomerInvoiceId(
				@Param("recordInvoiceId") String recordInvoiceId) throws RepositoryException;
		
// Este es el query que filtra por customerType e idType obtendra un client
		public List<Client> getClientByCustomerTypeIdType(
				@Param("recordIdType") String recordIdType)
				throws RepositoryException;
		
// Este sera el query que filtra por cliente y billing period y me devuleve una lista de invoices
		public List<Invoice> getInvoicesByClient(@Param("recordClientId") String recordClientId,
				@Param("recordBillingPeriod") String recordBillingPeriod)
				throws RepositoryException;
		
// Este sera el query que filtra customerType, idType, billingPeriod & invoiceId		
		public Invoice getInvByCustomerIdTypeBillingInvoiceId(@Param("recordCustomerType") String recordCustomerType,
				@Param("recordIdType") String recordIdType, @Param("recordBillingPeriod") String recordBillingPeriod, 
				@Param("recordInvoiceId") String recordInvoiceId)
				throws RepositoryException;
}
