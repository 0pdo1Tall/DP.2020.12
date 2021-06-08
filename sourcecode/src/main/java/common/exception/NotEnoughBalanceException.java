package common.exception;

public class NotEnoughBalanceException extends PaymentException{

	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
		
		// Data coupling, chỉ truyền đúng tham số cho phương thức khởi tạo
	}

}
