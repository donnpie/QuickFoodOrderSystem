package main;
import java.util.Scanner;

//Notes for L2 T11 first submission
//1. Added functionality to output a textfile with customer names and order numbers, sorted by customer name
//2. Added functionality to output a textfile with customer names and locations, sorted by customer location
//3. Added functionality to overwrite drivers textfile with updated number of loads

public class Program {
	public static void main(String[] args) {
		//Define file paths and names
		String driverFile = "../drivers.txt";
		String orderFile = "../orders.txt";
		String customerFile = "../customerLocations.txt";
		
		
		//Create main list objs
		CustomerList customerList = MakeCustomerList();
		RestaurantList restaurantList = MakeRestaurantList();
		DriverList driverList = IOHandler.MakeDriverList(driverFile);
		

		//DriverList driverList = IOHandler.MakeDriverList("./data/drivers.txt");
		//System.out.println(driverList.toString());
		char result;
		OrderList orderList = new OrderList();
		do {
			DisplayWelcomeMessage();
			
			boolean validOptionSelected;
			do {
				DisplayMainMenuOptions();
				int option = IOHandler.GetNumberFromUser();
				switch (option) {
					case 1: 
					validOptionSelected = true;
					Order order = Order.CreateOrder(customerList, restaurantList, driverList);
					//At this point the food order is not yet created
					if (order!=null) {
						//Take food order
						order.TakeFoodOrder();
						orderList.Add(order); //Add the order to the order list
						System.out.println("Here is a summary of your order:");
						System.out.println(order.toString());
						//Write invoice text to file
						IOHandler.WriteToFile(order.toString(), "../invoice "+ order.GetOrderNumber()+".txt");						
					} else {
						System.out.println("Sorry, there are no drivers in this area, cannot process order.");
					}
					break;
				default:
					validOptionSelected = false;
				}
			} while (!validOptionSelected);
			
			do {
				result = DoAnotherTransaction();
			} while(result != 'y' && result != 'n');
		} while(result == 'y');
		
		System.out.println("Here is a summary of all orders:");
		orderList.SortByCustomerName();
		System.out.println(orderList.nameAndNumToString()); //Should print names in alpha order followed by order numbers
		IOHandler.WriteToFile(orderList.nameAndNumToString(), orderFile);	//Write names and nums to file
		
		System.out.println("Here is a summary of customers by location (Includes customers with no orders):");
		customerList.SortByCustomerLocation();
		System.out.println(customerList.locationAndNameToString()); 
		IOHandler.WriteToFile(customerList.locationAndNameToString(), customerFile);	//Write to file
		
		System.out.println("Here is a summary of drivers with updated loads:"); //Default sort order
		System.out.println(driverList.toString()); 
		IOHandler.WriteToFile(driverList.toString(), driverFile);	//Write to file
		
		System.out.println("Goodbye!");
		
	}//main
	
	public static void DisplayWelcomeMessage() {
		System.out.println("Welcome to the Quick Food Order System!");
	}
	
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

	public static CustomerList MakeCustomerList() {
		//Create some customer objects. Eventually this should be changed to a database query
		CustomerList customerList = new CustomerList();
		
		Customer cust1 = new Customer("Jill Jack", "123 456 7890", "jilljack@yahoo.com", "12 Cherry Road", "Plumstead", "Cape Town", 1);
		customerList.Add(cust1);
		//System.out.println(cust1.toString());
		Customer cust2 = new Customer("Harry Potter", "876 342 7328", "harry@magic.co.za", "13 Rivonia Road", "Sandton", "Johannesburg", 2);
		customerList.Add(cust2);
		Customer cust3 = new Customer("Dirty Harry", "+27 82 999 4444", "ionlyaskonce@dontask.com", "14 Cherry Road", "North Beach", "Durban", 3);
		customerList.Add(cust3);
		Customer cust4 = new Customer("John Wick", "+27 82 111 2222", "donttouchmydog@guns.com", "15 Cherry Road", "Boksburg", "Johannesburg", 4);
		customerList.Add(cust4);
		//System.out.println(customerList.toString());
		return customerList;
	}
	
	public static RestaurantList MakeRestaurantList() {
		//Create some Meal objects. Eventually this should be changed to a database query
		RestaurantList restaurantList = new RestaurantList();
		
		Restaurant rest1 = new Restaurant("Aesop's Pizza", "098 765 4321", "Cape Town", 1);
		Restaurant rest2 = new Restaurant("Hero Steaks", "011 999 1111", "Johannesburg", 2);
		Restaurant rest3 = new Restaurant("Junk Food", "031 567 4321", "Durban", 3);
		
		//Add to menu
		Meal meal1 = new Meal("Pizza", 1, 55.80);
		rest1.Add(meal1);
		rest2.Add(meal1);
		rest3.Add(meal1);
		Meal meal2 = new Meal("Pasta", 2, 40.23);
		rest1.Add(meal2);
		rest2.Add(meal2);
		rest3.Add(meal2);
		Meal meal3 = new Meal("Steak", 3, 90.44);
		rest1.Add(meal3);
		rest2.Add(meal3);
		rest3.Add(meal3);
		
		//Add restaurant to RestaurantList
		restaurantList.Add(rest1);
		restaurantList.Add(rest2);
		restaurantList.Add(rest3);
		
		return restaurantList;	
	}
	
	public static void DisplayMainMenuOptions() {
		System.out.println("Please select an option from the menu below by entering the corresponding number");
		System.out.println("1. Create a new order");
	}
}//public class Program
