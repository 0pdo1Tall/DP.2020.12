package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	//SOLID: Vi ph?m nguyên lí OCP: Vì khi có lo?i Card m?i thì ph?i s?a
	//SOLID: Vi ph?m nguyên lí DIP: B?i vì nó ph? thu?c vào m?t l?p cài ??t c? th? là CreditCard ch? không ph?i là m?t abstract class ??i di?n cho m?i ph??ng ti?n thanh toán.

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
