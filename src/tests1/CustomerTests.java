package tests1;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Customer;

class CustomerTests {

	@Test
	public void Customer_OverloadedCtor_ShouldCreateObjCorrectly() {
		//Expected values
		String expectedName = "name";
		String expectedTelNumber = "telNumber";
		String expectedEmail = "email";
		String expectedStreetAddress = "streetAddress";
		String expectedSuburb = "suburb";
		String expectedLocation = "location";
		int expectedId = 1;
		
		Customer c = new Customer(expectedName, expectedTelNumber, expectedEmail, expectedStreetAddress, expectedSuburb, expectedLocation, expectedId);
		
		assertEquals(c.GetName(), expectedName);
		assertEquals(c.GetTelNumber(), expectedTelNumber);
		assertEquals(c.GetEmail(), expectedEmail);
		assertEquals(c.getStreetAddress(), expectedStreetAddress);
		assertEquals(c.getSuburb(), expectedSuburb);
		assertEquals(c.GetLocation().toString(), expectedLocation);
		assertEquals(c.GetId(), expectedId);
	}

}
