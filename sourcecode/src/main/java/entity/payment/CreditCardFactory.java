package entity.payment;

import org.w3c.dom.CDATASection;

public class CreditCardFactory extends PaymentMethodFactory{

    private static CreditCardFactory creditCardFactory;
    private CreditCardFactory(){}

    public static CreditCardFactory getInstance(){
        if(creditCardFactory == null) creditCardFactory = new CreditCardFactory();
        return creditCardFactory;
    }

	@Override
	public PaymentMethod createMethod(String cardCode, String owner, String dateExpired, int cvvCode) {
		// TODO Auto-generated method stub
		return new CreditCard(cardCode,owner, dateExpired,cvvCode);
	}

    // Credit Card Method Here
}
