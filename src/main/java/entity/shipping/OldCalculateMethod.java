package entity.shipping;

public class OldCalculateMethod implements CalculateMethod{
  static final double MULTIPLIER = 1.2;
  
  public int calculateShippingFee(int distance){
    return (int) (distance * MULTIPLIER);
  }
}