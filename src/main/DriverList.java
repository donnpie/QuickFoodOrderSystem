package main;
import java.util.ArrayList;
import java.util.List;
/**
 * The class {@code DriverList} provides a array object that holds any number of Driver objects
 * @author donnp
 *
 */
public class DriverList {
	List<Driver> list = new ArrayList<Driver>();
	
	/**
	 * Adds a {@code Driver} object to the list
	 * @param d {@code Driver} object
	 */
	public void Add(Driver d) {
		list.add(d);
	}
	
	/**
	 * Filters the drivers in the area of the restaurant and returns the driver with the lowest number of orders
	 * @param restaurant drivers are found based on the location of the restaurant
	 * Returns null if no suitable driver foun
	 * @return
	 */
	public Driver PickDriverInAreaWithLowestNumberOfOrders(Restaurant restaurant) {
		ArrayList<Driver> shortList = new ArrayList<Driver>();
		Driver driverWithLowestDeliveries = null;
		
		if (list.size() > 0) {
			//Make shortlist of drivers in the area
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).GetLocation().toString().equalsIgnoreCase(restaurant.GetLocation().toString())) {
					shortList.add(list.get(i));
				}			
			}
			//Pick the driver with the lowest number of deliveries
			int lowestSoFar = 1000;
			for(int i = 0; i < shortList.size(); i++) {
				if(shortList.get(i).GetNumOfDeliveries() == 0) return shortList.get(i);
				if(shortList.get(i).GetNumOfDeliveries() < lowestSoFar) {
					driverWithLowestDeliveries = shortList.get(i);
					lowestSoFar = shortList.get(i).GetNumOfDeliveries();
				}
			}					
		}
		return driverWithLowestDeliveries;
	}
	
	/**
	 * Returns the ith {@code Driver} object in the list
	 * @param index the list index, starting at 0
	 * @return
	 */
	public Driver GetDriverByIndex(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		} else return null;
	}
	
	/**
	 *  Returns a {@code String} representing all Driver objects in the list
	 */
	public String toString() {
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
}//public class DriverList
