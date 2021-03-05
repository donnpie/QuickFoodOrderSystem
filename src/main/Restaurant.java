package main;
/**
 * The class {@code Restaurant} provides a concrete implementation for a Restaurant object
 * @author donnp
 *
 */
public class Restaurant {
	int id;
	private String name;
	private String telNumber;
	private Location location;
	Menu menu = null;
	
	/**
	 * Default constructor
	 */
	public Restaurant() {
		this.name = new String();
		this.telNumber =  new String();
		this.location = new Location();
		this.id = 0;
	}
	
	/**
	 * Overloaded constructor
	 * @param name {@code String} name of the Restaurant
	 * @param telNumber {@code String} contact number for the Restaurant
	 * @param location {@code String} representing Restaurant's city
	 * @param id {@code int} unique restaurantId
	 */
	public Restaurant(String name, String telNumber, String location, int id) {
		this.name = name;
		this.telNumber =  telNumber;
		this.location = new Location(location);
		this.id = id;
	}
	
	/**
	 * Overloaded constructor
	 * @param name {@code String} name of the Restaurant
	 * @param telNumber {@code String} contact number for the Restaurant
	 * @param location {@code Location} object representing Restaurant's city
	 * @param id {@code int} unique restaurantId
	 */ 
	public Restaurant(String name, String telNumber, Location location, int id) {
		this.name = name;
		this.telNumber =  telNumber;
		this.location = location;
		this.id = id;
	}

	/**
	 * Adds a {@code Meal} object to the list
	 * @param meal {@code Meal} object
	 */
	public void Add(Meal meal) {
		if (this.menu == null) {
			this.menu = new Menu();
		}
		menu.Add(meal);
	}
	
	public String GetName() {
		return name;
	}
	
	public int GetId() {
		return id;
	}
	
	
	public void SetMenu(Menu menu) {
		this.menu = menu;
		
	}
	
	public Menu GetMenu() {
		return menu;
	}
	
	public Location GetLocation() {
		return location;
	}
	
	public String GetTelNumber() {
		return telNumber;
	}
	
	/**
	 * Returns a string representing the driver object
	 */
	public String toString() {
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += this.location.toString();
		return result;
	}
}
