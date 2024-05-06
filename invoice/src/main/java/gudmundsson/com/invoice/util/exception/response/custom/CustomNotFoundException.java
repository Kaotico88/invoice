package gudmundsson.com.invoice.util.exception.response.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomNotFoundException captures the exception to be customized. Is used to
 * customize the body of the error response
 *
 * @author Elio Arias
 * @since 1.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public CustomNotFoundException(String message) {
        super(message);
    }
}
