package com.aurionpro.model.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.aurionpro.enums.food.FoodType;

public abstract class FoodItem implements Serializable {
	public List<Food> items = new ArrayList<>();

	public abstract String getFoodItemName();

	private double discountPercent;

	public Food getFoodItem(int id) {
		for (Food food : items) {
			if (food.getFoodId() == id) {
				return food;
			}
		}
		return null;
	}

	public void addFoodItem(Food item) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("step1");
		Iterator<Food> iterator = items.iterator();
		while (iterator.hasNext()) {
			Food f = iterator.next();
			if (f.getFoodName().equals(item.getFoodName())) {
				System.out.println(item.getFoodName() + " already present. Want to update the existing food ? (y/n)");
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("y")) {
					iterator.remove();
					items.add(item);
					System.out.println("Food Updated!.");
					return;
				}
				System.out.println("Okay");
				return;
			}
		}
		items.add(item);
	};

	public boolean deleteFoodItem(int id) {
		Iterator<Food> iterator = items.iterator();
		while (iterator.hasNext()) {
			Food f = iterator.next();
			if (f.getFoodId() == id) {
				iterator.remove(); // ✅ Safe removal
				System.out.println(f.getFoodName() + " is removed");
				return true;
			}
		}
		return false;
	};

	public void DisplayVegFoodItem() {
		System.out.println("\n==============================================================");
		System.out.println("         🥗 Veg Menu - " + this.getFoodItemName());
		System.out.println("         🎯 Discount: " + this.getDiscountPercent() + "%");
		System.out.println("================================================================");
		System.out.printf("ID    %-25s %-10s %-10s %-6s\n", "🍽️ Item", "💰 Price", "🏷️ Offer", "⭐ Rating");
		System.out.println("---------------------------------------------------------------");
		System.out.println("\t\t" + this.getFoodItemName() + "\tDiscount " + this.getDiscountPercent() + "% \n");
		for (Food food : items) {
			if (food.getFoodtype().equals(FoodType.VEG)) {
				double discountedPrice = food.getFoodPrice() - (food.getFoodPrice() * (0.01 * food.getFoodDiscount()));
				System.out.printf("%-5s %-25s ₹%-9.2f ₹%-9.2f %-6.1f★\n", food.getFoodId(), food.getFoodName(),
						food.getFoodPrice(), discountedPrice, food.getFoodRating());
			}

		}
		System.out.println("===============================================================\n");
	};

	public void DisplayNonVegFoodItem() {
		System.out.println("\n===============================================");
		System.out.println("         Veg + Non-Veg Menu" + this.getFoodItemName());
		System.out.println("===============================================");
		System.out.printf("ID    %-25s %-10s %-10s %-6s\n", "🍽️ Item", "💰 Price", "🏷️ Offer", "⭐ Rating");
		System.out.println("---------------------------------------------------------------");
		for (Food food : items) {
			double discountedPrice = food.getFoodPrice() - (food.getFoodPrice() * (0.01 * food.getFoodDiscount()));
			System.out.printf("%-5s %-25s ₹%-9.2f ₹%-9.2f %-6.1f★\n", food.getFoodId(), food.getFoodName(),
					food.getFoodPrice(), discountedPrice, food.getFoodRating());
		}
		System.out.println("===============================================================\n");
	}

	public Double getDiscountPercent() {
		return discountPercent;
	};

	void setDiscountPercent(double percent) {
		this.discountPercent = percent;
	}

	public FoodItem() {
		super();
	}

	public FoodItem(List<Food> items, double discountPercent) {
		super();
		this.items = items;
		System.out.println(discountPercent);
		this.discountPercent = discountPercent;
	};
}
