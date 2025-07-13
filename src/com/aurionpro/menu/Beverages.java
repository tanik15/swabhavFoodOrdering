package com.aurionpro.menu;

import java.io.Serializable;
import java.util.List;

public class Beverages extends FoodItem implements Serializable{
	private String listName = "Beverages";

	public Beverages(List<Food> items, double discountPercent) {
		super(items, discountPercent);
		setDiscountPercent(discountPercent);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getFoodItemName() {
		// TODO Auto-generated method stub
		return listName;
	}
}
