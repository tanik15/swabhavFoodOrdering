package com.aurionpro.login;

public class InvalidPassword extends Throwable {
	public InvalidPassword() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Password. Password Must be atleast 6 digit";
	}
}
