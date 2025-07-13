package com.aurionpro.login;

public class UsernameNotAvailable  extends Throwable{
	public UsernameNotAvailable() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Username not available";
	}
}
