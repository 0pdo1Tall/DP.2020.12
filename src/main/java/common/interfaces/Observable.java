package common.interfaces;

/**
 * @author
 */

/*
 * Functional Programming, vì không rơi vào những loại khác
 */
public interface Observable {

    void attach(Observer observer);
    void remove(Observer observer);
    void notifyObservers();

}
