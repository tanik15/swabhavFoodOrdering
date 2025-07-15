package com.aurionpro.app;

import java.io.IOException;
import java.util.Scanner;

import com.aurionpro.enums.food.FoodType;
import com.aurionpro.model.login.User;
import com.aurionpro.model.menu.Food;
import com.aurionpro.services.DeliveryService;
import com.aurionpro.services.FoodService;
import com.aurionpro.services.UserService;

public class AdminMenuFunctions {
	
	static void displayAdminMenu(Scanner scanner, User currentUser) throws ClassNotFoundException, IOException {
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
				CustomerMenuFunction.displayMenu(scanner);
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
}
