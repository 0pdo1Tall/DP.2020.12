package common.exception;;

public class SuspiciousTransactionException extends PaymentException {
	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
		
		// Data coupling, chuyển tham số cho phương thức khác vừa đủ để xử lý
	}
}
