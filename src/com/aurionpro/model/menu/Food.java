package com.aurionpro.model.menu;

import java.io.Serializable;

import com.aurionpro.enums.food.FoodType;

public class Food implements Serializable{
	private int foodId;
	private String foodName;
	private Double foodPrice;
	private Double foodRating;
	private String foodIngrediants ;
	private FoodType foodType;
	private double foodDiscount;
	
	
	public double getFoodDiscount() {
		return foodDiscount;
	}


	public void setFoodDiscount(double foodDiscount) {
		this.foodDiscount = foodDiscount;
	}


	public int getFoodId() {
		return foodId;
	}


	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}


	public FoodType getFoodtype() {
		return foodType;
	}


	public void setFoodtype(FoodType foodtype) {
		this.foodType = foodtype;
	}


	public String getFoodName() {
		return foodName;
	}


	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}


	public Double getFoodPrice() {
		return foodPrice;
	}


	public void setFoodPrice(Double foodPrice) {
		this.foodPrice = foodPrice;
	}


	public Double getFoodRating() {
		return foodRating;
	}


	public void setFoodRating(Double foodRating) {
		this.foodRating = foodRating;
	}


	public String getFoodIngrediants() {
		return foodIngrediants;
	}


	public void setFoodIngrediants(String foodIngrediants) {
		this.foodIngrediants = foodIngrediants;
	}


	public Food(int foodId,double foodDiscount,String foodName, Double foodPrice,Double foodRating, String foodIngrediants , FoodType foodType) {
		super();
		this.foodDiscount = foodDiscount;
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodRating = foodRating;
		this.foodIngrediants = foodIngrediants;
		this.foodType = foodType;
	}
}
