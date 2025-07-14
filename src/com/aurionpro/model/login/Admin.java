package com.aurionpro.model.login;

public class Admin extends User {

    public Admin(String username, String password,String name,String address, String contactNumber) {
        super(username, password,name ,address,contactNumber);
    }

    @Override
    public String getRole() {
        return "Admin";
    }
}

