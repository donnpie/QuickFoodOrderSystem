package main;
/**
 * The class {@code Location} provides a concrete implementation for a Location object
 * @author donnp
 *
 */
public class Location {
	private int locationId;
	private String location;
	
	/**
	 * Default constructor
	 */
	public Location() {
		location = new String("");
	}
	
	/**
	 * Overloaded constructor
	 * @param location {@code String} representing city name
	 */
	public Location(String location) {
		this.location = location;
	}
	
	public Location(int locationId, String location) {
		this.locationId = locationId;
		this.location = location;
	}
	
	/**
	 * Returns a string representing the object
	 */
	public String toString() {
		String result = new String(location);

		return result;
	}
}
