package entity.shipping;

import org.example.DistanceCalculator;

public class OldDistanceCalculator implements DistanceCalculatorInterface{

	protected DistanceCalculator distanceCalculator;
	
	public OldDistanceCalculator() {
		this.distanceCalculator = new DistanceCalculator();
	}

	@Override
	public int calculateDistance(String address, String province) {
		// TODO Auto-generated method stub
		return distanceCalculator.calculateDistance(address, province);
	} 
	
}
