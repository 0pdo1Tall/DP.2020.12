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

	private static InterbankPayloadConverter interbankPayloadConverter = new InterbankPayloadConverter();
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();

	// Stamp Coupling
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
