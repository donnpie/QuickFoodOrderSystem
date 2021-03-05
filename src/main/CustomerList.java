package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * The class {@code CustomerList} provides a array object that holds any number of Customer objects
 * @author donnp
 *
 */
public class CustomerList {
	private List<Customer> list = new ArrayList<Customer>();
	
	public CustomerList() {
		//Currently empty
	}
	
	/**
	 * Adds a {@code Customer} object to the list
	 * @param customer {@code Customer} object
	 */
	public void Add(Customer customer) {
		list.add(customer);
	}
	
	/**
	 * Returns {@code Customer} object
	 * @param id the unique customerId
	 * @return
	 */
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
	
	/**
	 * Sorts the list by location in ascending order
	 */
	public void SortByCustomerLocation() {
		Collections.sort(list, Customer.custLocationComparator);
	}
	
	/**
	 * Returns a string with customer names and locations. Includes all customers, even those without orders
	 * @return
	 */
	public String locationAndNameToString() {
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).locationAndNameToString() + "\n";
		}
		return result;
	}
	
	/**
	 *  Returns a string representing all Customer objects in the list
	 */
	public String toString() {
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).toString() + "\n";
		}
		return result;
	}
	
	/**
	 * Returns the number of {@code Customer} objects in the list
	 * @return
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Returns the ith {@code Customer} object in the list
	 * @param i the list index, starting at 0
	 * @return
	 */
	public int GetId(int i) {
		/**
		 * Return the id for customer at index position i
		 */
		return list.get(i).GetId();
	}
}
