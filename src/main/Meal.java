package main;

public class Meal {
	private String name;
	private int id;
	private double price;
	
	
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
	
	public String toString() {
		//returns a string representing the driver object
		String result = new String(this.id + ", ");
		result += this.name + ", ";
		result += "R " + this.price;
		return result;
	}
}
