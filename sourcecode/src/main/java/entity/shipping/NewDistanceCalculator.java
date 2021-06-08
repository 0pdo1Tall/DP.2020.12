package entity.shipping;

import org.example.AlternativeDistanceCalculator;

public class NewDistanceCalculator {

 	protected AlternativeDistanceCalculator alternativeDistanceCalculator;
	
	public NewDistanceCalculator() {
		this.alternativeDistanceCalculator = new AlternativeDistanceCalculator();
	}
	
	public int calculateDistance(String fullAddress) {
		return alternativeDistanceCalculator.calculateDistance(fullAddress);
	}
}
