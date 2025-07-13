package com.aurionpro.payments;

public class Upi implements IPayment {
	
	@Override
	public void payAmount(double amount) {
		// TODO Auto-generated method stub
		System.out.println("\n"+amount + " paid using UPI successfully!.");
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "UPI";
	}
}
