package com.aurionpro.order;

import java.io.Serializable;

import com.aurionpro.menu.Food;

public class OrderItem implements Serializable{
	double discountAmount;
	Food foodItem;
	int quantity;
	public OrderItem(Food foodItem, int quantity) {
		super();
		this.foodItem = foodItem;
		this.quantity = quantity;
		this.discountAmount =(foodItem.getFoodPrice()  - (foodItem.getFoodPrice() * 0.01 * foodItem.getFoodDiscount()));
		}
	
	public double getDiscountAmount() {
		return discountAmount;
	}
	public Food getFoodItem() {
		return foodItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setFoodItem(Food foodItem) {
		this.foodItem = foodItem;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
