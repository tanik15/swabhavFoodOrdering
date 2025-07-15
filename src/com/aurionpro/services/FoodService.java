package com.aurionpro.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.enums.food.FoodType;
import com.aurionpro.exceptions.menu.FoodNotFoundException;
import com.aurionpro.model.menu.Food;
import com.aurionpro.model.menu.FoodItem;

public class FoodService {
	public static int newids = 100;
	private  List<FoodItem> menuList = new ArrayList<>();

	public Food getFoodById(int id) throws FoodNotFoundException {
		for (FoodItem item : menuList) {
			if (item.getFoodItem(id) != null) {
				return item.getFoodItem(id);
			}
		}
		throw new FoodNotFoundException();
	}

	public void removeFoodItem(int id) throws IOException {
		boolean isChange = false;
		for (FoodItem item : menuList) {
			isChange = item.deleteFoodItem(id);
			if(isChange==true) {
				serializeMenu();         
				break;
			}
		}
		if(isChange==false) {
			System.out.println("No Food Exist with id : " + id);
		}
	}

	public void addNewFoodItem(Food food, String foodItem) throws IOException {
		menuList.forEach((menu) -> {
			if (menu.getFoodItemName().equals(foodItem)) {
				menu.addFoodItem(food);
				
			}
		});
		serializeMenu();
	}

	private void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("Menu");
			ObjectInputStream ois = new ObjectInputStream(fis);
			menuList = (List<FoodItem>) ois.readObject();
			fis.close();
			ois.close();
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
//		System.out.println("Serialize");
		fos.close();
		oos.close();
	}
}
