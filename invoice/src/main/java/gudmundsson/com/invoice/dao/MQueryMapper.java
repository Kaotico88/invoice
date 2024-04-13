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
}