package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Customer;
import main.Driver;

class DriverTests {

	@Test
	void Driver_OverloadedCtor_ShouldCreateObjCorrectly() {
		//Expected values
		String expectedName = "name";
		String expectedLocation = "location";
		int expectedNumOfDeliveries = 1;
		
		Driver d = new Driver(expectedName, expectedLocation, expectedNumOfDeliveries);
		
		assertEquals(d.GetName(), expectedName);
		assertEquals(d.GetLocation().toString(), expectedLocation);
		assertEquals(d.GetNumOfDeliveries(), expectedNumOfDeliveries);
	}

}
