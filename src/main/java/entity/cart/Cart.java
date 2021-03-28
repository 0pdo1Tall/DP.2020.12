package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

// Card  là đối tượng duy nhất nên ta có thê áp dụng singleton cho nó như sau:

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
    public void addCartMedia(CartItem cm){
        lstCartItem.add(cm);
    }

    // data coupling
    public void removeCartMedia(CartItem cm){
        lstCartItem.remove(cm);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    //SRP: Phương thức này có thể chia ra một class khác
    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }

    // stamp coupling
    public CartItem checkMediaInCart(Media media){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
        }
        return null;
    }

    //communicational cohesion: các phương thức dùng dung thuộc tính lstCartItem
}
