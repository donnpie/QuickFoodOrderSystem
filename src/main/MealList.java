package main;
import java.util.ArrayList;
import java.util.List;
/**
 * The class {@code MealList} provides a array object that holds any number of Meal objects
 * @author donnp
 *
 */
public class MealList {
	List<MealLineItem> list = new ArrayList<MealLineItem>();
	
	/**
	 * Adds a {@code MealLineItem} object to the list
	 * @param mli {@code MealLineItem} object
	 */
	public void Add(MealLineItem mli) {
		list.add(mli);
	}
	
	/**
	 * Returns the number of {@code MealLineItem} objects in the list
	 * @return {@code int}
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Returns the ith {@code MealLineItem} object in the list
	 * @param i the list index, starting at 0
	 * @return {@code int}
	 */
	public int GetId(int i) {
		return list.get(i).GetId();
	}
	
	/**
	 *  Returns a {@code String} representing all {@code MealLineItem} objects in the list
	 */
	public String toString() {
		//returns a string representing all MealLineItem objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			MealLineItem item = list.get(i);
			result += item.GetQuantity() + " x ";
			result += item.GetName() + " ";
			result += "(R " + item.GetTotalAmount() + ")\n";
		}
		return result;
	}

	/**
	 * Calculates the total amount due
	 * The amount due is the sum of the products of meal price and quantity
	 * @return {@code double} total amount due
	 */
	public double CalcTotalAmount() {
		double result = 0;
		for (int i = 0; i < list.size(); i++) {
			MealLineItem item = list.get(i);
			result += item.GetTotalAmount();
		}
		return result;
	}
	
	/**
	 * Returns the ith {@code MealLineItem} object in the list
	 * @param index the list index, starting at 0
	 * @return {@code MealLineItem}
	 */
	public MealLineItem GetMealLineItemByIndex(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		}
		else return null;
	}
}
