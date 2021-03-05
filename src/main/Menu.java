package main;
import java.util.ArrayList;
import java.util.List;
/**
 * The class {@code Menu} provides a array object that holds any number of Meal objects
 * A Menu is unique to a specific Restaurant and contains all the Meals offered by that Restaurant
 * @author donnp
 *
 */
public class Menu {
	List<Meal> list = new ArrayList<Meal>();
	
	/**
	 * Adds a {@code Meal} object to the list
	 * @param m {@code Meal} object
	 */
	public void Add(Meal m) {
		list.add(m);
	}
	
	/**
	 * Find and returns meal by id
	 * @param id
	 * @return
	 */
	public Meal GetMealById(int id) {
		for (int i = 0; i < list.size(); i++) {
			Meal m = list.get(i);
			if (m.GetId() == id) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Returns the number of {@code Meal} objects in the list
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Returns the ith {@code Meal} object in the list
	 * @param i the list index, starting at 0
	 * @return
	 */
	public int GetId(int i) {
		/**
		 * Return the id for meal at index position i
		 */
		return list.get(i).GetId();
	}
		
	/**
	 * Returns a string representing all Meal objects
	 */
	public String toString() {
		//
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
}//public class Menu
