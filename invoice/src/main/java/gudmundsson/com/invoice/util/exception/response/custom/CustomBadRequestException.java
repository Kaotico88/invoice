package gudmundsson.com.invoice.util.exception.response.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomBadRequestException captures the exception to be customized. Is used to
 * customize the body of the error response
 *
 * @author Elio Arias
 * @since 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public CustomBadRequestException(String message) {
        super(message);
    }
}
