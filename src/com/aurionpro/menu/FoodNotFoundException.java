package com.aurionpro.menu;

public class FoodNotFoundException extends Throwable{
	public FoodNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Food Not Found";
	}
}
