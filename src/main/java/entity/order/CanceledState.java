package entity.order;

public class CanceledState extends State{

    CanceledState(Order order) {
        super(order);
    }

    @Override
    void changeState() {
        // Change to Default State
    }

    @Override
    void cancelOrder() {
        // Handling cancelOrder include: clear order info + refund
    }
}
