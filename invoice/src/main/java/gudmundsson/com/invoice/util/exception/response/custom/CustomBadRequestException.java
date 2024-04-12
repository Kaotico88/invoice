package gudmundsson.com.invoice.util.exception.response.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public CustomBadRequestException(String message) {
        super(message);
    }
}
