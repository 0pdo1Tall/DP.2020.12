package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderItem;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;
import org.example.DistanceCalculator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController {
	
	/**
	 * SOLID: Vi phạm nguyên lý SRP. Do class PlaceOrderController thực hiện nhiều hơn một nhiệm vụ.
	 * Class này vừa thực hiện chức năng đặt hàng placeOrder(), vừa thực hiện kiểm tra tính hợp lệ của dữ liệu validateDeliveryInfo.
	 */
	
	/**
	 * SOLID: Vi phạm nguyên lý OCP. Phương thức validateDeliveryInfo sẽ phải thay đổi code khi thêm các
	 * thuộc tính cần phải validate.
	 */
	
	/**
	 * SOLID: Vi phạm nguyên lý DIP. Phương thức processDeliveryInfo có phụ thuộc vào một phương thức.
	 * Phương thức cụ thể đó là DistanceCalculator(). Nếu sau này có thay đổi cách thức tính khoảng cách,
	 * ví dụ như sử dụng thư viện Alternative Distance Calculator, ta lại phải thay đổi do tính khoảng cách
	 * tại đang phụ thuộc vào một lớp được cài đặt cụ thể mà không phải là lớp trừu tượng.
	 */
	
	/**
	 * Coincidental cohesion, do các phương thức validate không thực hiện chức năng cho PlaceOrderController
	 * chúng nên nằm trong một module khác xử lý về kiểm tra tính hợp lệ của dữ liệu
	 */
	
	
    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        SessionInformation.cartInstance.checkAvailabilityOfProduct();
        
        // Data coupling, gọi đến phương thức để lấy dữ liệu cần thiết
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException {
        return new Order(SessionInformation.cartInstance);
        
        // Data coupling, gọi đến phương thức để lấy dữ liệu
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
        
        // Data coupling, truy cập thông tin hợp lệ
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
        || validateName(info.get("name"))
        || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) return false;
        if (!phoneNumber.startsWith("0")) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
        
        // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
    }
    
    public boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
        
        // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
    }
    
    public boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        String patternString = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
        
        // Data coupling, sử dụng vừa đủ dữ liệu để xử lý
    }
}
