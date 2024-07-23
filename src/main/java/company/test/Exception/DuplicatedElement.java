package company.test.Exception;

public class DuplicatedElement extends RuntimeException {
    public DuplicatedElement(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedElement(String message) {
        super(message);
    }
}
