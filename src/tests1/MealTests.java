package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Driver;
import main.DriverList;
import main.Meal;
import main.Restaurant;

class MealTests {

	@Test
	void Meal_OverloadedCtor_ShouldCreateObjCorrectly() {
		//Expected values
		String expectedName = "Meal1";
		int expectedId = 1;
		double expectedPrice = 55.33;
		
		Meal m = new Meal(expectedName, expectedId, expectedPrice);
		
		assertEquals(m.GetName(), expectedName);
		assertEquals(m.GetId(), expectedId);
		assertEquals(m.GetPrice(), expectedPrice);
	}

}
