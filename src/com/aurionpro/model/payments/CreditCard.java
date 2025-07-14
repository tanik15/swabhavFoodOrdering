package com.aurionpro.model.payments;

public class CreditCard implements IPayment {
	@Override
	public void payAmount(double amount) {
		// TODO Auto-generated method stub
		System.out.println("\n"+amount + " paid using credit Card successfully!");
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CreditCard";
	}
}
