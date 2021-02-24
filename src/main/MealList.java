package main;
import java.util.ArrayList;
import java.util.List;

public class MealList {
	List<MealLineItem> list = new ArrayList<MealLineItem>();
	
	public void Add(MealLineItem mli) {
		list.add(mli);
	}
	
	public String toString() {
		//returns a string representing all MealLineItem objects
		String result = new String();
		for (int i = 0; i < list.size(); i++) {
			MealLineItem item = list.get(i);
			result += item.GetQuantity() + " x ";
			result += item.GetName() + " ";
			result += "(R " + item.GetTotalAmount() + ")\n";
		}
		return result;
	}

	public double CalcTotalAmount() {
		double result = 0;
		for (int i = 0; i < list.size(); i++) {
			MealLineItem item = list.get(i);
			result += item.GetTotalAmount();
		}
		return result;
	}
	
	public MealLineItem GetMealLineItemByIndex(int index) {
		if (index >= 0 && index < list.size()) {
			return list.get(index);
		}
		else return null;
	}
}
