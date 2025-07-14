package com.aurionpro.exceptions.login;

public class InvalidContactNumberException extends Throwable {
	public InvalidContactNumberException() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid contact number";
	}
}
