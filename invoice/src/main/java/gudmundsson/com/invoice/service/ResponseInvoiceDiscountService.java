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
	
	public ResponseInvoiceDto getInvoiceDetails(String invoiceId) {
		
		Invoice invoice = rQueryRepository.getInvoiceById(Optional.of(invoiceId));
		
		Client client = rQueryRepository.getClientById(Optional.of(invoice.getClient().getClientId()));
		
		List<ItemService> itemServices = rQueryRepository.getServiceByClientId(Optional.of(client.getClientId()));
		
		double discountYears = getDiscoutYears(client, invoice);
		
		double discountServices = getDiscoutServices(itemServices.size());
		
		ResponseInvoiceDto invoiceDto = new ResponseInvoiceDto(
										invoice, client, itemServices, discountYears, discountYears, discountYears);
		
		return invoiceDto;
	}

	private double getDiscoutYears(Client client, Invoice invoice) {
		
		double totalAmount;
		double totalDiscount= 0;
		
		GregorianCalendar cale = new GregorianCalendar();
        float currentYear = cale.get(Calendar.YEAR);
        int currentDaysOfYear = Math.max(cale.get(Calendar.DAY_OF_YEAR), 365);
        currentYear = currentYear + (currentDaysOfYear / 366);
        
        cale.setTime(client.getActivationDate());
        double activationYear = cale.get(Calendar.YEAR);
        int activatioDaysOfYear = Math.max(cale.get(Calendar.DAY_OF_YEAR), 365);
        activationYear = activationYear + (activatioDaysOfYear / 366);       
        double diffYear = currentYear - activationYear;
        System.out.println("Los años despues de su activacion del cliente son:  " + diffYear);
        if(diffYear > 5) {
        	double amountInvoice = invoice.getTotalAmount();
        	double discount = (5.0/100.0) * amountInvoice;
        	totalAmount = amountInvoice - discount;
        	invoice.setTotalAmount(totalAmount);
        	totalDiscount += 5;
        	client.setTotalDiscount(totalDiscount);
        }else {
        	totalAmount = invoice.getTotalAmount();
        	totalDiscount += 0;
        	client.setTotalDiscount(totalDiscount);
        }
		return totalAmount;
	}
	
	private double calculateItemServiceAmount(List<ItemService> items) {
		
		double totalItemServiceAmount = 0;
		
		for(ItemService itemService : items) {
			totalItemServiceAmount += itemService.getServiceAmount();
		}
		
		return totalItemServiceAmount;
	}
	
	private double getDiscoutServices(List<ItemService> items) {
		
		double totalAmount;
		double totalDiscount = 0;
		
		if(size > 2) {
			totalAmount = calculateItemServiceAmount(itemsServices) 
		}
		
		return 0;
	}
	
}
