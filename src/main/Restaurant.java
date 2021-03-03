package main;

public class Restaurant {
	private String name;
	private String telNumber;
	private Location location;
	int id;
	Menu menu = null;
	
	public Restaurant(String name, String telNumber, String location, int id) {
		this.name = name;
		this.telNumber =  telNumber;
		this.location = new Location(location);
		this.id = id;
	}
	
	public Restaurant(String name, String telNumber, Location location, int id) {
		this.name = name;
		this.telNumber =  telNumber;
		this.location = location;
		this.id = id;
	}

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
	
	public String toString() {
		//returns a string representing the driver object
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += this.location.toString();
		return result;
	}
	
	
	
	
	
	
}
