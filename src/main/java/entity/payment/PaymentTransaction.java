package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private PaymentMethod paymentMethod;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	//SOLID: Vi ph?m nguy�n l� OCP: V� khi c� lo?i Card m?i th� ph?i s?a
	//SOLID: Vi ph?m nguy�n l� DIP: B?i v� n� ph? thu?c v�o m?t l?p c�i ??t c? th? l� CreditCard ch? kh�ng ph?i l� m?t abstract class ??i di?n cho m?i ph??ng ti?n thanh to�n.

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
