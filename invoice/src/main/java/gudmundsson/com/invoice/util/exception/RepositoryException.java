package gudmundsson.com.invoice.util.exception;

/**
 * Indicates something went wrong while accessing the underlying database
 * system.
 *
 * @author Elio Arias
 * @since 1.0
 */
public class RepositoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
