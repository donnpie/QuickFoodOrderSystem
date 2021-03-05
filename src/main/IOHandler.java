package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
/**
 * Handles all connections to the outside world
 * @author donnp
 *
 */
public class IOHandler {
	
	/**
	 * Sets up a connection to the database
	 * @return {@code Connection} object
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				 "jdbc:sqlserver://localhost;database=QuikFoodMS",
				 "test",
				 "test"
		 );
		return connection;
	}
	
	/**
	 * Returns a SQL {@code Statement} object
	 * Use IO Handler.Connection method to created the connection parameter
	 * @param connection 
	 * @throws SQLException
	 */
	public static Statement createStatement(Connection connection)  throws SQLException {
		return connection.createStatement();
	}
	
	/**
	 * Queries the database to retrieve a list of customer objects.
	 * @return
	 */
	public static CustomerList MakeCustomerList() {
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

	/**
	 * Queries the database to retrieve a list of restaurant objects.
	 */
	public static RestaurantList MakeRestaurantList() {
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
	
	/**
	 * Queries the database to retrieve a list of driver objects.
	 */
	public static DriverList MakeDriverList() {
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
	
	/**
	 * Send a request to the database to create a new order
	 */
	public static boolean SendOrderToDB(Order o) {
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
	

	/**
	 * Add food to an existing order in the database
	 */
	public static boolean SendFoodOrderToDB(Order order) {
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
	
	/**
	 * Creates a menu for the given order.
	 * The menu is bound to the restaurant of the order
	 * Customer must choose meals from the menu
	 */
	public static void MakeMenu(Order order) {
		
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
	
	/**
	 * Gets numerical input from user	
	 */
	public static int GetNumberFromUser() {
			Scanner s = new Scanner(System.in);
			int result = 0;
			while (s.hasNext()) {
				result = s.nextInt();
				break;
			}
			//s.close();  //This line causes infinite loop - why???
			return result;
	}
	
	/**
	 * Gets string input from user	
	 */
	public static String GetNextLineFromUser() {
			Scanner s = new Scanner(System.in);
			String result = "";
			while (s.hasNextLine()) {
				result = s.nextLine();
				break;
			}
			//s.close(); //This line causes infinite loop - why???
			return result;
	}

	/**
	 * Queries the database to retrieve the id of the last order
	 */
	public static int GetLastOrderNumber() {
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
	
	/**
	 * Queries the database to retrieve a customer with the specified id
	 */
	public static Customer GetCustomerById(int id) {
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

	/**
	 * Check if the given location is valid and returns the corresponding locationId
	 * Returns 0 if the location is not valid
	 * Caller must handle invalid locations
	 */
	public static int GetLocationId(String location) {
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

	/**
	 * Add food to an existing order in the database
	 */
	public static boolean AddNewCustomer(String name, String contactNumber, String email, String streetAddress,
			String suburb, int locationId) {
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

	/**
	 * Update an existing customer in the database
	 */
	public static boolean UpdateCustomer(int customerId, String name, String contactNumber, String email, String streetAddress,
			String suburb, int locationId) {
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

	/**
	 * Returns a list of orders where the orders are incomplete
	 * @return {@code OrderList} object containing incomplete orders
	 */
	public static OrderList GetIncompleteOrders() {
		//Create empty list
		OrderList list = new OrderList();
		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Get list from DB
			ResultSet results = statement.executeQuery("SELECT\r\n"
					+ "	o.orderId\r\n"
					+ "	,o.specialInstructions\r\n"
					+ "	,c.customerID\r\n"
					+ "	,c.name AS custName\r\n"
					+ "	,c.contactNumber AS custContactNum\r\n"
					+ "	,c.email\r\n"
					+ "	,c.streetAddress\r\n"
					+ "	,c.suburb\r\n"
					+ "	,c.locationId AS custLocationId\r\n"
					+ "	,cl.name AS custLocationName\r\n"
					+ "	,r.restaurantId\r\n"
					+ "	,r.name AS restName\r\n"
					+ "	,r.contactNumber AS restContactNum\r\n"
					+ "	,r.locationId AS restLocationId\r\n"
					+ "	,rl.name AS restLocationName\r\n"
					+ "	,d.driverId\r\n"
					+ "	,d.name AS driverName\r\n"
					+ "	,d.numOfDeliveries\r\n"
					+ "	,d.locationId AS driverLocationId\r\n"
					+ "	,dl.name AS driverLocationName\r\n"
					+ "FROM [Order] AS o INNER JOIN Customer AS c ON o.customerId = c.customerID\r\n"
					+ "	INNER JOIN Restaurant AS r ON o.restaurantId = r.restaurantId\r\n"
					+ "	INNER JOIN Driver AS d ON o.driverId = d.driverId\r\n"
					+ "	INNER JOIN Location AS cl ON c.locationId = cl.locationId\r\n"
					+ "	INNER JOIN Location AS rl ON c.locationId = rl.locationId\r\n"
					+ "	INNER JOIN Location AS dl ON c.locationId = dl.locationId\r\n"
					+ "WHERE  isComplete is NULL OR isComplete = 0");
			
			//Loop through results, create new object and add to list
			 while (results.next()) {
				 Location custLoc = new Location(results.getInt("custLocationId"), 
						 results.getString("custLocationName"));
				 Customer c = new Customer(results.getString("custName"), 
						 results.getString("custContactNum"), 
						 results.getString("email"), 
						 results.getString("streetAddress"), 
						 results.getString("suburb"),
						 custLoc, 
						 results.getInt("customerID"));
				 Location restLoc = new Location(results.getInt("restLocationId"),
						 results.getString("restLocationName"));
				 Restaurant r = new Restaurant(results.getString("restName"), 
						 results.getString("restContactNum"), 
						 restLoc, 
						 results.getInt("restLocationId"));
				 Location driverLoc = new Location(results.getInt("driverLocationId"),
						 results.getString("driverLocationName"));
				 Driver d = new Driver(results.getString("driverName"), 
						 driverLoc, 
						 results.getInt("numOfDeliveries"), 
						 results.getInt("driverId"));
				 Order o = new Order(results.getInt("orderId"), 
						 results.getString("specialInstructions"), 
						 c, 
						 r, 
						 d);
				 list.Add(o);
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
		return list;
	}

	/**
	 * Returns order specified by orderId
	 * @param id the id of the order to be returned
	 * @return {@code Order} object
	 */
	public static Order GetOrderById(int id) {
		//Create empty list
				Order o = null;
				try {
					//Establish connection
					Connection connection = IOHandler.getConnection();
					Statement statement = IOHandler.createStatement(connection);
					
					//Get data from DB
					ResultSet results = statement.executeQuery("SELECT\r\n"
							+ "	o.orderId\r\n"
							+ "	,o.specialInstructions\r\n"
							+ "	,o.isComplete\r\n"
							+ "	,o.completionDate\r\n"
							+ "	,c.customerID\r\n"
							+ "	,c.name AS custName\r\n"
							+ "	,c.contactNumber AS custContactNum\r\n"
							+ "	,c.email\r\n"
							+ "	,c.streetAddress\r\n"
							+ "	,c.suburb\r\n"
							+ "	,c.locationId AS custLocationId\r\n"
							+ "	,cl.name AS custLocationName\r\n"
							+ "	,r.restaurantId\r\n"
							+ "	,r.name AS restName\r\n"
							+ "	,r.contactNumber AS restContactNum\r\n"
							+ "	,r.locationId AS restLocationId\r\n"
							+ "	,rl.name AS restLocationName\r\n"
							+ "	,d.driverId\r\n"
							+ "	,d.name AS driverName\r\n"
							+ "	,d.numOfDeliveries\r\n"
							+ "	,d.locationId AS driverLocationId\r\n"
							+ "	,dl.name AS driverLocationName\r\n"
							+ "FROM [Order] AS o INNER JOIN Customer AS c ON o.customerId = c.customerID\r\n"
							+ "	INNER JOIN Restaurant AS r ON o.restaurantId = r.restaurantId\r\n"
							+ "	INNER JOIN Driver AS d ON o.driverId = d.driverId\r\n"
							+ "	INNER JOIN Location AS cl ON c.locationId = cl.locationId\r\n"
							+ "	INNER JOIN Location AS rl ON c.locationId = rl.locationId\r\n"
							+ "	INNER JOIN Location AS dl ON c.locationId = dl.locationId\r\n"
							+ "WHERE  orderId = " + id);
					
					//Loop through results, create new customer object and add to list
					 while (results.next()) {
						 Location custLoc = new Location(results.getInt("custLocationId"), 
								 results.getString("custLocationName"));
						 Customer c = new Customer(results.getString("custName"), 
								 results.getString("custContactNum"), 
								 results.getString("email"), 
								 results.getString("streetAddress"), 
								 results.getString("suburb"),
								 custLoc, 
								 results.getInt("customerID"));
						 Location restLoc = new Location(results.getInt("restLocationId"),
								 results.getString("restLocationName"));
						 Restaurant r = new Restaurant(results.getString("restName"), 
								 results.getString("restContactNum"), 
								 restLoc, 
								 results.getInt("restLocationId"));
						 Location driverLoc = new Location(results.getInt("driverLocationId"),
								 results.getString("driverLocationName"));
						 Driver d = new Driver(results.getString("driverName"), 
								 driverLoc, 
								 results.getInt("numOfDeliveries"), 
								 results.getInt("driverId"));
						 boolean isComplete = results.getInt("isComplete") == 1 ? true : false;
						 
						 o = new Order(results.getInt("orderId"), 
								 results.getString("specialInstructions"), 
								 c, 
								 r, 
								 d,
								 isComplete,
								 results.getString("completionDate"));
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
				return o;
	}

	/**
	 * Sets isComplete to true
	 * Sets completionDate to current date
	 * @param id id of the order to be completed
	 * @return true if order was completed successfully, false otherwise
	 */
	public static boolean UpdateOrderComplete(int id) {

		try {
			//Establish connection
			Connection connection = IOHandler.getConnection();
			Statement statement = IOHandler.createStatement(connection);
			
			//Create date string
			Date date = new Date();
			int year = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			int day = date.getDay();
			String dateString = new String(year + "-" + month + "-" + day);
			//System.out.println("Date: " + dateString)
			
			//Write data to db
			statement.executeUpdate("UPDATE [Order]"
					+ "			   SET "
					+ "			      isComplete = 1"
					+ "			      ,completionDate = '"+dateString+"'"
					+ "			 WHERE orderId = " + id
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