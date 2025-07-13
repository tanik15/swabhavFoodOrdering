package com.aurionpro.menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodService {
	public static int newids = 100;
	public static List<FoodItem> menuList = new ArrayList<>();

	public Food getFoodById(int id) throws FoodNotFoundException {
		for (FoodItem item : menuList) {
			if (item.getFoodItem(id) != null) {
				return item.getFoodItem(id);
			}
		}
		throw new FoodNotFoundException();
	}

	public void removeFoodItem(int id) throws IOException {
		for (FoodItem item : menuList) {
			item.deleteFoodItem(id);
		}
		serializeMenu();
	}

	public void addNewFoodItem(Food food, String foodItem) throws IOException {
		menuList.forEach((menu) -> {
			if (menu.getFoodItemName().equals(foodItem)) {
				menu.addFoodItem(food);
			}
		});
		serializeMenu();
		System.out.println(food.getFoodName() + " is added to Starter.");
		return;
	}

	private void deserialize() {
		try {
			FileInputStream fos = new FileInputStream("Menu");
			ObjectInputStream oos = new ObjectInputStream(fos);
			menuList = (List<FoodItem>) oos.readObject();
			fos.close();
			oos.close();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

	public FoodService() {
		deserialize();
	}

	public void displayFoodCategory() {
		deserialize();
		System.out.println("\n===================================================");
		System.out.println("            🍽️  Food Menu Categories");
		System.out.println("===================================================");
		System.out.printf("%-5s %-25s %-10s\n", "ID", "🍴 Food Item", "💸 Discount");
		System.out.println("---------------------------------------------------");
		int id = 1;

		for (FoodItem category : menuList) {
			System.out.printf("%-5d %-25s %-10s\n", id, category.getFoodItemName(),
					category.getDiscountPercent() + "%");
			id++;
		}
		System.out.println("===================================================");
	}

	public void displayAllFoodItem(FoodType foodtype) {
		System.out.printf("\t%-5s %-25s %-10s %-17s   %-6s\n\n", "Id", "Name", "Price", "DiscountedPrice", "Rating");
		if (foodtype.equals(FoodType.VEG)) {
			for (FoodItem food : menuList) {
				food.DisplayVegFoodItem();
			}
			return;
		}
		for (FoodItem food : menuList) {
			food.DisplayNonVegFoodItem();
		}
	}

	public void displayFoodItem(int item, FoodType foodtype) {
		if (item > menuList.size()) {
			displayAllFoodItem(foodtype);
			return;
		}
		FoodItem foodItem = menuList.get(0);
		for (int i = 0; i < menuList.size(); i++) {
			if (item == i) {
				foodItem = menuList.get(i);
				break;
			}
		}
		if (foodtype.equals(FoodType.VEG)) {
			foodItem.DisplayVegFoodItem();
			return;
		}
		foodItem.DisplayNonVegFoodItem();
	}

	public double displayCategoryDiscout(FoodItem foodItem) {
		return foodItem.getDiscountPercent();
	}

	public void serializeMenu() throws IOException {
		FileOutputStream fos = new FileOutputStream("Menu");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(menuList);
		System.out.println("Serialize");
		fos.close();
		oos.close();
	}
}
