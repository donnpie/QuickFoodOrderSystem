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
	
	public void SetSpecialInstructions() {
		System.out.println("Please specify any special instructions (Press enter if none):");
		this.specialInstructions = IOHandler.GetNextLineFromUser();
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
	
	
	public static Order CreateOrder(CustomerList cList, RestaurantList rList, DriverList dList) {
		//Create new order object, populate the members of the object and return it
		Order o = new Order();
		int custId = MakeCustomerSelection(cList);
		o.SetCustomer(cList.GetCustomerBy(custId));
		int restId = MakeRestaurantSelection(rList);
		o.SetRestaurant(rList.GetRestaurantBy(restId));
		o.SetDriver(dList.PickDriverInAreaWithLowestNumberOfOrders(o.GetRestaurant()));
		if (o.GetDriver() == null) {
			return null;
		}
		o.SetOrderNumber(OrderList.GetNextOrderNumber()); 
		o.SetSpecialInstructions();
		return o;
	}
	
	
	//Helper functions
	private static void DisplayCustomers(CustomerList cList) {
		System.out.println("Please select a customer from the list below by entering the corresponding number");
		System.out.println(cList.toString());
	}
	
	
	
	private static int MakeCustomerSelection(CustomerList cList) {
		boolean validOptionSelected;
		do {
			DisplayCustomers(cList);
			int option = IOHandler.GetNumberFromUser();
			//NB: This code will have to be updated every time a new customer is added to the list
			//Find a better solution
			switch (option) {
				case 1: 
					//validOptionSelected = true;
					return 1;				
				case 2: 
					//validOptionSelected = true;
					return 2;			
				case 3: 
					//validOptionSelected = true;
					return 3;				
				default:
					validOptionSelected = false;
			}
		} while (!validOptionSelected);
		return 0;
}
	//This is duplicated code. Can be simplified by introducing an interface
	private static void DisplayRestaurants(RestaurantList rList) {
		System.out.println("Please select a restaurant from the list below by entering the corresponding number");
		System.out.println(rList.toString());
	}
	
	
	//This is duplicated code. Can be simplified by introducing an interface
	private static int MakeRestaurantSelection(RestaurantList rList) {
		boolean validOptionSelected;
		do {
			DisplayRestaurants(rList);
			int option = IOHandler.GetNumberFromUser();
			//NB: This code will have to be updated every time a new customer is added to the list
			//Find a better solution
			switch (option) {
				case 1: 
					//validOptionSelected = true;
					return 1;				
				case 2: 
					//validOptionSelected = true;
					return 2;			
				case 3: 
					//validOptionSelected = true;
					return 3;				
				default:
					validOptionSelected = false;
			}
		} while (!validOptionSelected);
		return 0;
	}
	
	public void TakeFoodOrder() {
		char result;
		MealList mList = new MealList();
		do {
			//DisplayFoodOrderMessage();
			
			boolean validOptionSelected;
			do {
				DisplayMenu(); 
				int option = IOHandler.GetNumberFromUser();
				MealLineItem mealLineItem = new MealLineItem();
				switch (option) {
					case 1: 
						validOptionSelected = true;
						mealLineItem.SetMeal(restaurant.GetMenu().GetMealBy(option));
						System.out.println("How many would you like?");
						mealLineItem.SetQuantity(IOHandler.GetNumberFromUser());
						mList.Add(mealLineItem);
						//System.out.println(mealLineItem.toString());
						break;
					case 2: 
						validOptionSelected = true;
						mealLineItem.SetMeal(restaurant.GetMenu().GetMealBy(option));
						System.out.println("How many would you like?");
						mealLineItem.SetQuantity(IOHandler.GetNumberFromUser());
						mList.Add(mealLineItem);
						break;
					case 3: 
						validOptionSelected = true;
						mealLineItem.SetMeal(restaurant.GetMenu().GetMealBy(option));
						System.out.println("How many would you like?");
						mealLineItem.SetQuantity(IOHandler.GetNumberFromUser());
						mList.Add(mealLineItem);
						break;
				default:
					validOptionSelected = false;
				}
			} while (!validOptionSelected);
			
			do {
				result = OrderAnotherMeal();
			} while(result != 'y' && result != 'n');
		} while(result == 'y');
		this.mealList = mList;
		//System.out.println("Printing meal list:");
		//System.out.println(this.mealList.toString());
	}
	
	private void DisplayMenu() {
		System.out.println("Please select a meal from the list below by entering the corresponding number");
		System.out.println(restaurant.menu.toString());
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
