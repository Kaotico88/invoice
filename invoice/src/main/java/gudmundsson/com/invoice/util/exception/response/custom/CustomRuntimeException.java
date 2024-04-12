package gudmundsson.com.invoice.util.exception.response.custom;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class CustomRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    private Date date;

    private Integer statusCode;

    private String statusName;

    private Integer code;

    private String message;

    private String details;

    private boolean printTraceEnabled;

    public CustomRuntimeException(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, null, "", false);
    }

    public CustomRuntimeException(HttpStatus httpStatus, String message) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, message, "", false);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer code, String message) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, message, "", false);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer code) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, null, "", false);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer statusCode, String statusName, Integer code,
            String message) {
        this(httpStatus, statusCode, statusName, code, message, "", false);
    }

    public CustomRuntimeException(HttpStatus httpStatus, boolean printTraceEnabled) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, null, "", printTraceEnabled);
    }

    public CustomRuntimeException(HttpStatus httpStatus, String message, boolean printTraceEnabled) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, message, "", printTraceEnabled);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer code, boolean printTraceEnabled) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, null, "", printTraceEnabled);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer code, String message, boolean printTraceEnabled) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, message, "", printTraceEnabled);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer statusCode, String statusName, Integer code,
            String message, boolean printTraceEnabled) {
        this(httpStatus, statusCode, statusName, code, message, "", printTraceEnabled);
    }

    public CustomRuntimeException(HttpStatus httpStatus, Integer statusCode, String statusName, Integer code,
            String message, String details, boolean printTraceEnabled) {
        super(message);
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.code = code;
        this.message = message;
        this.details = details;
        this.printTraceEnabled = printTraceEnabled;
    }

    public XErrorResponse getXErrorResponse() {
        return new XErrorResponse(this);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isPrintTraceEnabled() {
        return printTraceEnabled;
    }

    public void setPrintTraceEnabled(boolean printTraceEnabled) {
        this.printTraceEnabled = printTraceEnabled;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
