package common.exception;

public class InternalServerException extends PaymentException {
    public InternalServerException(String message) {
        super(message);
        
        // Data coupling, gọi phương thức khác và truyền tham số không bị thừa
    }
}
