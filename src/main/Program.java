package main;

import java.util.Scanner;
/**
 * Provides a console based application to run the Quick Food Order System.
 * The following functionality is provided:
 * - Create a new order
 * - Find an existing customer
 * - Create a new customer
 * - Update an existing customer
 * - Find incomplete orders
 * - Finalise an order
 * - Find an existing order
 * 
 * @author donnp
 * 
 */
public class Program {
	/**
	 * Run the main program loop.
	 */
	public static void main(String[] args) {
		char result;
		do {
			DisplayWelcomeMessage();
			
			boolean validOptionSelected;
			do {
				DisplayMainMenuOptions();
				int option = IOHandler.GetNumberFromUser();
				switch (option) {
					case 1: 
						validOptionSelected = true;
						CreateNewOrder();
						break;
					case 2: 
						validOptionSelected = true;
						FindCustomer();
						break;
					case 3: 
						validOptionSelected = true;
						AddNewCustomer();
						break;
					case 4: 
						validOptionSelected = true;
						UpdateCustomer();
						break;
					case 5: 
						validOptionSelected = true;
						FindIncompleteOrders();
						break;
					case 6: 
						validOptionSelected = true;
						FinaliseOrder();
						break;
					case 7: 
						validOptionSelected = true;
						FindOrder();
						break;
				default:
					validOptionSelected = false;
				}
			} while (!validOptionSelected);
			
			do {
				result = DoAnotherTransaction();
			} while(result != 'y' && result != 'n');
		} while(result == 'y');
		
		System.out.println("Goodbye!");
		
	}//main


	/**
	 * Display welcome message when program is started
	 */
	public static void DisplayWelcomeMessage() {
		System.out.println("Welcome to the Quick Food Order System!");
	}
	
	/**
	 * Asks the user if he/she wants to do another transaction
	 * @return 'y' if user wants to do another transaction, 'n' if not
	 */
	public static char DoAnotherTransaction() {
		//	
			Scanner s = new Scanner(System.in);
			do {
				System.out.println("Do you want to do another transaction? (y/n)");
				String result = s.next();		
				if (result.equalsIgnoreCase("y")) return 'y';
				else if (result.equalsIgnoreCase("n")) return 'n';
				else break;
			}while (s.hasNext());
			return 'k';
	}

	/**
	 * Displays the main menu options
	 */
	public static void DisplayMainMenuOptions() {
		System.out.println("Please select an option from the menu below by entering the corresponding number");
		System.out.println("1. Create a new order");
		System.out.println("2. Find existing customer");
		System.out.println("3. Create new customer");
		System.out.println("4. Update existing customer");
		System.out.println("5. Find incomplete orders");
		System.out.println("6. Finalise order");
		System.out.println("7. Find existing order");
	}
	
	/**
	 * Gets the location id for a given location
	 * @param location A string representing a location, eg Cape Town
	 * @return Integer representing locationId
	 */
	private static int GetLocationId(String location) {
		int locationId = IOHandler.GetLocationId(location);
		if (locationId > 0) {
			System.out.println("Location id found");
		} else {
			System.out.println("Error: Location id not found. Customer not added to database.");
		}
		return locationId;
	}
	
	/**
	 * Creates a new order and inserts the order into the database.
	 */
	public static void CreateNewOrder() {
		
		//Create main list objs
		CustomerList customerList = IOHandler.MakeCustomerList();
		RestaurantList restaurantList = IOHandler.MakeRestaurantList();
		DriverList driverList = IOHandler.MakeDriverList();
		OrderList orderList = new OrderList();
		
		Order order = Order.CreateOrder(customerList, restaurantList, driverList);
		//At this point the food order is not yet created
		if (order!=null) {
			//Take food order
			order.TakeFoodOrder();
			orderList.Add(order); //Add the order to the order list
			System.out.println("Here is a summary of your order:");
			System.out.println(order.toString());
					
		} else {
			System.out.println("Sorry, there are no drivers in this area, cannot process order.");
		}
	}
	
	/**
	 * Returns the details of the specified customer
	 */
	private static void FindCustomer() {

		//Ask user for customerId
		System.out.println("Enter id of customer: ");
		int id = IOHandler.GetNumberFromUser();
		
		//Find the customer and print current details
		Customer cust = IOHandler.GetCustomerById(id);
		System.out.println("Current details for this customer: ");
		System.out.println(cust.toString());
		
	}
	
	/**
	 * Create a new customer and to database
	 */	
	private static void AddNewCustomer() {

		
		//Get inputs from user
		String name = new String();
		String contactNumber = new String();
		String email = new String();
		String streetAddress = new String();
		String suburb = new String();
		String location = new String();

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name:");
		name = sc.nextLine();
		System.out.print("Enter contact number:");
		contactNumber = sc.nextLine();
		System.out.print("Enter email:");
		email = sc.nextLine();
		System.out.print("Enter street address:");
		streetAddress = sc.nextLine();		
		System.out.print("Enter suburb:");
		suburb = sc.nextLine();	
		System.out.print("Enter location:");
		location = sc.nextLine();	
			
		System.out.println("name: " + name + ", tel number: " + contactNumber + ", email: " + email + ", street address: " + streetAddress + ", suburb: " + suburb + ", location: " + location);
		System.out.println("");
		
		//Check if location is valid and get locationId
		int locationId = GetLocationId(location);
		
		//Write to db
		if (IOHandler.AddNewCustomer(name, contactNumber, email, streetAddress, suburb, locationId)) {
			System.out.println("Customer successfully added to the database!");
		} else {
			System.out.println("Error: Customer was not added to the database");
		}
		
	}

	/**
	 * Updates an exiting customer in the database
	 * User is required to enter the id of the customer to be updated
	 * User then specifies new details
	 */
	private static void UpdateCustomer() {

		//Ask user for customerId of customer to be updated
		System.out.println("Enter id of customer to be updated: ");
		int id = IOHandler.GetNumberFromUser();
		
		//Find the customer and print current details
		Customer cust = IOHandler.GetCustomerById(id);
		System.out.println("Current details for this customer: ");
		System.out.println(cust.toString());
		
		//ask user to enter new details
		String name = new String();
		String contactNumber = new String();
		String email = new String();
		String streetAddress = new String();
		String suburb = new String();
		String location = new String();

		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name:");
		name = sc.nextLine();
		System.out.print("Enter contact number:");
		contactNumber = sc.nextLine();
		System.out.print("Enter email:");
		email = sc.nextLine();
		System.out.print("Enter street address:");
		streetAddress = sc.nextLine();		
		System.out.print("Enter suburb:");
		suburb = sc.nextLine();	
		System.out.print("Enter location:");
		location = sc.nextLine();	
			
		System.out.println("name: " + name + ", tel number: " + contactNumber + ", email: " + email + ", street address: " + streetAddress + ", suburb: " + suburb + ", location: " + location);
		System.out.println("");
		
		//Check if location is valid and get locationId
		int locationId = GetLocationId(location);
		
		//Write new details to db
		if (IOHandler.UpdateCustomer(id, name, contactNumber, email, streetAddress, suburb, locationId)) {
			System.out.println("Customer successfully updated!");
		} else {
			System.out.println("Error: Customer was not updated");
		}
		
	}
	
	/**
	 * Find orders for which the completion status is 0 (incomplete)
	 */
	private static void FindIncompleteOrders() {
		
		//Find the incomplete orders and print details
		OrderList list = IOHandler.GetIncompleteOrders();
		System.out.println("Incomplete orders: ");
		System.out.println(list.summaryToString());
	}
	
	/**
	 * Sets the completion status to complete and sets the completion date to current date for given order
	 */
	private static void FinaliseOrder() {
		
		//Ask user for orderId of order to be completed
		System.out.println("Enter id of order to be completed: ");
		int id = IOHandler.GetNumberFromUser();
		
		//Find the order and print current details
		GetOrderById(id);

		//Write new details to db
		if (IOHandler.UpdateOrderComplete(id)) {
			System.out.println("Order has been marked as completed");
		} else {
			System.out.println("Error: Order could not be marked as completed");
		}
	}

	/**
	 * Returns an Order specified by the given orderId
	 * @param id the orderId of the order
	 * @return {@code Order} object
	 */
	private static Order GetOrderById(int id) {
		Order order = IOHandler.GetOrderById(id);
		System.out.println("Current details for this order: ");
		System.out.println(order.summaryToString());
		return order;
	}
	
	/**
	 * Returns the details of the specified order
	 */
	private static void FindOrder() {
		//Ask user for orderId
		System.out.println("Enter id of order: ");
		int id = IOHandler.GetNumberFromUser();
		
		GetOrderById(id);
		
	}
}//public class Program
