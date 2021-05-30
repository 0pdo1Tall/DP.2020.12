package entity.order;

public abstract class State {

    protected Order order;
    State(Order order){
        this.order = order;
    }

    abstract void changeState();
    abstract void cancelOrder();
}
