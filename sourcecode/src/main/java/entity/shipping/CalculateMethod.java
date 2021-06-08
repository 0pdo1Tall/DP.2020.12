package entity.shipping;

import entity.order.Order;

public interface CalculateMethod {
	int calculateShippingFee(int distance, Order order);
}