package entity.shipping;

import entity.order.Order;

public class OldCalculateMethod implements CalculateMethod{
	private static final double MULTIPLIER = 1.2;
	
	public int calculateShippingFee(int distance, Order order) {
		return (int)(distance * MULTIPLIER);
	}
}
