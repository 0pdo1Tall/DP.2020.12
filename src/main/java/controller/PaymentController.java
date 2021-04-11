package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.cart.Cart;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 * 
 * @author hieud
 *
 */


//SOLID: vi phạm nguyên lí ISP, vì nó kế thừa lớp BaseController nhưng không dùng lại các hàm trong lớp đó 

public class PaymentController extends BaseController {
	
	/**
	 * Singleton: liên quan đến thanh toán --> chỉ cần 1 controller như vậy để sử dụng trong quá trình tạo hóa đơn và thanh toán
	 * mỗi hóa đơn và thanh toán là hoàn toàn riêng biệt, chỉ cần sử dụng một controller để giải quyết
	 */
	
	private static PaymentController _paymentControllerInstance;
	
	private PaymentController() {
		
	}
	
	public static synchronized PaymentController getInstance() {
		if (_paymentControllerInstance == null) {
			_paymentControllerInstance = new PaymentController();
		}
		return _paymentControllerInstance;
	}
	
	/**
	 * SOLID: Vi phạm nguyên lý SRP. Do class thực hiện nhiều hơn một nhiệm vụ.
	 * Cụ thể nó vừa thực hiện kiểm tra hạn qua phương thức getExpirationDate, vừa thực hiện thanh toán qua phương thức payOrder.
	 * Cũng vừa làm rỗng giỏ hàng qua phương thức emptyCart()
	 */
	
	/**
	 * SOLID: Vi phạm OCP. Do payOrder chỉ có thanh toán theo Credit Card.
	 * Do đó nếu thêm phương thức thanh toán thì phải sửa code phần này.
	 */
	
	/**
	 * SOLID: Vi phạm nguyên lý DIP. Do phương thức payOrder() phụ thuộc vào một lớp cài đặt cụ thể là CreditCard.
	 * Vì thế nếu như thêm một phương thức thanh toán khác, thẻ nội địa Domestic Card thì phải cài đặt lại hàm này.
	 */
	
	/**
	 * Coincidental cohesion, lớp PaymentController có 3 phương thức không liên quan tới nhau,
	 * phương thức getExpirationDate(Date date) nên được để sang một lớp khác vì chỉ xử lý đến kiểm tra ngày tháng
	 * phương thức emptyCart() thì nên để bên ViewCartController xử lý, vì chỉ có Cart mới có quyền quản lý việc làm trống giỏ hàng
	 */
	
	
	/**
	 * Represent the card used for payment
	 */
	private CreditCard card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link String String} represents the input date
	 * @return {@link String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	// Coincidental cohension. Nên tách riêng ra class riêng
	// SOLID: SRP ko lien quan den chuc nang cua Payment Controller
	
	/*
	 * Clean code: Cần thay đổi strs => dateSplit
	 */
	private String getExpirationDate(String date) throws InvalidCardException {

		// Clean Code: change strs to dateString
		String[] dateString = date.split("/");
		if (dateString.length != 2) {
			throw new InvalidCardException();
		}

		String expirationDate = null;
		int month = -1;
		int year = -1;

		try {
			month = Integer.parseInt(dateString[0]);
			year = Integer.parseInt(dateString[1]);
			if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
				throw new InvalidCardException();
			}
			expirationDate = dateString[0] + dateString[1];
		} catch (Exception ex) {
			throw new InvalidCardException();
		}

		return expirationDate;
		
		// Data coupling, chỉ sử dụng dữ liệu vừa đủ để xử lý kết quả
	}

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
	
	// SOLID: Vi phạm nguyên lí OCP. Vì nếu thêm 1 phương thức thanh toán sẽ phải modify lại mã nguồn
	// SOLID: Vi phạm nguyên lí DIP. Vì nó phụ thuộc vào lớp chi tiết InterbankSubsystem
	// SOLID: DIP do phu thuoc vaf CreditCard ko phai la Abstract/Interface
	public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {
			this.card = new CreditCard(
					cardNumber,
					cardHolderName,
					getExpirationDate(expirationDate),
					Integer.parseInt(securityCode));

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);

			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
		
		// Data coupling, sử dụng tất cả dữ liệu truyền vào để xử lý quá trình
	}

	public void emptyCart(){
        //SessionInformation.cartInstance.emptyCart();
        // Data coupling, sử dụng một phương thức khác để yêu cầu
        Cart.getCard().emptyCart();
    }
}