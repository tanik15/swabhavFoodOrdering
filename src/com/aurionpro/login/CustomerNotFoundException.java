package com.aurionpro.login;

public class CustomerNotFoundException extends Throwable{
	
	public CustomerNotFoundException() {
		
	}
	@Override
	public String getMessage() {
		return "Customer not Found";
	}
}
