package main;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
/**
 * The class {@code Order} provides a concrete implementation for a Order object
 * An Order object represents a food order by a given customer, from a given restaurant, to be delivered by a given driver
 * @author donnp
 *
 */
public class Order {
	int orderNumber;
	MealList mealList; //stores the meals and quantities ordered
	String specialInstructions;
	Customer customer;
	Restaurant restaurant;
	Driver driver;
	boolean isComplete;
	Date completionDate;
	
	/**
	 * Default constructor
	 */
	public Order() {
		this.orderNumber = 0;
		this.mealList = null;
		this.specialInstructions = new String();
		this.customer = new Customer();
		this.restaurant = new Restaurant();
		this.driver = new Driver();
		this.isComplete = false;
		this.completionDate = null;
	}
	
	/**
	 * Overloaded constructor
	 * @param orderNumber {@code int} unique orderId
	 * @param specialInstructions {@code String} specifying if there are any special instructions for the meal
	 * @param customer {@code Customer} object representing the Customer for this Order
	 * @param restaurant {@code Restaurant} object representing the Restaurant for this Order
	 * @param driver  {@code Driver} object representing the Driver who will deliver this Order
	 */
	public Order(int orderNumber, String specialInstructions, Customer customer, Restaurant restaurant, Driver driver) {
		this.orderNumber = orderNumber;
		this.mealList = null;
		this.specialInstructions = specialInstructions;
		this.customer = customer;
		this.restaurant = restaurant;
		this.driver = driver;
		this.isComplete = false;
		this.completionDate = null;
	}
	
	/**
	 * Overloaded constructor
	 * @param orderNumber {@code int} unique orderId
	 * @param specialInstructions {@code String} specifying if there are any special instructions for the meal
	 * @param customer {@code Customer} object representing the Customer for this Order
	 * @param restaurant {@code Restaurant} object representing the Restaurant for this Order
	 * @param driver  {@code Driver} object representing the Driver who will deliver this Order
	 * @param isComplete {@code boolean} true if order is complete, false otherwise
	 * @param dateString {@code Date} date on which order was completed
	 */
	public Order(int orderNumber, String specialInstructions, Customer customer, Restaurant restaurant, Driver driver, boolean isComplete, String dateString) {
		this.orderNumber = orderNumber;
		this.mealList = null;
		this.specialInstructions = specialInstructions;
		this.customer = customer;
		this.restaurant = restaurant;
		this.driver = driver;
		this.isComplete = isComplete;
		setCompletionDate(dateString);
	}
	
	public void SetOrderNumber(int num) {
		this.orderNumber = num;
	}
	
	public int GetOrderNumber() {
		return this.orderNumber;
	}
	
	public void SetCustomer(Customer c) {
		this.customer = c;
	}
	
	public Customer GetCustomer() {
		return this.customer;
	}
	
	public void SetSpecialInstructions() {
		System.out.println("Please specify any special instructions (Press enter if none):");
		this.specialInstructions = IOHandler.GetNextLineFromUser();
	}
	
	public String GetSpecialInstructions() {
		return this.specialInstructions;
	}
	
	public void SetRestaurant(Restaurant r) {
		this.restaurant = r;
	}
	
	public Restaurant GetRestaurant() {
		return this.restaurant;
	}
	
	/**
	 * Assigns Driver to Order
	 * @param d
	 */
	public void SetDriver(Driver d) {
		this.driver = d;
		this.driver.IncrementNumOfDeliveries();
	}
	
	public Driver GetDriver() {
		return driver;
	}
	

	public MealList GetMealList() {
		return this.mealList;
	}
	
	public void setCompletionFlag(int flag) {
		this.isComplete = flag == 1 ? true : false;
	}
	
	/**
	 * Parses a date string to a date object
	 * @param dateString A string representing a date in "yyyy-MM-dd" format
	 */
	public void setCompletionDate(String dateString) {
		try {
			this.completionDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			System.out.println("Error: Could not parse date"); 
			e.printStackTrace();
		}  
	    System.out.println(dateString+"\t"+this.completionDate.toString() );  

	}
	
	/**
	 * Create new order object, populate the members of the object and return it
	 * @param cList {@code CustomerList} object
	 * @param rList {@code RestaurantList} object
	 * @param dList {@code DriverList} object
	 * @return {@code Order} object
	 */
	public static Order CreateOrder(CustomerList cList, RestaurantList rList, DriverList dList) {
		
		Order o = new Order();
		
		//User is asked to select customer and restaurant
		int custId = MakeCustomerSelection(cList);
		o.SetCustomer(cList.GetCustomerBy(custId));
		int restId = MakeRestaurantSelection(rList);
		o.SetRestaurant(rList.GetRestaurantBy(restId));
		
		//Driver is picked automatically
		o.SetDriver(dList.PickDriverInAreaWithLowestNumberOfOrders(o.GetRestaurant()));
		if (o.GetDriver() == null) {
			System.out.println("Sorry, there is no driver available in that area, order cannot be completed");
			return null;
		}
		o.SetSpecialInstructions();
		
		//Send order to db and get order number back
		if (IOHandler.SendOrderToDB(o)) {
			int orderNumber = IOHandler.GetLastOrderNumber();
			o.SetOrderNumber(orderNumber);
		} else {
			System.out.println("Error: Order could not be sent to the database");
			return null;
		}
															
		return o;
	}
	
	
	/**
	 * Displays a list of customers so that user can make a selection
	 * @param cList {@code CustomerList} object
	 */
	private static void DisplayCustomers(CustomerList cList) {
		System.out.println("Please select a customer from the list below by entering the corresponding number");
		System.out.println(cList.toString());
	}
	
	
	/**
	 * Prompts user to select customer by entering a valid customerId
	 * @param cList {@code CustomerList} object
	 * @return
	 */
	private static int MakeCustomerSelection(CustomerList cList) {
		boolean validOptionSelected = false;
		int option = 0;
		do {
			DisplayCustomers(cList);
			option = IOHandler.GetNumberFromUser();
			
			for (int i = 0; i < cList.size(); i++) {
				if (option == cList.GetId(i)) {
					validOptionSelected = true;
					break;
				} else { 
					validOptionSelected = false;
				}
			}
		} while (!validOptionSelected);
		return option;
}
	//This is duplicated code. Can be simplified by introducing an interface
	/**
	 * Displays a list of restaurants so that user can make a selection
	 * @param rList {@code RestaurantList} object
	 */
	private static void DisplayRestaurants(RestaurantList rList) {
		System.out.println("Please select a restaurant from the list below by entering the corresponding number");
		System.out.println(rList.toString());
	}
	
	
	//TODO: This is duplicated code. Can be simplified by introducing an interface. Refactor
	/**
	 * Prompts user to select restaurant by entering a valid restaurantId
	 * @param rList {@code RestaurantList} object
	 * @return 
	 */
	private static int MakeRestaurantSelection(RestaurantList rList) {
		boolean validOptionSelected = false;
		int option = 0;
		do {
			DisplayRestaurants(rList);
			option = IOHandler.GetNumberFromUser();
			
			for (int i = 0; i < rList.size(); i++) {
				if (option == rList.GetId(i)) {
					validOptionSelected = true;
					break;
				} else { 
					validOptionSelected = false;
				}
			}
		} while (!validOptionSelected);
		return option;
	}
	
	/**
	 * Prompts user to select item from menu by entering a valid restaurantId
	 */
	private static int MakeMealSelection(Menu menu) {
		boolean validOptionSelected = false;
		int option = 0;
		do {
			DisplayMenu(menu);
			option = IOHandler.GetNumberFromUser();
			
			for (int i = 0; i < menu.size(); i++) {
				if (option == menu.GetId(i)) {
					validOptionSelected = true;
					break;
				} else { 
					validOptionSelected = false;
				}
			}
		} while (!validOptionSelected);
		return option;
	}
	
	/**
	 * Take food order associated with this order
	 */
	public void TakeFoodOrder() {
		if (this.restaurant.menu == null) {
			IOHandler.MakeMenu(this);			
		} else {
			System.out.println("menu already exists");
		}
		char result;
		MealList mList = new MealList();
		do {
			
			int option = MakeMealSelection(this.restaurant.menu);
			MealLineItem mealLineItem = new MealLineItem();
			mealLineItem.SetMeal(restaurant.GetMenu().GetMealById(option));
			System.out.println("How many would you like?");
			mealLineItem.SetQuantity(IOHandler.GetNumberFromUser());
			mList.Add(mealLineItem);
			do {
				result = OrderAnotherMeal();
			} while(result != 'y' && result != 'n');
		} while(result == 'y');
		
		//Bind meal list to order
		this.mealList = mList;
		
		//Send food order to db
		if (IOHandler.SendFoodOrderToDB(this)) {
			System.out.println("Food order successfully sent to the database!");
		} else {
			System.out.println("Error: Food order could not be sent to the database");
		}
		
		//System.out.println("Printing meal list:");
		//System.out.println(this.mealList.toString());
	}
	
	/**
	 * Prompts user to select meal by entering a valid mealId
	 * @param meanu {@code Menu} object
	 */
	private static void DisplayMenu(Menu menu) {
		System.out.println("Please select a meal from the list below by entering the corresponding number");
		System.out.println(menu.toString());
	}
	
	
	public static void DisplayFoodOrderMessage() {
		System.out.println("Please order items from the menu");
	}
	
	/**
	 * Ask user if he/she wants to order another meal
	 * @return
	 */
	public static char OrderAnotherMeal() {
		//	
			Scanner s = new Scanner(System.in);
			do {
				System.out.println("Do you want to order another meal? (y/n)");
				String result = s.next();		
				if (result.equalsIgnoreCase("y")) return 'y';
				else if (result.equalsIgnoreCase("n")) return 'n';
				else break;
			}while (s.hasNext());
			return 'k';
	}
	
	/**
	 * Calculates the total amount due
	 * The amount due is the sum of the products of meal price and quantity
	 * @return {@code double} total amount due
	 */
	private double CalcTotalAmount() {
		return mealList.CalcTotalAmount();
	}
	
	/**
	 * Comparator for sorting by Customer name field
	 */
	public static Comparator<Order> custNameComparator = new Comparator<Order>() {
		
		public int compare(Order o1, Order o2) {
			String custName1 = o1.customer.GetName().toLowerCase();
			String custName2 = o2.customer.GetName().toLowerCase();
			
			//ascending order
			return custName1.compareTo(custName2); 
			
			//descending order
			//return custName2.compareTo(custName1); 
		}
	};
	
	/**
	 * Comparator for sorting by Customer location
	 */
	public static Comparator<Order> custLocationComparator = new Comparator<Order>() {
		
		public int compare(Order o1, Order o2) {
			String custLoc1 = o1.customer.GetLocation().toString().toLowerCase();
			String custLoc2 = o2.customer.GetLocation().toString().toLowerCase();
			
			//ascending order
			return custLoc1.compareTo(custLoc2); 
			
			//descending order
			//return custName2.compareTo(custName1); 
		}
	};
	
	/**
	 * Returns a string with customer name and order number
	 * @return
	 */
	public String nameAndNumToString() {
		String result = new String("Customer: " + this.customer.GetName() + ", ");
		result += "Order number:" + this.orderNumber + "\n";
		return result;
	}
	
	/**
	 * Returns a string with customer name and location
	 * @return
	 */
	public String locationAndNameToString() {
		//
		return this.customer.locationAndNameToString();
	}
	
	/**
	 * Returns a string representing a summary of the order object
	 * @return
	 */
	public String summaryToString() {
		String result = new String("Order number: " + this.orderNumber + ", ");
		result += "Customer: " + this.customer.GetName() + ", ";
		result += "Restaurant: " + this.restaurant.GetName() + ", ";
		result += "Driver: " + this.driver.GetName() + ", ";
		result += "Status: " + (this.isComplete == true ? "Complete" : "Incomplete")   + ", ";
		result += "Order completion date: " + (this.completionDate != null ? this.completionDate.toString() : "N/A" );
		result += "\n";
		return result;
	}
	
	//@Override - TODO later, for all classes
	/**
	 * Returns a string representing the order object
	 */
	public String toString() {
		String result = new String("Order number:" + this.orderNumber + "\n");
		result += "Customer: " + this.customer.GetName() + "\n";
		result += "Email: " + this.customer.GetEmail() + "\n";
		result += "Phone number: " + this.customer.GetTelNumber() + "\n";
		result += "Location: " + this.customer.GetLocation().toString() + "\n";
		result += "Order status: " + (this.isComplete == true ? "Complete" : "Incomplete" ) + "\n";
		result += "Order completion date: " + (this.completionDate != null ? this.completionDate.toString() : "N/A" ) + "\n";
		result += "\n";
		result += "You have ordered the following from " + this.restaurant.GetName() + " in " + this.restaurant.GetLocation().toString() + ":\n";		
		result += "\n";
		result += this.mealList.toString();
		result += "\n";
		result += "Special instructions: " + this.specialInstructions + "\n";
		result += "\n";
		result += "Total: R " + this.CalcTotalAmount() + "\n";
		result += "\n";
		result += this.driver.GetName() + " is the nearest to the restaurant and so he/she will be delivering your order to you at:\n";
		result += "\n";
		result += this.customer.getStreetAddress() + "\n";
		result += this.customer.getSuburb() + "\n";
		result += "\n";
		result += "If you need to contact the restaurant, their number is " + this.restaurant.GetTelNumber();
		result += "\n";
		return result;
	}
}
