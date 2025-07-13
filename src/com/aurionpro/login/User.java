package com.aurionpro.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.order.Order;

public abstract class User implements Serializable{
    protected String username;
    protected String password;
    protected String name;
    protected String address;
    protected String contactNumber;
    protected List<Order> orders = new ArrayList<>();
    

    public List<Order> getOrders() {
		return orders;
	}



	public void addOrders(Order order) {
		orders.add(order);
	}



	public User(String username, String password,String name,String address, String contactNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address= address;
        this.contactNumber = contactNumber;
    }

    

	public String getAddress() {
		return address;
	}



	public String getName() {
		return name;
	}


	public abstract String getRole(); 

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }
    public String getContactNumber() {
    	return contactNumber;
    }
}
