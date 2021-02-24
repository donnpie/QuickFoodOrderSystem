package main;

public class Location {
	private String location;
	
	public Location() {
		location = "";
	}
	
	public Location(String location) {
		this.location = location;
	}
	
	public String toString() {
		//returns a string representing the object
		String result = new String(location);

		return result;
	}
}
