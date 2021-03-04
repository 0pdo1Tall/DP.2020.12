package common.exception;;

public class InvalidVersionException extends PaymentException{
	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
		
		// Data coupling, truyền tham số không dư thừa cho lời gọi đến phương thức của lớp khác
	}
}
