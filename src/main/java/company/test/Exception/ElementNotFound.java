package company.test.Exception;

public class ElementNotFound extends RuntimeException{
    public ElementNotFound(String message, Throwable cause){
        super(message, cause);
    }
    public ElementNotFound(String message){
        super(message);
    }
}
