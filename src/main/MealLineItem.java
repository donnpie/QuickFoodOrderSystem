package main;

public class MealLineItem {
	Meal meal;
	int quantity;
	
	public void SetMeal(Meal m) {
		this.meal = m;
	}
	
	public void SetQuantity(int q) {
		this.quantity = q;
	}

	public String GetName() {
		return meal.GetName();
	}

	public int GetQuantity() {
		return quantity;
	}
	
	public double GetPrice() {
		//Returns the unit price of a MealLineItem
		return meal.GetPrice();
	}

	public double GetTotalAmount() {
		return quantity * meal.GetPrice();
	}
	
	public String toString() {
		//returns a string representing the driver object
		String result = new String(this.meal.toString() + ", ");
		result += this.quantity;
		return result;
	}
	
}
