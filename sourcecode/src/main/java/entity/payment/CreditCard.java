package entity.payment;

/**
 * @author
 */
public class CreditCard extends PaymentMethod{

//    private String cardCode;
//    private String owner;
//    private String dateExpired;
//    protected int cvvCode;           //common coupling
    private final String dateExpired;
    protected int cvvCode;

    // data coupling
    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        super("Credit Card",cardCode,owner);
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
