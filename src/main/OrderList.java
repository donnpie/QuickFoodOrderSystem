package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderList {
	//OrderList is used to keep track of multiple orders
	private List<Order> list = new ArrayList<Order>();
	
	private static int lastOrderNumber = 1000;
	
	public OrderList() {
		
	}
	
	public void Add(Order order) {
		list.add(order);
	}
	
	public static int GetNextOrderNumber() {
		return lastOrderNumber++; //Number is returned first and then incremented
	}
	
	public void SortByCustomerName() {
		Collections.sort(list, Order.custNameComparator);
	}
	
	public String nameAndNumToString() {
		//returns a string with customer names and order numbers
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).nameAndNumToString() + "\n";
		}
		return result;
	}
	
	public String locationAndNameToString() {
		//returns a string with customer names and locations. Only includes customers with orders
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).locationAndNameToString() + "\n";
		}
		return result;
	}
	
	public String toString() {
		//returns a string representing all order objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
}
