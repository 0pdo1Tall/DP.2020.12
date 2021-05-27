package entity.shipping;

public class AlternativeCalculatorAdapter implements DistanceCalculatorInterface{

	NewDistanceCalculator newDistanceCalculator;
	
	public AlternativeCalculatorAdapter() {
		this.newDistanceCalculator = new NewDistanceCalculator();
	}
	
	@Override
	public int calculateDistance(String address, String province) {
		// TODO Auto-generated method stub
		String fullAddress = convertAddress(address, province);
		return newDistanceCalculator.calculateDistance(fullAddress);
	}
	
	public String convertAddress(String address, String province) {
		return province + address;
	}
}
