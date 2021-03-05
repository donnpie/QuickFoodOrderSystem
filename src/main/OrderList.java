package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The class {@code OrderList} provides a array object that holds any number of Order objects
 * @author donnp
 *
 */
public class OrderList {
	//OrderList is used to keep track of multiple orders
	private List<Order> list = new ArrayList<Order>();
	
	public OrderList() {
		
	}
	
	/**
	 * Adds a {@code Order} object to the list
	 * @param order {@code Order} object
	 */
	public void Add(Order order) {
		list.add(order);
	}
	
	/**
	 * Sorts the list by name in ascending order
	 */
	public void SortByCustomerName() {
		Collections.sort(list, Order.custNameComparator);
	}
	
	/**
	 *  Returns a {@code String} with customer names and order numbers
	 */
	public String nameAndNumToString() {
		//
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).nameAndNumToString() + "\n";
		}
		return result;
	}
	
	/**
	 *  Returns a {@code String} with customer names and locations. Only includes customers with orders
	 */
	public String locationAndNameToString() {
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).locationAndNameToString() + "\n";
		}
		return result;
	}
	
	/**
	 *  Returns a {@code String} representing all order objects. Only summary info is given
	 */
	public String summaryToString() {
		//returns a string representing all order objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).summaryToString() + "\n";
		}
		return result;
	}
	
	/**
	 *  Returns a {@code String} representing all order objects
	 */
	public String toString() {
		//returns a string representing all order objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
}
