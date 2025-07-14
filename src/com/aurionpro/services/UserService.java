package com.aurionpro.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aurionpro.exceptions.login.CustomerNotFoundException;
import com.aurionpro.exceptions.login.InvalidContactNumberException;
import com.aurionpro.exceptions.login.InvalidPasswordException;
import com.aurionpro.exceptions.login.UsernameNotAvailable;
import com.aurionpro.model.login.Admin;
import com.aurionpro.model.login.Customer;
import com.aurionpro.model.login.User;
import com.aurionpro.model.order.Order;

public class UserService {
	private static List<User> customers = new ArrayList<>();
	private Admin admin;
	public static User currentUser;

	public UserService() throws ClassNotFoundException, IOException {
		this.admin = new Admin("admin", "admin123", "Tanik", "Virar", "9970508512");
		loadCustomers();
	}

	public static void updateCustomerOrder(Order order) {
		int index = customers.indexOf(currentUser);
		currentUser.addOrders(order);
		customers.set(index, currentUser);
		try {
			FileOutputStream fos = new FileOutputStream("Customer");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(customers);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void loadCustomers() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("Customer");
		ObjectInputStream ois = new ObjectInputStream(fis);
		customers = (List<User>) ois.readObject();
		fis.close();
		ois.close();
	}

	public void displayOnlyCustomer(User user) {
		if (user.getRole().equals("Admin")) {
			for (User customer : customers) {
				System.out.println("\nUser Name      : " + customer.getUsername());
				System.out.println("Name           : " + customer.getName());
				System.out.println("Address        : " + customer.getAddress());
			}
		}
	}

	public void displayCustomer(User user) {
		if (user.getRole().equals("Admin")) {
			for (User customer : customers) {
				System.out.println("\nUser Name      : " + customer.getUsername());
				System.out.println("Name           : " + customer.getName());
				System.out.println("Address        : " + customer.getAddress());
				System.out.println("Contact Number : " + customer.getContactNumber());
				System.out.println("Orders");
				customer.getOrders().forEach((order) -> {
					System.out.println(order.displayOrderList());
				});
			}
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		String regex = "^(\\+91|91)?[6-9][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	public User registerCustomer(String username, String password, String name, String address, String contactNumber)
			throws IOException, UsernameNotAvailable, InvalidContactNumberException, InvalidPasswordException {
		for (User customer : customers) {
			if (customer.getUsername().equals(username) || username.equals("admin")) {
				throw new UsernameNotAvailable();
			}
			if (!isValidPhoneNumber(contactNumber)) {
				throw new InvalidContactNumberException();
			}
			if (password.length() < 6) {
				throw new InvalidPasswordException();
			}
		}
		User customer = new Customer(username, password, name, address, contactNumber);
		customers.add(customer);
		currentUser = customer;
		System.out.println("Customer registered!");
		FileOutputStream fos = new FileOutputStream("Customer");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(customers);
		oos.close();
		fos.close();
		return currentUser;
	}

	public User login(String username, String password) throws CustomerNotFoundException {
		if (admin.login(username, password)) {
			currentUser = admin;
			return admin;
		}

		for (User c : customers) {
			if (c.login(username, password)) {
				currentUser = c;
				return c;
			}
		}
		throw new CustomerNotFoundException();
	}

}
