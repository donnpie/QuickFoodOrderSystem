package main;
import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code RestaurantList} provides a array object that holds any number of Restaurant objects
 * @author donnp
 *
 */
public class RestaurantList {
	private List<Restaurant> list = new ArrayList<Restaurant>();
	
	
	public RestaurantList() {
		//Currently empty
	}

	/**
	 * Adds a {@code Restaurant} object to the list
	 * @param rest {@code Restaurant} object
	 */
	public void Add(Restaurant rest) {
		list.add(rest);
	}
	
	/**
	 * Returns {@code Restaurant} object
	 * @param id the unique restaurantId
	 * @return
	 */
	public Restaurant GetRestaurantBy(int id) {
		//Find and returns customer by id
		for (int i = 0; i < list.size(); i++) {
			Restaurant r = list.get(i);
			if (r.GetId() == id) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 *  Returns a {@code String} representing all Restaurant objects in the list
	 */
	public String toString() {
		//returns a string representing all driver objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Returns the number of {@code Restaurant} objects in the list
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Returns the ith {@code Restaurant} object in the list
	 * @param i the list index, starting at 0
	 * @return
	 */
	public int GetId(int i) {
		return list.get(i).GetId();
	}
	
	//Later add the following:
	//Remove restaurant
	
	
}
