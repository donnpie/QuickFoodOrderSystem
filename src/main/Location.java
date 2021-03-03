package main;

public class Location {
	private int locationId;
	private String location;
	
	public Location() {
		location = "";
	}
	
	public Location(String location) {
		this.location = location;
	}
	
	public Location(int locationId, String location) {
		this.locationId = locationId;
		this.location = location;
	}
	
	public String toString() {
		//returns a string representing the object
		String result = new String(location);

		return result;
	}
}
