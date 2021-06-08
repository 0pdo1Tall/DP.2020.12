package entity.payment;

import common.exception.*;

public class PaymentTransaction {
	private final String errorCode;
	private final PaymentMethod paymentMethod;
	private final String transactionId;
	private final String transactionContent;
	private final int amount;
	private final String createdAt;

	//SOLID: Vi ph?m nguyên lí OCP: Vì khi có lo?i Card m?i thì ph?i s?a
	//SOLID: Vi ph?m nguyên lí DIP: B?i vì nó ph? thu?c vào m?t l?p cài ??t c? th? là CreditCard ch? không ph?i là m?t abstract class ??i di?n cho m?i ph??ng ti?n thanh toán.

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
