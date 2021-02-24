package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Driver;
import main.DriverList;
import main.IOHandler;

class IOHandlerTests {

	@Test
	public void MakeDriverList_HappyPath() {
		DriverList driverList = IOHandler.MakeDriverList("./data/drivers.txt");

		Driver d = driverList.GetDriverByIndex(0);
		
		assertEquals(d.GetName(), "Julie Carty");
		assertEquals(d.GetLocation().toString(), "Cape Town");
		assertEquals(d.GetNumOfDeliveries(), 6);
		
	}

//	public void GetNumberFromUser_HappyPath() {
		//Fore some reason, this test does not run.
//		//User must enter 6
//		System.out.println("Enter 6 and press enter:");
//		int actual = IOHandler.GetNumberFromUser();
//		assertEquals(6, actual);
//	}
	
}
//