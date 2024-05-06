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
import org.springframework.web.bind.annotation.RestController;

import gudmundsson.com.invoice.dto.HealthMessage;
import gudmundsson.com.invoice.dto.ResponseInvoiceDto;
import gudmundsson.com.invoice.service.ResponseInvoiceDiscountService;
import gudmundsson.com.invoice.util.AElog;
import gudmundsson.com.invoice.util.AEutil;
import gudmundsson.com.invoice.util.exception.response.custom.CustomRuntimeException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/tigo/BO/invoicesDetails")
public class InvoiceDetailsResource {

	private static final Logger logger = LoggerFactory.getLogger(InvoiceDetailsResource.class);

	@Autowired
	private AEutil util;
	
	@Autowired
	private ResponseInvoiceDiscountService response; 
	
	@GetMapping("/status")
	public ResponseEntity<Object> healthRequest(HttpServletRequest request) throws Exception {

		HealthMessage object;
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, "X: ");

		object = new HealthMessage("Service is operating normally in Dettails!!");

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 OK");
		return new ResponseEntity<Object>(object, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/{invoiceId}")
	public ResponseEntity<ResponseInvoiceDto> getInvoiceDetails(@PathVariable("invoiceId") String invoiceId,
			HttpServletRequest request) {

		String sessionLogId = System.currentTimeMillis() + ": ";
		ResponseInvoiceDto responseObj = new ResponseInvoiceDto();// este es el objetito
		HttpHeaders responseHeaders = new HttpHeaders();
		requestLog(request, sessionLogId);

		if (invoiceId == null || invoiceId.isEmpty()) {
			throw new CustomRuntimeException(HttpStatus.BAD_REQUEST, 400, "El parametro 'invoiceId' no es valido");
		}

		responseObj = response.getInvoiceDetails(ofNullable(invoiceId));

		if (responseObj == null ) {
			throw new CustomRuntimeException(HttpStatus.NOT_FOUND, 404, "No se encontraron datos para la busqueda");
		}

		responseHeaders.set("Custom-Message", "HTTP/1.1 200 Ok");
		return new ResponseEntity<ResponseInvoiceDto>(responseObj, responseHeaders, HttpStatus.OK);

	}
	
	private synchronized void requestLog(HttpServletRequest request, String sessionLogId) {
		AElog.infoX(logger,
				sessionLogId + util.getInetAddressPort() + " <= " + request.getRemoteHost() + " {method:"
						+ request.getMethod() + ", URI:" + request.getRequestURI() + ", query:"
						+ request.getQueryString() + "}");
	}
}
