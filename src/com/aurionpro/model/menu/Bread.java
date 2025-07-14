package com.aurionpro.model.menu;

import java.io.Serializable;
import java.util.List;

public class Bread extends FoodItem implements Serializable{
	private String listName = "Bread";

	public Bread(List<Food> items, double discountPercent) {
		super(items, discountPercent);
		setDiscountPercent(discountPercent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFoodItemName() {
		// TODO Auto-generated method stub
		return listName;
	}
}
