package entity.cart;

import entity.media.Media;

public class CartItem {
    
    private Media media;
    private int quantity;
    private int price;

    public CartItem(){

    }

    // data coupling
    public CartItem(Media media, Cart cart, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Media getMedia() {
        return this.media;
    }

    // data coupling
    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // data coupling
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    // data coupling
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" 
            + " media='" + media + "'" 
            + ", quantity='" + quantity + "'" 
            + "}";
    }

    //communicational cohesion: các phương thức dùng dung thuộc tính

}

    
