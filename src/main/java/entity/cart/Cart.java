package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

// Card  lÃ  Ä‘á»‘i tÆ°á»£ng duy nháº¥t nÃªn ta cÃ³ thÃª Ã¡p dá»¥ng singleton cho nÃ³ nhÆ° sau:

public class Cart {
    
    private static Cart instance;

    private List<CartItem> lstCartItem;

	private Cart(){
	    lstCartItem = new ArrayList<>();
	};
	
	public static Cart getCard(){
	    if(instance == null){
	        instance = new Cart();
	    }
	    
	    return instance;
	}

    // public Cart() {
    //     lstCartItem = new ArrayList<>();
    // }


    // data coupling
	/*
	 * Clean code. sửa biến cm => cartItem
	 */
    public void addCartMedia(CartItem cartItem){
        lstCartItem.add(cartItem);
    }

    // data coupling
    public void removeCartMedia(CartItem cartItem){
        lstCartItem.remove(cartItem);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){

        // Clean Code: change total to totalMediaQuantity, obj to cartItemObject, cm to cartItem
        int totalMediaQuantity = 0;
        for (Object cartItemObject : lstCartItem) {
            CartItem cartItem = (CartItem) cartItemObject;
            totalMediaQuantity += cartItem.getQuantity();
        }
        return totalMediaQuantity;
    }

    public int calSubtotal(){

        // Clean Code: change total to totalMediaQuantity, obj to cartItemObject, cm to cartItem
        int totalMediaQuantity = 0;
        for (Object cartItemObject : lstCartItem) {
            CartItem cartItem = (CartItem) cartItemObject;
            totalMediaQuantity += cartItem.getPrice()*cartItem.getQuantity();
        }
        return totalMediaQuantity;
    }

    //SRP: PhÆ°Æ¡ng thá»©c nÃ y cÃ³ thá»ƒ chia ra má»™t class khÃ¡c
    public void checkAvailabilityOfProduct() throws SQLException{
        // Clean Code: change allAvailable to allProductAvailable, object to cartItemObject.availQuantity to availableQuantity
        boolean allProductAvailable = true;
        for (Object cartItemObject : lstCartItem) {
            CartItem cartItem = (CartItem) cartItemObject;
            int requiredQuantity = cartItem.getQuantity();
            int availableQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availableQuantity) allProductAvailable = false;
        }
        if (!allProductAvailable) throw new MediaNotAvailableException("Some media not available");
    }

    // stamp coupling
    public CartItem checkMediaInCart(Media media){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
        }
        return null;
    }

    //communicational cohesion: cÃ¡c phÆ°Æ¡ng thá»©c dÃ¹ng dung thuá»™c tÃ­nh lstCartItem
}
