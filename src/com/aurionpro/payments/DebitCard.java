package com.aurionpro.payments;

public class DebitCard implements IPayment {
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DebitCard";
	}

	@Override
	public void payAmount(double amount) {
		// TODO Auto-generated method stub
		System.out.println("\n"+amount + " paid using Debit Card successfully");
	}
}
