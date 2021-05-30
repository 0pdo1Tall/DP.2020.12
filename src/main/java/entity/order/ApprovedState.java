package entity.order;

public class ApprovedState extends State{

    ApprovedState(Order order) {
        super(order);
    }

    @Override
    void changeState() {
        // Change to Delivery State
    }

    @Override
    void cancelOrder() {
        // Do something
    }
}
