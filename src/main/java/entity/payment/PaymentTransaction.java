package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private CreditCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	//SOLID: Vi ph?m nguy�n l� OCP: V� khi c� lo?i Card m?i th� ph?i s?a
	//SOLID: Vi ph?m nguy�n l� DIP: B?i v� n� ph? thu?c v�o m?t l?p c�i ??t c? th? l� CreditCard ch? kh�ng ph?i l� m?t abstract class ??i di?n cho m?i ph??ng ti?n thanh to�n.

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
