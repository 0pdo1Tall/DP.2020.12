package entity.shipping;

import entity.order.Order;

public interface CalculateMethod {
	public int calculateShippingFee(int distance, Order order);
}
