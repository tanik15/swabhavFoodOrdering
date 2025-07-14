package com.aurionpro.model.login;

public class Customer extends User {

    public Customer(String username, String password,String name,String address, String contactNumber) {
        super(username, password,name ,address,contactNumber);
    }

    @Override
    public String getRole() {
        return "Customer";
    }
    
}
