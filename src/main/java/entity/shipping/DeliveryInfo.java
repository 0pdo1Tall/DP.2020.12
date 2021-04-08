package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;

public class DeliveryInfo {

	/*
	 * Clean code. Định nghĩa hệ số nhân MULTIPLIER
	 */
	static final double MULTIPLIER = 1.2;
    //Common coupling
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;  


    // data coupling
    //SOLID: Vi pháº¡m nguyÃªn lÃ­ OCP: vÃ¬ náº¿u má»Ÿ rá»™ng theo yÃªu cáº§u, ta sáº½ pháº£i Ä‘á»•i phÆ°Æ¡ng thá»©c tÃ­nh khoáº£ng cÃ¡ch khÃ¡c, vÃ  nhÆ° tháº¿ pháº£i modify láº¡i pháº§n mÃ£ nguá»“n cá»§a class nÃ y
	//SOLID: Vi pháº¡m nguyÃªn lÃ­ DIP: Phá»¥ thuá»™c vÃ o DistanceCalculator

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    // Stamp coupling: DÃ™ng Order lÃ m tham sá»‘ truyá»�n vÃ o nhÆ°ng khÃ´ng sá»­ dá»¥ng (háº¿t) thuá»™c tÃ­nh
    public int calculateShippingFee(Order order) {
    	
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * MULTIPLIER);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }

    //communicational cohesion: má»™t vÃ i phÆ°Æ¡ng thá»©c dÃ¹ng dung thuá»™c tÃ­nh
}
