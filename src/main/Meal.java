package main;
/**
 * The class {@code Meal} provides a concrete implementation for a Meal object
 * @author donnp
 *
 */
public class Meal {
	private int id;
	private String name;
	private double price;
	
	/**
	 * Overloaded constructor
	 * @param name {@code String} name of the Meal
	 * @param id {@code int} unique mealId
	 * @param price {@code double} selling price of the Meal
	 */
	public Meal(String name, int id, double price){
		this.name = name;
		this.id = id;
		this.price = price;
	}
	
	
	public int GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public double GetPrice() {
		return price;
	}
	
	/**
	 * Returns a string representing the driver object
	 */
	public String toString() {
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += "R " + this.price;
		return result;
	}
}
