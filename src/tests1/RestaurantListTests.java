package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Customer;
import main.CustomerList;
import main.Restaurant;
import main.RestaurantList;

class RestaurantListTests {

	@Test
	void GetRestaurantBy_ShouldWork() {
		//Arrange
		String expectedName = "name";
		String expectedTelNumber = "telNumber";
		String expectedEmail = "email";
		String expectedStreetAddress = "streetAddress";
		String expectedSuburb = "suburb";
		String expectedLocation = "location";
		int expectedId = 1;
		
		Restaurant rNew = new Restaurant(expectedName, expectedTelNumber, expectedLocation, expectedId);
		Restaurant rNew2 = new Restaurant("Name2", "TelNumber2", "Location2", 2);
		RestaurantList l = new RestaurantList();
		
		//Act
		l.Add(rNew);
		l.Add(rNew2);
		
		Restaurant r = l.GetRestaurantBy(1);
		
		assertEquals(r.GetName(), expectedName);
		assertEquals(r.GetTelNumber(), expectedTelNumber);
		assertEquals(r.GetLocation().toString(), expectedLocation);
		assertEquals(r.GetId(), expectedId);
	}

}
