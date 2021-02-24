package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Driver;
import main.DriverList;
import main.Restaurant;

class DriverListTests {

	@Test
	void PickDriverInAreaWithLowestNumberOfOrders_HappyPath() {
		//Expected values
		String expectedName = "Driver1";
		String expectedLocation = "location";
		int expectedNumOfDeliveries = 1;
		
		Driver d1 = new Driver("Driver2", "location", 5);
		Driver d2 = new Driver(expectedName, expectedLocation, expectedNumOfDeliveries);
		Driver d3 = new Driver("Driver3", "Location3", 4);
		
		DriverList dl = new DriverList();
		dl.Add(d1);
		dl.Add(d2);
		dl.Add(d3);
		
		Restaurant r = new Restaurant("Name", "1234", "location", 1);
		
		Driver d = dl.PickDriverInAreaWithLowestNumberOfOrders(r);
		
		assertEquals(d.GetName(), expectedName);
		assertEquals(d.GetLocation().toString(), expectedLocation);
		assertEquals(d.GetNumOfDeliveries(), expectedNumOfDeliveries);
	}

}
