package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private PaymentMethod paymentMethod;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	//SOLID: Vi pham nguyen lí OCP: Vi khi co loai Card moi can chinh sua code
	//SOLID: Vi pham nguyen li DIP: Boi vi no phu thuoc vao mot lop cai dat cu the la CreditCard chu khong phai la mot Abstract Class dai dien cho phuong thuc thanh toan

	public PaymentTransaction(String errorCode, PaymentMethod paymentMethod, String transactionId, String transactionContent,
                              int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.paymentMethod = paymentMethod;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
