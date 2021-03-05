package main;
/**
 * The class {@code Driver} provides a concrete implementation for a Driver object
 * @author donnp
 *
 */
public class Driver {
	private int id;
	private String name;
	private int numOfDeliveries;
	private Location location;
	
	/**
	 * Default constructor
	 */
	public Driver() {
		this.name = "";
		this.numOfDeliveries = 0;
		this.location = new Location();
	}
	
	/**
	 * Overloaded constructor
	 * @param name {@code String} name of the Driver
	 * @param location {@code String} representing Driver's city
	 * @param numOfDeliveries number of deliveries still to be made
	 */
	public Driver(String name, String location, int numOfDeliveries) {
		this.name = name;
		this.numOfDeliveries = numOfDeliveries;
		this.location = new Location(location);
	}
	
	/**
	 * Overloaded constructor
	 * @param name {@code String} name of the Driver
	 * @param location {@code Location} object representing Driver's city
	 * @param numOfDeliveries number of deliveries still to be made
	 * @param id {@code int} unique driverId
	 */
	public Driver(String name, Location location, int numOfDeliveries, int id) {
		this.name = name;
		this.numOfDeliveries = numOfDeliveries;
		this.location = location;
		this.id = id;
	}
	
	
	public int GetId() {
		return id;
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
	
	/**
	 * Returns a string representing the driver object
	 */
	public String toString() {
		String result = new String(this.name + ", ");
		result += this.location.toString() + ", ";
		result += this.numOfDeliveries;
		return result;
	}
}
