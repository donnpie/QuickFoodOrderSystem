package main;
import java.util.Comparator;

/**
 * The class {@code Customer} provides a concrete implementation for a Customer object
 * @author donnp
 *
 */
public class Customer {
	private int id;
	private String name;
	private String telNumber;
	private String email;
	private String streetAddress;
	private String suburb;
	private Location location;
	
	/**
	 * Default constructor
	 */
	public Customer() {
		name = "";
		telNumber = "";
		email = "";
		location = new Location();
		id = 0;
		streetAddress = "";
		suburb = "";
	}
	
	/**
	 * Overloaded constructor, taking in a String object to define the location
	 * @param name {@code String} name of the customer
	 * @param telNumber {@code String} contact number for the customer
	 * @param email {@code String} email address
	 * @param streetAddress {@code String} street number and name eg 12 Raven Rd
	 * @param suburb {@code String} suburb where customer is located
	 * @param location {@code String} representing customer's city
	 * @param id {@code int} unique customerId
	 */
	public Customer(String name, String telNumber, String email, String streetAddress, String suburb, String location, int id) {
		this.name = name;
		this.telNumber = telNumber;
		this.email = email;
		this.location = new Location(location);
		this.id = id;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
	}
	
	/**
	 * Overloaded constructor, taking in a Location object
	 * @param name {@code String} name of the customer
	 * @param telNumber {@code String} contact number for the customer
	 * @param email {@code String} email address
	 * @param streetAddress {@code String} street number and name eg 12 Raven Rd
	 * @param suburb {@code String} suburb where customer is located
	 * @param location {@code Location} object representing customer's city
	 * @param id {@code int} unique customerId
	 */
	public Customer(String name, String telNumber, String email, String streetAddress, String suburb, Location location, int id) {
		this.name = name;
		this.telNumber = telNumber;
		this.email = email;
		this.location = location;
		this.id = id;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
	}
	
	public int GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetEmail() {
		return email;
	}
	
	public String GetTelNumber() {
		return telNumber;
	}
	
	public Object GetLocation() {
		return location;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public String getSuburb() {
		return suburb;
	}
	
	/**
	 * Comparator for sorting by Customer location
	 */
	public static Comparator<Customer> custLocationComparator = new Comparator<Customer>() {
		
		public int compare(Customer c1, Customer c2) {
			String custLoc1 = c1.GetLocation().toString().toLowerCase();
			String custLoc2 = c2.GetLocation().toString().toLowerCase();
			
			//ascending order
			return custLoc1.compareTo(custLoc2); 
			
			//descending order
			//return custName2.compareTo(custName1); 
		}
	};
	
	/**
	 * Returns a string with customer name and location
	 * @return
	 */
	public String locationAndNameToString() {
		String result = new String("Location: " + this.GetLocation().toString() + ", ");
		result += "Customer: " + this.GetName() + "\n";
		return result;
	}
	
	/**
	 * Returns a string representing the Customer object
	 */
	public String toString() {
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += this.telNumber + ", ";
		result += this.email + ", ";
		result += this.streetAddress + ", ";
		result += this.suburb + ", ";
		result += this.location.toString();
		return result;
	}

}
