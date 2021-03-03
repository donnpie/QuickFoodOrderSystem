package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerList {
	private List<Customer> list = new ArrayList<Customer>();
	
	public CustomerList() {
		
	}
	
	public void Add(Customer customer) {
		list.add(customer);
	}
	
	public Customer GetCustomerBy(int id) {
		/**
		 * Find and return customer by CustomerId
		 */
		for (int i = 0; i < list.size(); i++) {
			Customer c = list.get(i);
			if (c.GetId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public void SortByCustomerLocation() {
		Collections.sort(list, Customer.custLocationComparator);
	}
	
	public String locationAndNameToString() {
		//returns a string with customer names and locations. Includes all customers, even those without orders
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).locationAndNameToString() + "\n";
		}
		return result;
	}
	
	public String toString() {
		//returns a string representing all driver objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
	
	public int size() {
		return list.size();
	}

	public int GetId(int i) {
		/**
		 * Return the id for customer at index position i
		 */
		return list.get(i).GetId();
	}
	
	//Later should add the following:
	//inititalise customer list from array of Customers
	//remove customer from customerList
	
	
	
	
	
}
