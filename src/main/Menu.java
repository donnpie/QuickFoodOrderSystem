package main;
import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<Meal> list = new ArrayList<Meal>();
	
	public void Add(Meal m) {
		list.add(m);
	}
	
	public Meal GetMealBy(int id) {
		//Find and returns meal by id
		for (int i = 0; i < list.size(); i++) {
			Meal m = list.get(i);
			if (m.GetId() == id) {
				return m;
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
}//public class Menu
