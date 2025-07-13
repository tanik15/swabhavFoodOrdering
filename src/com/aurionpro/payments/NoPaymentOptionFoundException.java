package com.aurionpro.payments;

public class NoPaymentOptionFoundException extends Throwable{
	public NoPaymentOptionFoundException() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No payment option found";
	}
}
