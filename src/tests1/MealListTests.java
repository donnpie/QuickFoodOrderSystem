package tests1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Meal;
import main.MealLineItem;
import main.MealList;

class MealListTests {

	@Test
	void Add_ShouldWork() {
		//Expected values
		String expectedName = "Meal1";
		int expectedQty = 2;
		double expectedPrice = 50;
		
		
		
		MealList ml = new MealList();
		MealLineItem mli1 = new MealLineItem();
		mli1.SetMeal(new Meal(expectedName, 1, expectedPrice));
		mli1.SetQuantity(expectedQty);
		ml.Add(mli1);
		
		MealLineItem mli2 = ml.GetMealLineItemByIndex(0);
		
		assertEquals(mli2.GetName(), expectedName);
		assertEquals(mli2.GetQuantity(), expectedQty);
		assertEquals(mli2.GetPrice(), expectedPrice);
		
	}
	
	public void CalcTotalAmount_ShoudWork() {
		MealList ml = new MealList();
		
		MealLineItem mli1 = new MealLineItem();
		mli1.SetMeal(new Meal("Name1", 1, 50));
		mli1.SetQuantity(2);
		ml.Add(mli1);
		
		MealLineItem mli2 = new MealLineItem();
		mli2.SetMeal(new Meal("Name1", 1, 40));
		mli2.SetQuantity(3);
		ml.Add(mli2);
		
		//Total should come to 2*50 + 3*40 = 220
		double expected = 220.0;
		assertEquals(mli2.GetTotalAmount(), expected);
		
	}
	

}
