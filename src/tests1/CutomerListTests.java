package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Customer;
import main.CustomerList;

class CutomerListTests {

	@Test
	public void GetCustomerBy_ShouldWork() {
		//Arrange
		String expectedName = "name";
		String expectedTelNumber = "telNumber";
		String expectedEmail = "email";
		String expectedStreetAddress = "streetAddress";
		String expectedSuburb = "suburb";
		String expectedLocation = "location";
		int expectedId = 1;
		Customer cNew = new Customer(expectedName, expectedTelNumber, expectedEmail, expectedStreetAddress, expectedSuburb, expectedLocation, expectedId);
		Customer cNew2 = new Customer("Name2", "TelNumber2", "Email2", "StreetAddress2", "Suburb2", "Location2", 2);
		CustomerList l = new CustomerList();
		
		//Act
		l.Add(cNew);
		l.Add(cNew2);
		
		Customer c = l.GetCustomerBy(1);
		
		assertEquals(c.GetName(), expectedName);
		assertEquals(c.GetTelNumber(), expectedTelNumber);
		assertEquals(c.GetEmail(), expectedEmail);
		assertEquals(c.getStreetAddress(), expectedStreetAddress);
		assertEquals(c.getSuburb(), expectedSuburb);
		assertEquals(c.GetLocation().toString(), expectedLocation);
		assertEquals(c.GetId(), expectedId);
	}
	

}
