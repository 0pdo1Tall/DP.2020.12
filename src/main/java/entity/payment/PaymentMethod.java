package entity.payment;

public abstract class PaymentMethod {

    private String type;
    private String cardCode;
    private String owner;

    public PaymentMethod(){};

    public PaymentMethod(String type,String cardCode,String owner){
        this.type = type;
        this.cardCode = cardCode;
        this.owner = owner;
    }
}
