package common.exception;

public class InvalidTransactionAmountException extends PaymentException {
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
		
		// Data coupling, truyền vừa đủ tham số
	}
}
