package com.aurionpro.model.delivery;

public class Swiggy implements IDelivery {
	public static boolean isActive = true;
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
	@Override
	public String getAvailabilityStatus() {
		// TODO Auto-generated method stub
		if(isActive) {
			return "Active";
		}
		return "InActive";
	}
	
	@Override
	public void setAvailabililtyStatus(boolean isActive) {
		// TODO Auto-generated method stub
		this.isActive = isActive;
		
	}
}
