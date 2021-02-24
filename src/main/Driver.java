package main;

public class Driver {
	private String name;
	private int numOfDeliveries;
	private Location location;
	
	public Driver() {
		this.name = "";
		this.numOfDeliveries = 0;
		this.location = new Location();
	}
	
	public Driver(String name, String location, int numOfDeliveries) {
		this.name = name;
		this.numOfDeliveries = numOfDeliveries;
		this.location = new Location(location);
	}
	
	public String GetName() {
		return name;
	}
	
	public Location GetLocation() {
		return location;
	}
	
	public int GetNumOfDeliveries() {
		return numOfDeliveries;
	}
	
	public void IncrementNumOfDeliveries() {
		numOfDeliveries++;
	}
	
	
	public String toString() {
		//returns a string representing the driver object
		String result = new String(this.name + ", ");
		result += this.location.toString() + ", ";
		result += this.numOfDeliveries;
		return result;
	}
	
	
	
}
