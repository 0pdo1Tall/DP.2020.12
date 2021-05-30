package entity.order;

public class WaitingState extends State{

    WaitingState(Order order) {
        super(order);
    }

    @Override
    void changeState() {
        // Change to ApprovedState or CanceledState
    }

    @Override
    void cancelOrder() {
        // Do something
    }
}

