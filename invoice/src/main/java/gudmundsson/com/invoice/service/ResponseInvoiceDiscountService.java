package gudmundsson.com.invoice.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gudmundsson.com.invoice.core.Client;
import gudmundsson.com.invoice.core.Invoice;
import gudmundsson.com.invoice.core.ItemService;
import gudmundsson.com.invoice.dao.RQueryRepository;
import gudmundsson.com.invoice.dto.ResponseInvoiceDto;

@Service
public class ResponseInvoiceDiscountService {

	@Autowired
	private RQueryRepository rQueryRepository;
	
	@Autowired
	private InvoiceService invoiceService;

	public ResponseInvoiceDto getInvoiceDetails(Optional<String> invoiceId) {

		Invoice invoice = invoiceService.getInvoiceByIdImprove(invoiceId);
		
		Client client = rQueryRepository.getClientById(Optional.of(invoice.getClient().getClientId()));
		
		List<ItemService> itemServices = rQueryRepository.getServicesByClientId(Optional.of(client.getClientId()));

		double discountYears = getDiscoutYears(client, invoice);

		double discountServices = getDiscoutServices(itemServices, invoice);

		double totalNeto = discountYears + discountServices;
	
		ResponseInvoiceDto invoiceDto = new ResponseInvoiceDto(invoice, itemServices, discountYears, discountServices,
				totalNeto);

		return invoiceDto;
	}

	private double getDiscoutYears(Client client, Invoice invoiceImprove) {

		double totalAmount;
		double totalDiscount = 0;

		GregorianCalendar cale = new GregorianCalendar();
		float currentYear = cale.get(Calendar.YEAR);
		int currentDaysOfYear = Math.max(cale.get(Calendar.DAY_OF_YEAR), 365);
		currentYear = currentYear + (currentDaysOfYear / 366);

		cale.setTime(client.getActivationDate());
		double activationYear = cale.get(Calendar.YEAR);
		int activatioDaysOfYear = Math.max(cale.get(Calendar.DAY_OF_YEAR), 365);
		activationYear = activationYear + (activatioDaysOfYear / 366);
		double diffYear = currentYear - activationYear;
						
		if (diffYear >= 5) {
			totalAmount = invoiceImprove.getTotalAmount();
			double discount = (5.0 / 100.0) * totalAmount;
			totalAmount = totalAmount - discount;
			
			invoiceImprove.setClient(client);
			totalDiscount += discount;
			invoiceImprove.getClient().setTotalDiscount(totalDiscount);				
		} else {
			totalAmount = invoiceImprove.getTotalAmount();
			invoiceImprove.getClient().setTotalDiscount(totalDiscount);
		}
		return totalAmount;
	}

	private double calculateTotalItemServiceAmount(List<ItemService> itemServices) {

		double totalItemServiceAmount = 0;
		for (ItemService itemService : itemServices) {
			totalItemServiceAmount += itemService.getServiceAmount();
		}
		return totalItemServiceAmount;
	}

	private double getDiscoutServices(List<ItemService> itemServices, Invoice invoice) {

		double totalItemServiceAmount = calculateTotalItemServiceAmount(itemServices);
		double totalAmount = 0;
		double totalDiscount = 0;
		
		if (itemServices.size() >= 2) {
			double discount = (2.0 / 100.0) * totalItemServiceAmount;
			totalAmount = totalItemServiceAmount - discount;
			
			totalDiscount = invoice.getClient().getTotalDiscount();
			totalDiscount += discount;
			
			invoice.getClient().setTotalDiscount(totalDiscount);
		} else {
			totalAmount = itemServices.get(0).getServiceAmount();					
//			invoice.getClient().setTotalDiscount(totalDiscount);			
		}
		return totalAmount;
	}

}
