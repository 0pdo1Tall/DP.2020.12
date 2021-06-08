package common.exception;

public class UnrecognizedException extends RuntimeException {
	public UnrecognizedException() {
		super("ERROR: Something went wrowng!");
		
		// Data coupling, chỉ truyền tham số cần thiết lên module khác
	}
}
