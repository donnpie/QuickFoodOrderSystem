package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Scanner;
/**
 * Handles all connections to the outside world
 * @author donnp
 *
 */
public class IOHandler {
	
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				 "jdbc:sqlserver://localhost;database=QuikFoodMS",
				 "test",
				 "test"
		 );
		return connection;
	}
	
	public static Statement createStatement(Connection connection)  throws SQLException {
		return connection.createStatement();
	}
	
	public static CustomerList MakeCustomerList() {
		/**
		 * Queries the database to retrieve a list of customer objects.
		 */
		//Create empty list
		CustomerList customerList = new CustomerList();
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get customers from DB
			ResultSet results = statement.executeQuery("SELECT c.customerID\r\n"
					+ "	, c.name AS custName\r\n"
					+ "	,c.contactNumber\r\n"
					+ "	,c.email\r\n"
					+ "	,c.streetAddress\r\n"
					+ "	,c.suburb\r\n"
					+ "	,l.locationId\r\n"
					+ "	,l.name As locName\r\n"
					+ "FROM Customer AS c INNER JOIN Location AS l ON c.locationId = l.locationId");
			
			//Loop through results, create new customer object and add to list
			 while (results.next()) {
//				//For debug:					 
//				 System.out.println(
//					 results.getInt("customerId") + ", " +
//					 results.getString("custName") + ", " +
//					 results.getString("contactNumber") + ", " +
//					 results.getString("email") + ", " +
//					 results.getString("streetAddress") + ", " +
//					 results.getString("suburb") + ", " +
//					 results.getInt("locationId") + ", " +
//					 results.getString("locName") + ", " 
//
//				 );
				 Customer c = new Customer(results.getString("custName"),
						 results.getString("contactNumber"),
						 results.getString("email"),
						 results.getString("streetAddress"),
						 results.getString("suburb"),
						 new Location(results.getInt("locationId"), results.getString("locName")),
						 results.getInt("customerId")
					 );
				 customerList.Add(c);
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(customerList.toString());
		return customerList;
	}

	public static RestaurantList MakeRestaurantList() {
		/**
		 * Queries the database to retrieve a list of restaurant objects.
		 */
		//Create empty list
		RestaurantList restaurantList = new RestaurantList();
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get customers from DB
			ResultSet results = statement.executeQuery("SELECT r.restaurantId\r\n"
					+ "	, r.name AS restName\r\n"
					+ "	,r.contactNumber\r\n"
					+ "	,l.locationId\r\n"
					+ "	,l.name As locName\r\n"
					+ "FROM Restaurant AS r INNER JOIN Location AS l ON r.locationId = l.locationId");
			
			//Loop through results, create new object and add to list
			 while (results.next()) {
				 Restaurant r = new Restaurant(results.getString("restName"),
						 results.getString("contactNumber"),
						 new Location(results.getInt("locationId"), results.getString("locName")),
						 results.getInt("restaurantId")
					 );
				 restaurantList.Add(r);
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(restaurantList.toString());
		return restaurantList;
	}
	
	
	public static DriverList MakeDriverList() {
		/**
		 * Queries the database to retrieve a list of driver objects.
		 */
		//Create empty list
		DriverList driverList = new DriverList();
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get customers from DB
			ResultSet results = statement.executeQuery("SELECT d.driverId\r\n"
					+ "	, d.name AS driverName\r\n"
					+ "	,l.locationId\r\n"
					+ "	,l.name As locName\r\n"
					+ "	,d.numOfDeliveries\r\n"
					+ "FROM Driver AS d INNER JOIN Location AS l ON d.locationId = l.locationId");
			
			//Loop through results, create new object and add to list
			 while (results.next()) {
				 Driver d = new Driver(results.getString("driverName"),
						 new Location(results.getInt("locationId"), results.getString("locName")),
						 results.getInt("numOfDeliveries"),
						 results.getInt("driverId")
					 );
				 driverList.Add(d);
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(driverList.toString());
		return driverList;
	}
	
	public static boolean SendOrderToDB(Order o) {
		/**
		 * Send a request to the database to create a new order
		 */
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Write data to db
			statement.executeUpdate("INSERT INTO [Order] "
					+ "           (customerId "
					+ "           ,restaurantId "
					+ "           ,driverId "
					+ "           ,specialInstructions "
					+ "           ,isComplete "
					+ "           ,completionDate) "
					+ "     VALUES "
					+ "           (" + o.GetCustomer().GetId()
					+ "           ," + o.GetRestaurant().GetId()
					+ "           ," + o.GetDriver().GetId()
					+ "           ,'" + o.GetSpecialInstructions() +"'"
					+ "           , NULL "
					+ "           , NULL)");
					
			// Close connections
			statement.close();
			connection.close();
			//System.out.println("Query successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public static boolean SendFoodOrderToDB(Order order) {
		/**
		 * Add food to an existing order in the database
		 */
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Write data to db
			int orderNumber = order.GetOrderNumber();
			MealList mlist = order.GetMealList();
			
			//TODO: An unhandled exception occurs if the user enters the same meal in the same order more than once
			//Once a meal is ordered it cannot be ordered again on the same order
			for (int i = 0; i < mlist.size(); i++) {
			statement.executeUpdate("INSERT INTO [MealListItem] "
					+ "           (orderId "
					+ "           ,mealId "
					+ "           ,qty ) "
					+ "     VALUES "
					+ "           (" + orderNumber
					+ "           ," + mlist.GetMealLineItemByIndex(i).GetId() //Get the mealId
					+ "           ," + mlist.GetMealLineItemByIndex(i).GetQuantity() //Get the quantity
					+ ")"
					);
			}
					
			// Close connections
			statement.close();
			connection.close();
			//System.out.println("Query successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void MakeMenu(Order order) {
		/**
		 * Creates a menu for the given order.
		 * The menu is bound to the restaurant of the order
		 * Customer must choose meals from the menu
		 */
		
		//Get restaurant id
		int restId = order.GetRestaurant().GetId();
		
		Menu menu = new Menu();
		//Get meals for this restaurant from the db
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get customers from DB
			ResultSet results = statement.executeQuery("SELECT mi.mealId\r\n"
					+ "		,m.name AS mealName\r\n"
					+ "		,m.price\r\n"
					+ "FROM MenuItem AS mi INNER JOIN Meal AS m on mi.mealId = m.mealId\r\n"
					+ "WHERE mi.restuarantId = " + restId);
			
			//Loop through results, create new object and add to list
			 while (results.next()) {
				 Meal m = new Meal(results.getString("mealName"),
						 results.getInt("mealId"),
						 results.getDouble("price")
					 );
				 menu.Add(m);
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(driverList.toString());
		order.GetRestaurant().SetMenu(menu);

		
	}
	
	public static int GetNumberFromUser() {
		/**
		 * Gets numerical input from user	
		 */
			Scanner s = new Scanner(System.in);
			int result = 0;
			while (s.hasNext()) {
				result = s.nextInt();
				break;
			}
			//s.close();  //This line causes infinite loop - why???
			return result;
	}
	
	public static String GetNextLineFromUser() {
		/**
		 * Gets string input from user	
		 */
			Scanner s = new Scanner(System.in);
			String result = "";
			while (s.hasNextLine()) {
				result = s.nextLine();
				break;
			}
			//s.close(); //This line causes infinite loop - why???
			return result;
	}
	
	public static void WriteToFile(String inputText, String pathAndFileName) {
		//Writes inputText to file
		try {
			Scanner scnr = new Scanner(inputText);
			Formatter f1 = new Formatter(pathAndFileName);
			while(scnr.hasNextLine()){
				String nextLine = scnr.nextLine();
				f1.format("%s", nextLine +"\r\n");
				//System.out.println("nextLine: " + nextLine);
			}  
			f1.close(); 
			scnr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File was not found. " + e.getMessage());
		}
	}

	public static int GetLastOrderNumber() {
		/**
		 * Queries the database to retrieve a list of driver objects.
		 */
		//Create empty list
		int lastOrderNumber = 0;
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get order numbre from DB
			ResultSet results = statement.executeQuery("SELECT orderId\r\n"
					+ "FROM [Order]\r\n"
					+ "WHERE orderId = (SELECT MAX(orderId) FROM [Order])");
			
			//Loop through results, create new object and add to list
			 while (results.next()) {
				 lastOrderNumber = results.getInt("orderId");
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(driverList.toString());
		return lastOrderNumber;
	}
	
	public static Customer GetCustomerById(int id) {
		/**
		 * Queries the database to retrieve a customer with the specified id
		 */
		//Create empty list
		Customer c = null;
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get data from DB
			ResultSet results = statement.executeQuery("SELECT c.customerID\r\n"
					+ "	, c.name AS custName\r\n"
					+ "	,c.contactNumber\r\n"
					+ "	,c.email\r\n"
					+ "	,c.streetAddress\r\n"
					+ "	,c.suburb\r\n"
					+ "	,l.locationId\r\n"
					+ "	,l.name As locName\r\n"
					+ "FROM Customer AS c INNER JOIN Location AS l ON c.locationId = l.locationId "
					+ "WHERE c.customerID = " + id
					);
			
			//Loop through results, create new customer object and add to list
			 while (results.next()) {
//				//For debug:					 
//				 System.out.println(
//					 results.getInt("customerId") + ", " +
//					 results.getString("custName") + ", " +
//					 results.getString("contactNumber") + ", " +
//					 results.getString("email") + ", " +
//					 results.getString("streetAddress") + ", " +
//					 results.getString("suburb") + ", " +
//					 results.getInt("locationId") + ", " +
//					 results.getString("locName") + ", " 
//
//				 );
				 c = new Customer(results.getString("custName"),
						 results.getString("contactNumber"),
						 results.getString("email"),
						 results.getString("streetAddress"),
						 results.getString("suburb"),
						 new Location(results.getInt("locationId"), results.getString("locName")),
						 results.getInt("customerId")
					 );
			 }
					
			// Close connections
			results.close();
			statement.close();
			connection.close();
			//System.out.println("Query successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(driverList.toString());
		return c;
	}

	public static int GetLocationId(String location) {
		/**
		 * Check if the given location is valid and returns the corresponding locationId
		 * Returns 0 if the location is not valid
		 */
		//Create empty list
				int locationId = 0;
				try {
					//Establish connection
					Connection connection = IOHandler.getConnection();
					Statement statement = IOHandler.createStatement(connection);
					
					//Get locationId from DB
					ResultSet results = statement.executeQuery("SELECT locationId\r\n"
							+ "  FROM [Location]\r\n"
							+ "  WHERE [name] = '"+location+"'");
					
					//Loop through results, create new object and add to list
					 while (results.next()) {
						 locationId = results.getInt("locationId");
					 }
							
					// Close connections
					results.close();
					statement.close();
					connection.close();
					//System.out.println("Query successful");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//System.out.println(driverList.toString());
				return locationId;
	}

	public static boolean AddNewCustomer(String name, String contactNumber, String email, String streetAddress,
			String suburb, int locationId) {
		/**
		 * Add food to an existing order in the database
		 */
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Write data to db
			statement.executeUpdate("INSERT INTO Customer\r\n"
					+ "    (name\r\n"
					+ "    ,contactNumber\r\n"
					+ "    ,email\r\n"
					+ "    ,streetAddress\r\n"
					+ "    ,suburb\r\n"
					+ "    ,locationId)\r\n"
					+ "VALUES\r\n"
					+ "    ('"+name+"'"
					+ "    ,'"+contactNumber+"'"
					+ "    ,'"+email+"'"
					+ "    ,'"+streetAddress+"'"
					+ "    ,'"+suburb+"'"
					+ "    ,"+locationId+")"
					);
			
					
			// Close connections
			statement.close();
			connection.close();
			//System.out.println("Query successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean UpdateCustomer(int customerId, String name, String contactNumber, String email, String streetAddress,
			String suburb, int locationId) {
		/**
		 * Update an existing customer in the database
		 */
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Write data to db
			statement.executeUpdate("UPDATE Customer"
					+ "	   SET [name] = '"+name+"'"
					+ "	      ,contactNumber = '"+contactNumber+"'"
					+ "	      ,email = '"+email+"'"
					+ "	      ,streetAddress = '"+streetAddress+"'"
					+ "	      ,suburb ='"+suburb+"'"
					+ "	      ,locationId = " + locationId
					+ "	 WHERE customerId = " + customerId
					);
			
					
			// Close connections
			statement.close();
			connection.close();
			//System.out.println("Query successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
}

//Rough work
//
//while (s.hasNextLine()) {

	
	
	//find the first comma
	//int firstComma = line.indexOf(line);
	//String name = 

