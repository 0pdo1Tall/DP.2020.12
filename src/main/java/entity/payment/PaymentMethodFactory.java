package entity.payment;

public abstract class PaymentMethodFactory {

    public abstract PaymentMethod createMethod(String cardCode, String owner, String dateExpired, int cvvCode);
}
