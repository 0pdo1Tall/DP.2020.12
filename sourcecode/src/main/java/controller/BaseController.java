package controller;

import java.sql.SQLException;
import java.util.List;

import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */

public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    public CartItem checkMediaInCart(Media media){
        
        // Data coupling, chỉ gọi phương thức cần
        return Cart.getCart().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List getListCartMedia(){
        //return SessionInformation.cartInstance.getListMedia();
        // Data coupling, gọi phương thức cần thiết để lấy dữ liệu
        return Cart.getCart().getListMedia();
    }
    
    /**
     * Clean code: Clean class - Restruct class: Pull up method
     * Method checkAvailabilityOfProduct() in class ViewCartController 
     * and method placeOrder() in class PlaceOrderController do the same thing
     * and has different names
     * 
     * Move methods into superclass
     */
    public void checkAvailabilityOfProduct() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }
}
