package gudmundsson.com.invoice.util.exception.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import gudmundsson.com.invoice.util.exception.response.custom.CustomBadRequestException;
import gudmundsson.com.invoice.util.exception.response.custom.CustomNotFoundException;
import gudmundsson.com.invoice.util.exception.response.custom.CustomRuntimeException;
import gudmundsson.com.invoice.util.exception.response.custom.XErrorResponse;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class AEresponseEntityExceptionHandler {
	
	final static Logger logger = LoggerFactory.getLogger(AEresponseEntityExceptionHandler.class);

    // Custom Exceptions
    @ExceptionHandler(CustomBadRequestException.class)
    public final ResponseEntity<Object> handleCustomBadRequestEx(CustomBadRequestException ex, WebRequest webRequest) {
        logger.warn("CustomBadRequestException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        // logger.warn("CustomBadRequestException.class -> (full): ", ex);
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundEx(CustomNotFoundException ex, WebRequest webRequest) {
        logger.warn("CustomNotFoundException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        // logger.warn("CustomNotFoundException.class -> (full): ", ex);
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public final ResponseEntity<Object> handleCustomRuntimeEx(CustomRuntimeException ex, WebRequest webRequest) {
        logger.warn("CustomRuntimeException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        logger.warn("CustomRuntimeException.class -> handleException: {}.", ex.getXErrorResponse());
        if (ex.isPrintTraceEnabled()) {
            logger.warn("CustomRuntimeException.class -> (full): ", ex);
        }
        return new ResponseEntity<Object>(ex.getXErrorResponse(), ex.getHttpStatus());
    }

    // Native Exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest) {
        logger.warn("Exception.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        logger.warn("Exception.class -> (full): ", ex);
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> handleCustomHttpClientErrorEx(HttpClientErrorException ex,
            WebRequest webRequest) {
        logger.warn("HttpClientErrorException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        logger.warn("HttpClientErrorException.class -> (full): ", ex);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(ex.getResponseHeaders().getContentType());
        return new ResponseEntity<Object>(ex.getResponseBodyAsString(), responseHeaders, ex.getStatusCode());
    }

    @ExceptionHandler(InvalidParameterException.class)
    public final ResponseEntity<Object> handleInvalidParameterEx(InvalidParameterException ex, WebRequest webRequest) {
        logger.warn("InvalidParameterException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        // logger.warn("InvalidParameterException.class -> (full): ", ex);
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestRejectedException.class)
    public final ResponseEntity<Object> handleRequestRejectedEx(RequestRejectedException ex, WebRequest webRequest) {
        logger.warn("RequestRejectedException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
        // logger.warn("RequestRejectedException.class -> (full): ", ex);
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Object> handleRuntimeEx(RuntimeException ex, WebRequest webRequest) {
//        logger.warn("RuntimeException.class -> ({}): {}.", webRequest.getDescription(false), ex.getMessage());
//        // logger.warn("RequestRejectedException.class -> (full): ", ex);
//        return new ResponseEntity<>(new XErrorResponse(HttpStatus.CONFLICT, ex.getMessage()), HttpStatus.CONFLICT);
//
//        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
//    }

    // @Override Exceptions
    // Other exception handlers below
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("MethodArgumentNotValidException -> ({}): {}.", request.getDescription(false), ex.getMessage());
        // logger.warn("MethodArgumentNotValidException.class -> (full): ", ex);
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new XErrorResponse(HttpStatus.BAD_REQUEST, details.toString(), details),
                HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("HttpMessageNotReadableException -> (CustomMsg): {}.", "Malformed JSON request");
        logger.warn("HttpMessageNotReadableException -> ({}): {}.", request.getDescription(false), ex.getMessage());
        // logger.warn("HttpMessageNotReadableException.class -> (full): ", ex);
        return new ResponseEntity<Object>(new XErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception when the RequestParam is required and is not present in the url.
     */
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("MissingServletRequestParameter -> (CustomMsg): {}.", "Missing Servlet Request Parameter");
        logger.warn("MissingServletRequestParameter -> ({}): {}.", request.getDescription(false), ex.getMessage());
        // logger.warn("MissingServletRequestParameter.class -> (full): ", ex);
        return new ResponseEntity<Object>(new XErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception caught by spring boot (before resource)
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        logger.warn("ExceptionInternal -> ({}): {}.", request.getDescription(false), ex.getMessage());
        // logger.warn("ExceptionInternal.class -> (full): ", ex);
        return new ResponseEntity<Object>(new XErrorResponse(status, ex.getMessage()), status);
    }
}
