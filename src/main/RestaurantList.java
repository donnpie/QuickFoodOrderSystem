package main;
import java.util.ArrayList;
import java.util.List;

public class RestaurantList {
	private List<Restaurant> list = new ArrayList<Restaurant>();
	
	public RestaurantList() {
		
	}

	public void Add(Restaurant rest) {
		list.add(rest);
	}
	
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
	
	public String toString() {
		//returns a string representing all driver objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
	
	//Later add the following:
	//Remove restaurant
	
	
}
