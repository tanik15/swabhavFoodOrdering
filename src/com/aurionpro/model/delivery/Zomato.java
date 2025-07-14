package com.aurionpro.model.delivery;

public class Zomato implements IDelivery {
	@Override
	public void assignDevlivery() {
		// TODO Auto-generated method stub
		System.out.println("\n✅ Zomato is assigned for the delivery");
	}
	
	@Override
	public String getDeliveryPartnerName() {
		// TODO Auto-generated method stub
		return "Zomato";
	}
}
