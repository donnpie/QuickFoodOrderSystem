package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Meal;
import main.Menu;

class MenuTests {

	@Test
	void GetMealBy_ShouldWork() {
		Menu menu = new Menu();
		menu.Add(new Meal("Meal1", 1, 50.00));
		Meal m = menu.GetMealById(1);
		
		assertEquals(m.GetName(), "Meal1");
		assertEquals(m.GetId(), 1);
		assertEquals(m.GetPrice(), 50);
		
	}

}
