package entity.payment;

import common.exception.*;

public class PaymentTransaction {
	private final String errorCode;
	private final PaymentMethod paymentMethod;
	private final String transactionId;
	private final String transactionContent;
	private final int amount;
	private final String createdAt;

	//SOLID: Vi ph?m nguy�n l� OCP: V� khi c� lo?i Card m?i th� ph?i s?a
	//SOLID: Vi ph?m nguy�n l� DIP: B?i v� n� ph? thu?c v�o m?t l?p c�i ??t c? th? l� CreditCard ch? kh�ng ph?i l� m?t abstract class ??i di?n cho m?i ph??ng ti?n thanh to�n.

	public PaymentTransaction(String errorCode, PaymentMethod paymentMethod, String transactionId, String transactionContent,
                              int amount, String createdAt){
		super();
		this.errorCode = errorCode;
		this.paymentMethod = paymentMethod;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public void checkValidTransaction()
	{
		switch (errorCode) {
			case "00":
				break;
			case "01":
				throw new InvalidCardException();
			case "02":
				throw new NotEnoughBalanceException();
			case "03":
				throw new InternalServerErrorException();
			case "04":
				throw new SuspiciousTransactionException();
			case "05":
				throw new NotEnoughTransactionInfoException();
			case "06":
				throw new InvalidVersionException();
			case "07":
				throw new InvalidTransactionAmountException();
			default:
				throw new UnrecognizedException();
		}
	}
}
