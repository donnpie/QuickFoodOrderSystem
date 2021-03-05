package main;
/**
 * The class {@code MealLineItem} provides a concrete implementation for a MealLineItem object.
 * A MealLineItem represents one line on an invoice, consisting of a meal and a quantity
 * @author donnp
 *
 */
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
	
	/**
	 * Returns the unit price of a MealLineItem
	 * @return
	 */
	public double GetPrice() {
		return meal.GetPrice();
	}

	public double GetTotalAmount() {
		return quantity * meal.GetPrice();
	}
	
	/**
	 * Returns a string representing the driver object
	 */
	public String toString() {
		String result = new String(this.meal.toString() + ", ");
		result += this.quantity;
		return result;
	}

	public int GetId() {
		return this.meal.GetId();
	}
	
}
