package com.aurionpro.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.aurionpro.enums.food.FoodType;
import com.aurionpro.exceptions.menu.FoodNotFoundException;
import com.aurionpro.exceptions.payments.NoPaymentOptionFoundException;
import com.aurionpro.model.menu.Food;
import com.aurionpro.model.order.Order;
import com.aurionpro.model.order.OrderItem;
import com.aurionpro.services.DeliveryService;
import com.aurionpro.services.FoodService;
import com.aurionpro.services.PaymentService;
import com.aurionpro.services.UserService;
import com.aurionpro.utils.FileUtil;

public class CustomerMenuFunction {
	public static void processPayment(Scanner scanner, Order order, double totalAmount) {
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
			System.out.println("\n🙏 Thank you for ordering with AuroFood! Enjoy your meal! 🍽️");
		} catch (NoPaymentOptionFoundException e) {
			System.out.println(e.getMessage());
			processPayment(scanner, order, totalAmount);
		}
	}

	public static void createOrderList(Scanner scanner) {
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
		MyApp.latestOrderId++;
		order.orderId =  MyApp.latestOrderId;
		FileUtil.writeLastOrderId(MyApp.latestOrderId);
		Order orderinstance = order.displayOrderList();
		double totalamount = orderinstance.grandTotal;
		processPayment(scanner, order, totalamount);
		UserService.updateCustomerOrder(orderinstance);

	}

	public static FoodService displayMenu(Scanner scanner) {
		// TODO Auto-generated method stub
		if (MyApp.foodPreferencechoice == 0) {
			System.out.println("\n=====================================");
			System.out.println("   🍽️  Choose Food Preference");
			System.out.println("=====================================");
			System.out.println("1️⃣  Veg");
			System.out.println("2️⃣  Non-Veg");
			System.out.print("👉 Enter your choice (1 or 2): ");
			MyApp.foodPreferencechoice = scanner.nextInt();
			scanner.nextLine();
		}
		FoodService service = new FoodService();
		service.displayFoodCategory();
		System.out.print("👉 Please select a category: ");
		int categoryChoice = scanner.nextInt();
		categoryChoice--;
		if (MyApp.foodPreferencechoice == 1) {
			service.displayFoodItem(categoryChoice, FoodType.VEG);
			return service;
		}
		service.displayFoodItem(categoryChoice, FoodType.NONVEG);
		return service;
	}
}
