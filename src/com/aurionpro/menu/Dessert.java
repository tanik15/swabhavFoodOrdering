package com.aurionpro.menu;

import java.io.Serializable;
import java.util.List;

public class Dessert extends FoodItem implements Serializable{
	private String listName = "Dessert";

	public Dessert(List<Food> items, double discountPercent) {
		super(items, discountPercent);
		super.setDiscountPercent(discountPercent);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getFoodItemName() {
		// TODO Auto-generated method stub
		return listName;
	}
}
