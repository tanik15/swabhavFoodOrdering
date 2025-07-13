package com.aurionpro.delivery;

public class Swiggy implements IDelivery {
	@Override
	public void assignDevlivery() {
		// TODO Auto-generated method stub
		System.out.println("Swiggy is assigned for the delivery");
	}
	@Override
	public String getDeliveryPartnerName() {
		// TODO Auto-generated method stub
		return "Swiggy";
	}
}
