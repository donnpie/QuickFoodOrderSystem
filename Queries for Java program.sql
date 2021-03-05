use QuikFoodMS
Go

--Query for MakeCustomerList
--Creates a bunch of customer objects and add them to a list
--Sample data object: Customer cust1 = new Customer("Jill Jack", "123 456 7890", "jilljack@yahoo.com", "12 Cherry Road", "Plumstead", "Cape Town", 1);
SELECT c.customerID
	, c.name AS custName
	,c.contactNumber
	,c.email
	,c.streetAddress
	,c.suburb
	,l.locationId
	,l.name As locName
FROM Customer AS c INNER JOIN Location AS l ON c.locationId = l.locationId
WHERE c.customerID = 1

--Query for MakeRestaurantList
--Creates a bunch of restaurant objects and add them to a list
SELECT r.restaurantId
	, r.name AS restName
	,r.contactNumber
	,l.locationId
	,l.name As locName
FROM Restaurant AS r INNER JOIN Location AS l ON r.locationId = l.locationId

--Query for MakeDriverList
--Creates a bunch of restaurant objects and add them to a list
SELECT d.driverId
	, d.name AS driverName
	,l.locationId
	,l.name As locName
	,d.numOfDeliveries
FROM Driver AS d INNER JOIN Location AS l ON d.locationId = l.locationId

--Query for DisplayCustomers
--Creates a list of customers that is presented to the user to make a selection
SELECT c.customerID
	, c.name AS custName
	,l.name As locName
FROM Customer AS c INNER JOIN Location AS l ON c.locationId = l.locationId

--Query for MakeCustomerSelection
--User selects customer by customerId. This query must check if customer entered a valid customerId
SELECT count(c.customerID)
FROM Customer AS c
WHERE c.customerID = 1

--Query for DisplayMenu
SELECT m.mealId
	,m.name
	,m.price
FROM  MenuItem AS mi INNER JOIN meal AS m ON mi.mealId = m.mealId
WHERE mi.restuarantId = 1 --Get restaurantId from restaurant object in order object


--Query for PickDriverInAreaWithLowestNumberOfOrders
--Find the driver in the area with the lowest number of deliveries
SELECT TOP(1)* 
FROM Driver
WHERE locationId = 1 --LocationId to be provided as parameter
ORDER BY numOfDeliveries ASC


--Query to create new order
INSERT INTO [Order]
           (customerId
           ,restaurantId
           ,driverId
           ,specialInstructions
           ,isComplete
           ,completionDate)
     VALUES
           (1
           ,1
           ,1
           ,'test'
           ,NULL
           ,NULL)

--Query for GetNextOrderNumber
--Get the order number for the last order
SELECT orderId
FROM [Order]
WHERE orderId = (SELECT MAX(orderId) FROM [Order])

--Query to get all meals for given restaurant
SELECT mi.mealId
		,m.name AS mealName
		,m.price
FROM MenuItem AS mi INNER JOIN Meal AS m on mi.mealId = m.mealId
WHERE mi.restuarantId = 1



--Query to insert new customer
INSERT INTO Customer
           (name
           ,contactNumber
           ,email
           ,streetAddress
           ,suburb
           ,locationId)
     VALUES
           ('name'
           ,'number'
           ,'email'
           ,'street'
           ,'suburb'
           ,1)


--Query to update customer
UPDATE Customer
   SET [name] = 'name'
      ,contactNumber = 'contactNumber'
      ,email = 'email'
      ,streetAddress = 'streetAddress'
      ,suburb ='suburb'
      ,locationId = 1
 WHERE customerId = 1
GO

--Query to find locationId
SELECT locationId
  FROM [Location]
  WHERE [name] = 'Cape Town'

--Query to find incomplete orders
SELECT *
FROM [Order]
WHERE isComplete = NULL OR isComplete = 0

--Query to finalise order
UPDATE [Order]
   SET 
      isComplete = 1
      ,completionDate = '2021-03-02' --Date to be supplied as parameter
 WHERE orderId = 1 --orderId to be supplied as parameter
GO

