package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Customer;
import main.Restaurant;

class RestaurantTests {

	@Test
	void test() {
		//Expected values
		String expectedName = "name";
		String expectedTelNumber = "telNumber";
		String expectedLocation = "location";
		int expectedId = 1;
		
		Restaurant r = new Restaurant(expectedName, expectedTelNumber, expectedLocation, expectedId);
		
		assertEquals(r.GetName(), expectedName);
		assertEquals(r.GetTelNumber(), expectedTelNumber);
		assertEquals(r.GetLocation().toString(), expectedLocation);
		assertEquals(r.GetId(), expectedId);
	}

}
