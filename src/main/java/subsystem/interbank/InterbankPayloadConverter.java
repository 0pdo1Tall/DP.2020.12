package subsystem.interbank;

import common.exception.*;
import entity.payment.PaymentMethod;
import entity.payment.PaymentMethodFactory;
import entity.payment.PaymentTransaction;
import utils.MyMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author
 */
public class InterbankPayloadConverter {

    /**
     * Convert from native entity into interbank required format
     * @param card
     * @param amount
     * @param contents
     * @return
     */
	
	/**
	 * Clean code: remove dependancy from concrete class (CreditCard)
	 * and change dependancy to superclass (PaymentMethod)
	 * is a abstract class
	 */

    // Content Coupling
    String convertToRequestPayload(PaymentMethod card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new InvalidCardException();
        }
        transaction.put("command", InterbankConfigs.PAY_COMMAND); // Content Coupling Here(cannot access PAY_COMMAND private member)
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", InterbankConfigs.VERSION); // Content Coupling Here(cannot access VERSION private member)
        requestMap.put("transaction", transaction);

        return ((MyMap) requestMap).toJSON();
    }

    /**
     * Read the response from interbank server
     * @param responseText
     * @return
     */
    // coincidental cohesion: read the response from interbank server do not relate to InterbankConverter module
    
    /* 
     * Clean code: Method Refactoring - Method Level. Vì: errorCode được lấy từ response nên cần kiểm tra luôn, nếu có lỗi các câu lệnh sau đó
     * không cần khởi tạo không cần thiết
     */
    
    PaymentTransaction extractPaymentTransaction(String responseText, PaymentMethodFactory paymentMethodFactory) {
        MyMap response = convertJSONResponse(responseText);

        if (response == null)
            return null;
        
        switch ( (String) response.get("errorCode")) {
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
        
        MyMap transaction = (MyMap) response.get("transaction");
        PaymentMethod card = paymentMethodFactory.createMethod(
                (String) transaction.get("cardCode"),
                (String) transaction.get("owner"),
                (String) transaction.get("dateExpired"),
                Integer.parseInt((String) transaction.get("cvvCode")));

        PaymentTransaction trans = new PaymentTransaction(
                (String) response.get("errorCode"),
                card,
                (String) transaction.get("transactionId"),
                (String) transaction.get("transactionContent"),
                Integer.parseInt((String) transaction.get("amount")),
                (String) transaction.get("createdAt"));
        // Clean Code: Method Refactoring - Change getError to checkValidTransaction And throws Exception
        try
        {
            trans.checkValidTransaction();
        }catch(Exception e){}

        return trans;
    }

    /**
     * Convert response from interbank server as JSON-formatted String into a proper Map
     * @param responseText
     * @return
     */

    private MyMap convertJSONResponse(String responseText) {
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }
        return response;
    }

    /**
     * Return a {@link String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
     *
     * @author hieudm
     * @return the current time as {@link String String}.
     */

    // Coincidental Cohesion: getToday do not relate to InterbankConverter module
    
    /* 
     * Clean code: Method Refactoring - Statement Level. Vì: khởi tạo thêm biến date = new Date() không cần thiết, mất thời gian compile
     */
    
    private String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
