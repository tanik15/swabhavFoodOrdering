package com.aurionpro.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.aurionpro.enums.food.FoodType;
import com.aurionpro.exceptions.login.CustomerNotFoundException;
import com.aurionpro.exceptions.login.InvalidContactNumberException;
import com.aurionpro.exceptions.login.InvalidPasswordException;
import com.aurionpro.exceptions.login.UsernameNotAvailable;
import com.aurionpro.exceptions.menu.FoodNotFoundException;
import com.aurionpro.exceptions.payments.NoPaymentOptionFoundException;
import com.aurionpro.model.login.User;
import com.aurionpro.model.menu.Food;
import com.aurionpro.model.order.Order;
import com.aurionpro.model.order.OrderItem;
import com.aurionpro.model.payments.PaymentService;
import com.aurionpro.services.DeliveryService;
import com.aurionpro.services.FoodService;
import com.aurionpro.services.UserService;

public class MyApp {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		User currentuser = authenticateUser(scanner);
		if (currentuser.getUsername() == "admin") {
			displayAdminMenu(scanner, currentuser);
			return;
		}
		createOrderList(scanner);

	}

	private static void displayAdminMenu(Scanner scanner, User currentUser) throws ClassNotFoundException, IOException {
		int choice = 0;
		while (choice != 8) {
			adminService();
			choice = scanner.nextInt();
			scanner.nextLine();
			UserService userService = new UserService();
			FoodService foodService = new FoodService();
			if (choice == 1) {
				userService.displayOnlyCustomer(currentUser);
				continue;
			}
			if (choice == 2) {
				userService.displayCustomer(currentUser);
				continue;
			}
			if (choice == 3) {
				displayMenu(scanner);
				continue;
			}
			if (choice == 4) {
				addNewFoodItem(scanner, foodService);
				continue;
			}
			if (choice == 5) {
				System.out.println("Enter Food Id");
				int id = scanner.nextInt();
				foodService.removeFoodItem(id);
				continue;
			}
			if (choice == 6) {
				DeliveryService deliveryService = new DeliveryService();
				deliveryService.displayDeliveryPartner();
				continue;
			}
			if (choice == 7) {
				DeliveryService deliveryService = new DeliveryService();
				deliveryService.displayDeliveryPartner();
				System.out.println("Select the Service: ");
				int serviceSelected = scanner.nextInt();
				scanner.nextLine();
				System.out.println("1.Active \n2.InActive");
				int status = scanner.nextInt();
				serviceSelected--;
				if (status == 1) {
					deliveryService.setDeliveryPartner(serviceSelected, true);
				}
				deliveryService.setDeliveryPartner(serviceSelected, false);
				continue;
			}
			if (choice == 8) {
				System.out.println("Thankyou!");
			}

		}
	}

	private static void adminService() {
		System.out.println("\n==========================================");
		System.out.println("        🛠️  Admin Service Menu");
		System.out.println("==========================================");
		System.out.println("1️⃣  Display only Users");
		System.out.println("2️⃣  Display Users with Orders");
		System.out.println("3️⃣  Display Food Menu");
		System.out.println("4️⃣  ➕ Add New Food Item");
		System.out.println("5️⃣  ❌ Remove Existing Food Item");
		System.out.println("6️⃣  🚚 Display Delivery Partners");
		System.out.println("7️⃣  🚚 Modify Delivery Partners");
		System.out.println("8   🚪 Exit");
		System.out.println("==========================================");
		System.out.print("👉 Please choose a service (1–7): ");
	}

	private static void addNewFoodItem(Scanner scanner, FoodService foodService) {
		System.out.println("\n===========================================");
		System.out.println("        ➕ Add a New Food Item");
		System.out.println("===========================================");
		System.out.println("🍽️  Choose Category:");
		System.out.println("1️⃣  Starter");
		System.out.println("2️⃣  Main Course");
		System.out.println("3️⃣  Bread");
		System.out.println("4️⃣  Dessert");
		System.out.println("5️⃣  Beverages");
		System.out.print("👉 Enter your choice (1–5): ");
		int categoryChoice = scanner.nextInt();
		scanner.nextLine(); // consume newline

		System.out.print("📝 Enter Food Name: ");
		String foodName = scanner.nextLine();

		System.out.print("💰 Enter Food Price: ₹");
		double foodPrice = scanner.nextDouble();

		System.out.print("🏷️  Enter Discount (%): ");
		double foodDiscount = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("⭐ Enter Rating (out of 5): ");
		double foodRating = scanner.nextDouble();
		scanner.nextLine(); // consume leftover newline

		System.out.print("🥦 Enter Ingredients (comma-separated): ");
		String foodIngredients = scanner.nextLine();

		System.out.println("🥗 Choose Food Type:");
		System.out.println("1️⃣  Veg");
		System.out.println("2️⃣  Non-Veg");
		System.out.print("👉 Enter your choice (1 or 2): ");
		int foodTypeInput = scanner.nextInt();

		FoodType foodType = FoodType.VEG;
		if (foodTypeInput == 1) {
			foodType = FoodType.VEG;
		}
		if (foodTypeInput == 2) {
			foodType = FoodType.NONVEG;
		}
		int foodId = FoodService.newids;
		FoodService.newids++;
		Food food = new Food(foodId, foodDiscount, foodName, foodPrice, foodRating, foodIngredients, foodType);
		String category = (categoryChoice == 1) ? "Starter"
				: (categoryChoice == 2) ? "MainCourse"
						: (categoryChoice == 3) ? "Bread" : (categoryChoice == 4) ? "Dessert" : "Beverages";
		try {
			foodService.addNewFoodItem(food, category);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static void processPayment(Scanner scanner, Order order, double totalAmount) {
		PaymentService paymentService = new PaymentService();
		System.out.println("\n=======================================");
		System.out.println("         💳 Choose Payment Method");
		System.out.println("=======================================");
		paymentService.displayPaymentServices();
		System.out.print("👉 Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		try {
			paymentService.doPayment(choice, totalAmount, scanner);
			try {
				System.out.println("\n🔍 Finding the best delivery partner for your order...");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.getMessage();
			}
			DeliveryService deliveryService = new DeliveryService();
			deliveryService.AssignDeliveryPartner();
			System.out.println("🕒 Your order will be delivered in approximately 30 minutes.");
			System.out.println("🙏 Thank you for ordering with AuroFood! Enjoy your meal! 🍽️");
		} catch (NoPaymentOptionFoundException e) {
			System.out.println(e.getMessage());
			processPayment(scanner, order, totalAmount);
		}
	}

	private static void createOrderList(Scanner scanner) {
		int id = 0;
		Order order = new Order();
		while (id != -1) {
			FoodService service = displayMenu(scanner);
			System.out.println("📌 Enter the item number to add it to your order.");
			System.out.print("👉 Your Choice: ");
			id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("🔢 Enter Quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();
			try {
				Food food = service.getFoodById(id);
				OrderItem orderItem = new OrderItem(food, quantity);
				order.addItem(orderItem);
				System.out.println("\n============================================");
				System.out.println("\nWant to add more items or proceed to bill ?");
				System.out.println("	1️  Add more item");
				System.out.println("	2️  Proceed to bill\n===============================================");
				System.out.print("👉 Your Choice: ");
				int addItemChoice = scanner.nextInt();
				scanner.nextLine();
				if (addItemChoice == 1) {
					continue;
				}
				break;
			} catch (FoodNotFoundException exception) {
				System.out.println(exception.getMessage());
			}
		}
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formatted = now.format(formatter);
		order.dateTime = formatted;
		Order orderinstance = order.displayOrderList();
		double totalamount = orderinstance.grandTotal;
		processPayment(scanner, order, totalamount);
		UserService.updateCustomerOrder(orderinstance);

	}

	private static FoodService displayMenu(Scanner scanner) {
		// TODO Auto-generated method stub
		System.out.println("\n=====================================");
		System.out.println("   🍽️  Choose Food Preference");
		System.out.println("=====================================");
		System.out.println("1️⃣  Veg");
		System.out.println("2️⃣  Non-Veg");
		System.out.print("👉 Enter your choice (1 or 2): ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		FoodService service = new FoodService();
		service.displayFoodCategory();
		System.out.print("👉 Please select a category: ");
		int categoryChoice = scanner.nextInt();
		categoryChoice--;
		if (choice == 1) {
			service.displayFoodItem(categoryChoice, FoodType.VEG);
			return service;
		}
		service.displayFoodItem(categoryChoice, FoodType.NONVEG);
		return service;
	}

	private static User authenticateUser(Scanner scanner) throws ClassNotFoundException, IOException {
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

	private static User loginUser(Scanner scanner, UserService userService) throws ClassNotFoundException, IOException {
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
