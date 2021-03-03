package main;
import java.util.ArrayList;
import java.util.List;

public class DriverList {
	List<Driver> list = new ArrayList<Driver>();
	
	public void Add(Driver d) {
		list.add(d);
	}
	
	public Driver PickDriverInAreaWithLowestNumberOfOrders(Restaurant restaurant) {
		/**
		 * Returns the best driver for the given restaurant
		 * Returns null if no suitable driver found
		 */
	
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
	
	public Driver GetDriverByIndex(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		} else return null;
	}
	
	public String toString() {
		//returns a string representing all driver objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
}//public class DriverList
