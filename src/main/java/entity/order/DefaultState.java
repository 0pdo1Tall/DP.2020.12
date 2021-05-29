package entity.order;

public class DefaultState extends State{

    DefaultState(Order order) {
        super(order);
    }

    @Override
    void changeState() {
        // change to waiting state
    }

    @Override
    void cancelOrder() {
        // Do something
    }
}
