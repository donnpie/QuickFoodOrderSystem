package main;
import java.util.Comparator;
import java.util.Scanner;

public class Order {
	int orderNumber;
	MealList mealList; //stores the meals and quantities ordered
	String specialInstructions;
	Customer customer;
	Restaurant restaurant;
	Driver driver;
	
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
	
	public void SetDriver(Driver d) {
		this.driver = d;
		//When driver is assigned to an order, the driver's number of loads must be incremented
		this.driver.IncrementNumOfDeliveries();
	}
	
	public Driver GetDriver() {
		return driver;
	}
	

	public MealList GetMealList() {
		return this.mealList;
	}
	
	
	public static Order CreateOrder(CustomerList cList, RestaurantList rList, DriverList dList) {
		//Create new order object, populate the members of the object and return it
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
	
	
	//Helper functions
	private static void DisplayCustomers(CustomerList cList) {
		System.out.println("Please select a customer from the list below by entering the corresponding number");
		System.out.println(cList.toString());
	}
	
	
	
	private static int MakeCustomerSelection(CustomerList cList) {
		/**
		 * Prompts user to select customer by entering a valid customerId
		 */
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
	private static void DisplayRestaurants(RestaurantList rList) {
		System.out.println("Please select a restaurant from the list below by entering the corresponding number");
		System.out.println(rList.toString());
	}
	
	
	//TODO: This is duplicated code. Can be simplified by introducing an interface. Refactor
	private static int MakeRestaurantSelection(RestaurantList rList) {
		/**
		 * Prompts user to select restaurant by entering a valid restaurantId
		 */
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
	
	private static int MakeMealSelection(Menu menu) {
		/**
		 * Prompts user to select item from menu by entering a valid restaurantId
		 */
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
	
	public void TakeFoodOrder() {
		/**
		 * Take food order associated with this order
		 */
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
		
		///For other functions - copy here
		
	}
	
	private static void DisplayMenu(Menu menu) {
		System.out.println("Please select a meal from the list below by entering the corresponding number");
		System.out.println(menu.toString());
	}
	
	
	public static void DisplayFoodOrderMessage() {
		System.out.println("Please order items from the menue (DisplayFoodOrderMessage)");
	}
	
	
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
	
	private double CalcTotalAmount() {
		return mealList.CalcTotalAmount();
	}
	
	//Comparator for sorting by Customer name field
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
	
	//Comparator for sorting by Customer location
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
	
	public String nameAndNumToString() {
		//returns a string with customer name and order number
		String result = new String("Customer: " + this.customer.GetName() + ", ");
		result += "Order number:" + this.orderNumber + "\n";
		return result;
	}
	
	public String locationAndNameToString() {
		//returns a string with customer name and location
		return this.customer.locationAndNameToString();
	}
	
	//@Override - TODO later, for all classes
	public String toString() {
		//returns a string representing the order object
		String result = new String("Order number:" + this.orderNumber + "\n");
		result += "Customer: " + this.customer.GetName() + "\n";
		result += "Email: " + this.customer.GetEmail() + "\n";
		result += "Phone number: " + this.customer.GetTelNumber() + "\n";
		result += "Location: " + this.customer.GetLocation().toString() + "\n";
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
