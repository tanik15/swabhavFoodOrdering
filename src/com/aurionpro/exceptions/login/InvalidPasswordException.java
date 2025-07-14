package com.aurionpro.exceptions.login;

public class InvalidPasswordException extends Throwable {
	public InvalidPasswordException() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Password. Password Must be atleast 6 digit";
	}
}
