package main;

import java.util.Comparator;

public class Customer {
	private String name;
	private String telNumber;
	private String email;
	private String streetAddress;
	private String suburb;
	private Location location;
	private int id;
	
	public Customer() {
		name = "";
		telNumber = "";
		email = "";
		location = new Location();
		id = 0;
		streetAddress = "";
		suburb = "";
	}
	
	public Customer(String name, String telNumber, String email, String streetAddress, String suburb, String location, int id) {
		this.name = name;
		this.telNumber = telNumber;
		this.email = email;
		this.location = new Location(location);
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
	
	//Comparator for sorting by Customer location
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
	
	
	public String locationAndNameToString() {
		//returns a string with customer name and location
		String result = new String("Location: " + this.GetLocation().toString() + ", ");
		result += "Customer: " + this.GetName() + "\n";
		return result;
	}
	
	public String toString() {
		//returns a string representing the Customer object
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += this.location.toString();
		return result;
	}

}
