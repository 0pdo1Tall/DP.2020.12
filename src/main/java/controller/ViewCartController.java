package controller;

import java.sql.SQLException;

import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 * @author nguyenlm
 */


public class ViewCartController extends BaseController{
    
    /**
     * This method checks the available products in Cart
     * @throws SQLException
     */
	
	/** 
	 * Singleton:  Vì: chỉ cần 1 đối tượng thuộc ViewCartController bởi vì nó quản lý giỏ hàng (cart) của phiên (session) đó, 
	 * cho nên dù có khởi tạo nhiều đối tượng cũng không làm tăng thêm hiệu suất sử dụng, lãng phí tài nguyên 
	 */
	private static ViewCartController viewCartController = new ViewCartController();
    
	private ViewCartController() {}
	
    public static ViewCartController getInstance() {
    	return viewCartController;
    }
    
    public void checkAvailabilityOfProduct() throws SQLException{
        //SessionInformation.cartInstance.checkAvailabilityOfProduct();     
        // Data coupling, gọi đến phương thức cần thiết để thực hiện
        Cart.getCard().checkAvailabilityOfProduct();
    }
    
    /**
     * This method calculates the cart subtotal
     * @return subtotal
     */
    public int getCartSubtotal(){
        /**
         * Clean Code: Method Refactoring - Data-Level Refactoring --> Move an expression inline
         * remove local variable cartSubtotal
         */
        // Clean Code: Change subtotal to cartSubtotal
        return Cart.getCard().calSubtotal();
        
        // Data coupling, lấy dữ liệu cần thiết thông qua phương thức được cung cấp
    }


}
