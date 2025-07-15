package com.aurionpro.app;

import java.io.IOException;
import java.util.Scanner;

import com.aurionpro.exceptions.login.CustomerNotFoundException;
import com.aurionpro.exceptions.login.InvalidContactNumberException;
import com.aurionpro.exceptions.login.InvalidPasswordException;
import com.aurionpro.exceptions.login.UsernameNotAvailable;
import com.aurionpro.model.login.User;
import com.aurionpro.services.UserService;

public class AuthenticationFunction {

	public static User authenticateUser(Scanner scanner) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		UserService userService = new UserService();

		System.out.println("=====================================");
		System.out.println("      🍽️  Welcome to AuroFood! 🍽️     ");
		System.out.println("=====================================");
		System.out.println("1️  Register");
		System.out.println("2️  Login");
		System.out.println("=====================================");
		System.out.print("Please select an option (1 or 2): ");

		int choice = scanner.nextInt();
		scanner.nextLine();

		if (choice == 1) {
			System.out.println("\n=====================================");
			System.out.println("   📝 Customer Registration");
			System.out.println("=====================================");
			System.out.print("👤 Enter Full Name      : ");
			String name = scanner.nextLine();
			System.out.print("🏠 Enter Address        : ");
			String address = scanner.nextLine();
			System.out.print("📞 Enter Contact Number : ");
			String contactNumber = scanner.nextLine();
			System.out.print("🆔 Enter Username       : ");
			String user = scanner.nextLine();
			System.out.print("🔒 Enter Password       : ");
			String pass = scanner.nextLine();
			try {
				User currentUser = userService.registerCustomer(user, pass, name, address, contactNumber);
				return currentUser;
				// TODO Auto-generated catch block
			} catch (UsernameNotAvailable | InvalidContactNumberException | InvalidPasswordException exception) {
				System.out.println(exception.getMessage());
				authenticateUser(scanner);
			}
		}
		if (choice != 2) {
			authenticateUser(scanner);
		}

		User loginUser = loginUser(scanner, userService);
		return loginUser;

	}

	public static User loginUser(Scanner scanner, UserService userService) throws ClassNotFoundException, IOException {
		System.out.println("\n=====================================");
		System.out.println("   🔐 Customer Login");
		System.out.println("=====================================");
		System.out.print("🆔 Username : ");
		String user = scanner.nextLine();
		System.out.print("🔒 Password : ");
		String pass = scanner.nextLine();
		try {
			User loggedInUser = userService.login(user, pass);
			System.out.println("\nWelcome back, " + loggedInUser.getRole() + " " + loggedInUser.getName());
			return loggedInUser;
		} catch (CustomerNotFoundException exception) {
			System.out.println(exception.getMessage());
			authenticateUser(scanner);

		}
		return null;
	}
}
