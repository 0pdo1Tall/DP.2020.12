package common.exception;

public class NotEnoughTransactionInfoException extends PaymentException {
public NotEnoughTransactionInfoException() {
	super("ERROR: Not Enough Transcation Information");
	
	// Data coupling, truyền tham số vừa đủ để module khác xử lý
}
}
