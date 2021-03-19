package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;
	
	//SOLID: Vi phạm nguyên lí OCP: Vì khi có loại Card mới thì phải sửa
	//SOLID: Vi phạm nguyên lí DIP: Bởi vì nó phụ thuộc vào một lớp cài đặt cụ thể là CreditCard chứ không phải là một abstract class đại diện cho mọi phương tiện thanh toán.
	public PaymentTransaction(String errorCode, CreditCard card, String transactionId, String transactionContent,
                              int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
