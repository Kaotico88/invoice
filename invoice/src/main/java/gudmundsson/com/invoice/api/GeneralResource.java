package gudmundsson.com.invoice.api;

import static java.util.Optional.ofNullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gudmundsson.com.invoice.dto.HealthMessage;
import gudmundsson.com.invoice.dto.ResponseObjectDto;
import gudmundsson.com.invoice.service.ResponseObjectService;
import gudmundsson.com.invoice.util.AElog;
import gudmundsson.com.invoice.util.AEutil;
import gudmundsson.com.invoice.util.exception.response.custom.CustomRuntimeException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/tigo/BO/invoices")
public class GeneralResource {

	private static final Logger logger = LoggerFactory.getLogger(GeneralResource.class);

    @Autowired
    private AEutil util;
    
    @Autowired
    private ResponseObjectService responseObjectService;
    
    @GetMapping("/status")
    public ResponseEntity<Object> healthRequest(HttpServletRequest request) throws Exception {

        HealthMessage object;
        HttpHeaders responseHeaders = new HttpHeaders();
        requestLog(request, "X: ");

        object = new HealthMessage("Service is operating normally!!");

        responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
        return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
    }
    //aca empieza el resource del requrimiento
    @GetMapping("/{customerType}/{idType}/{clientId}/{billingPeriod}")
    public ResponseEntity<ResponseObjectDto> getInvoiceClient(@PathVariable("customerType") String customerType, 
    		@PathVariable("idType") String idType, @PathVariable("clientId") String clientId, 
    		@PathVariable("billingPeriod") String billingPeriod, @RequestParam(name = "invoiceId") String invoiceId,
    		HttpServletRequest request){
    	
    	String sessionLogId = System.currentTimeMillis() + ": ";
        ResponseObjectDto responseObj = new ResponseObjectDto();//este es el objetito
        HttpHeaders responseHeaders = new HttpHeaders();
        //requestLog(request); 
    	
        if(customerType == null || customerType.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'customerType' no es valido");
        }
        if(idType == null || idType.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'idType' no es valido");
        }
        if(clientId == null || clientId.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'clientId' no es valido");
        }
        if(idType == null || idType.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'idType' no es valido");
        }
        if(billingPeriod == null || billingPeriod.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'billingPeriod' no es valido");
        }
        if(invoiceId == null || invoiceId.isEmpty()) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'invoiceIde' no es valido");
        }
        
        responseObj = responseObjectService.getQueryRecords(ofNullable(customerType), ofNullable(idType), ofNullable(clientId), 
        		ofNullable(billingPeriod), ofNullable(invoiceId), sessionLogId);
        
        if(responseObj == null || responseObj.getData() == null || responseObj.getData().getInvoices().isEmpty() ) {
        	throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "No se encontraron datos para la busqueda");
        }
        
        responseHeaders.set("Custom-Message", "HTTP/1.1 200 Ok");
        return new ResponseEntity<ResponseObjectDto>(responseObj, responseHeaders, HttpStatus.ACCEPTED); 
        
    }
    
    
    private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
        AElog.infoX(logger,
                sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
                        + request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
                        + request.getQueryString() + "}");
    }
    
    
}
