package com.aurionpro.login;

public class InvalidContactNumber extends Throwable {
	public InvalidContactNumber() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid contact number";
	}
}
