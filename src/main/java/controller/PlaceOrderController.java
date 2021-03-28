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

//SOLID: vi phạm nguyên lí ISP, vì nó kế thừa lớp BaseController nhưng không dùng lại các hàm trong lớp đó 
public class PlaceOrderController extends BaseController {
	
	/**
	 * SOLID: Vi phạm nguyên lý SRP. Do class PlaceOrderController thực hiện nhiều hơn một nhiệm vụ.
	 * Class này vừa thực hiện chức năng đặt hàng placeOrder(), vừa thực hiện kiểm tra tính hợp lệ của dữ liệu validateDeliveryInfo.
	 */
	
	/**
	 * SOLID: Vi phạm nguyên lý OCP. Phương thức validateDeliveryInfo sẽ phải thay đổi code khi thêm các
	 * thuộc tính cần phải validate.
	 * 
	 * Ngoài ra trong phương thức processDeliveryInfo cũng vi phạm OCP do có phụ thuộc trực tiếp vào cấu trúc
	 * của info. Nếu tương lai cấu trúc info thay đổi, cũng sẽ dẫn tới phải modify lại phương thức của class này.
	 */
	
	/**
	 * SOLID: Vi phạm nguyên lý DIP. Phương thức processDeliveryInfo có phụ thuộc vào một phương thức.
	 * Phương thức cụ thể đó là DistanceCalculator(). Nếu sau này có thay đổi cách thức tính khoảng cách,
	 * ví dụ như sử dụng thư viện Alternative Distance Calculator, ta lại phải thay đổi do tính khoảng cách
	 * tại đang phụ thuộc vào một lớp được cài đặt cụ thể mà không phải là lớp trừu tượng.
	 * 
	 * Vi phạm DIP còn phụ thuộc thêm vào info trong processDeliveryInfo. Do phụ thuộc trực tiếp vào cấu trúc của
	 * info chứ không phải một class info trừu tượng hơn.
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
	 * Singleton:  Vì: mỗi lần đặt hàng thì mới sử dụng đến đối tượng của lớp này, 
	 * không cần thiết dùng tới nhiều đối tượng của lớp 
	 */
	private static PlaceOrderController placeOrderController = new PlaceOrderController();
    
	private PlaceOrderController() {}
    public static PlaceOrderController getInstance() {
    	return placeOrderController;
    }
    
    
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
    
    // SOLID: Vi phạm nguyên lí OCP. Vì nếu thêm một kiểu vận chuyển khác thì sẽ phải modify code
    // SOLID: Vi phạm nguyên lí DIP. Vì nó phụ thuộc vào lớp chi tiết DistanceCalculator
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
    
    // SOLID: Vi phạm nguyên lí OCP. Vì nếu muốn thay đổi hoặc thêm dữ liệu validate thì sẽ phải modify code 
    // Coincidental Cohesion. Do các hàm validate đang được đặt trong class không liên quan đến validate, nên để trong class ở utils
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
        || validateName(info.get("name"))
        || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
     
        //stamp coupling: do info có nhiều hơn 3 trường dl mà trong hàm chỉ sử dụng 3 trường name, phone, address
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
