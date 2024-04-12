package gudmundsson.com.invoice.util.exception.response.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class XErrorResponse {

	@JsonIgnore
    private HttpStatus httpStatus;

    @JsonIgnore
    private Date date;

    // @JsonProperty("status")
    @JsonIgnore
    private Integer statusCode;

    // @JsonProperty("error")
    @JsonIgnore
    private String statusName;

    @JsonIgnore
    private Integer code;

    @JsonIgnore
    private String message;

    @JsonIgnore
    private List<String> details;

    // ----------- Custom Exception: Start -----------
    private String data;

    private List<ErrorItem> errors;
    // ----------- Custom Exception: End -----------

    public XErrorResponse(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, null, new ArrayList<String>());
    }

    public XErrorResponse(HttpStatus httpStatus, List<String> details) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, null, details);
    }

    public XErrorResponse(HttpStatus httpStatus, String message) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, message, new ArrayList<String>());
    }

    public XErrorResponse(HttpStatus httpStatus, String message, List<String> details) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), null, message, details);
    }

    public XErrorResponse(HttpStatus httpStatus, Integer code) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, null, new ArrayList<String>());
    }

    public XErrorResponse(HttpStatus httpStatus, Integer code, String message) {
        this(httpStatus, httpStatus.value(), httpStatus.name(), code, message, new ArrayList<String>());
    }

    public XErrorResponse(HttpStatus httpStatus, Integer statusCode, String statusName, Integer code, String message) {
        this(httpStatus, statusCode, statusName, code, message, new ArrayList<String>());
    }

    public XErrorResponse(CustomRuntimeException cre) {
        this(cre.getHttpStatus(), cre.getStatusCode(), cre.getStatusName(), cre.getCode(), cre.getMessage(),
                new ArrayList<String>());
    }

    public XErrorResponse(HttpStatus httpStatus, Integer statusCode, String statusName, Integer code, String message,
            List<String> details) {
        this.httpStatus = httpStatus;
        this.date = new Date();
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.code = code;
        this.message = message;
        this.details = details;
        // ----------- Custom Exception: Start -----------
        data = null;
        errors = new ArrayList<>();
        String msgError = "Error code " + statusCode + " " + statusName + ": " + message;
        errors.add(new ErrorItem(msgError, new Extension(statusCode, statusName)));
        // ----------- Custom Exception: End -----------
    }

    // Getters & Setters
    // ----------- Custom Exception: Start -----------
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<ErrorItem> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorItem> errors) {
        this.errors = errors;
    }
    // ----------- Custom Exception: End -----------

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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    // Internal Objects
    // ----------- Custom Exception: Start -----------
    /**
     * ErrorItem
     *
     * @author Jhojan Arias
     * @since 1.0
     */
    class ErrorItem {

        private String message;

        private Extension extensions;

        public ErrorItem(String message, Extension extension) {
            this.message = message;
            this.extensions = extension;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Extension getExtensions() {
            return extensions;
        }

        public void setExtensions(Extension extensions) {
            this.extensions = extensions;
        }

    }

    /**
     * Extension
     *
     * @author Jhojan Arias
     * @since 1.0
     */
    class Extension {

        private Integer status;

        private String message;

        public Extension(Integer status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
    // ----------- Custom Exception: End -----------
}
