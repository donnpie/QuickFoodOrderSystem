package main;

public class Restaurant {
	private String name;
	private String telNumber;
	private Location location;
	int id;
	Menu menu = new Menu();
	
	public Restaurant(String name, String telNumber, String location, int id) {
		this.name = name;
		this.telNumber =  telNumber;
		this.location = new Location(location);
		this.id = id;
	}

	public void Add(Meal meal) {
		menu.Add(meal);
	}
	
	public String GetName() {
		return name;
	}
	
	public int GetId() {
		return id;
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
	
	public String toString() {
		//returns a string representing the driver object
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += this.location.toString();
		return result;
	}
	
	
	
	
	
	
}
