package com.aurionpro.model.menu;

import java.io.Serializable;
import java.util.List;

public class Starter extends FoodItem implements Serializable{
	
	private String listName = "Starter";


	@Override
	public String getFoodItemName() {
		return listName;
	}
	public Starter() {
		super(null,0);
	}
	
	public Starter(List<Food> items, double discountPercent) {
		super(items, discountPercent);
		super.setDiscountPercent(discountPercent);
	}


	



	

}
