package company.test.Exception;

public class UnexpectedError  extends RuntimeException {
    public UnexpectedError(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedError(String message) {
        super(message);
    }
}
