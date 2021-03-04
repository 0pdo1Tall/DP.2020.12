package common.exception;;

public class InternalServerErrorException extends PaymentException {

	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
		
		// Data coupling, gọi phương thức khác với tham số vừa đủ.
	}

}
