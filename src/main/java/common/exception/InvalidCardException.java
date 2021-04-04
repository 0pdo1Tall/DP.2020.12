package common.exception;;

public class InvalidCardException extends PaymentException {
	public InvalidCardException() {
		super("ERROR: Invalid card!");
		
		// Data coupling, chuyển lại tham số một cách vừa đủ cho lớp trên
	}
}
