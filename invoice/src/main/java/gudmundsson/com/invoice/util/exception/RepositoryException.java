package gudmundsson.com.invoice.util.exception;

public class RepositoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
