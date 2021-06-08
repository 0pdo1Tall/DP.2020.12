package subsystem.interbank;

import entity.payment.PaymentMethod;
import entity.payment.PaymentMethodFactory;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {
	
	/**
	 * Clean code: remove dependancy from concrete class (CreditCard)
	 * and change dependancy to superclass (PaymentMethod)
	 * is a abstract class
	 */

	private static final InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static final InterbankBoundary interbankBoundary = new InterbankBoundary();

	// Stamp Coupling
	// Clean Class: if we have another payment method: ie: domestic card ==> we have to change refund and payOrder to handle PaymentMethod instead of CreditCard
	public PaymentTransaction refund(PaymentMethod card, int amount, String contents) {

		return null;
	}

	// Data Coupling
	public PaymentTransaction payOrder(PaymentMethod card, int amount, String contents, PaymentMethodFactory paymentMethodFactory) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText, paymentMethodFactory);
	}

}
