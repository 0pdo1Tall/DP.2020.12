package common.exception;

public class PaymentException extends RuntimeException {
	public PaymentException(String message) {
		super(message);
		
		// Data coupling, sử dụng phương thức để chuyển tham số vừa đủ cho phương thức khởi tạo
	}
}
