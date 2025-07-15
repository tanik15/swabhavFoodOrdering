package com.aurionpro.model.order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
	List<OrderItem> orderList = new ArrayList<>();
	public double grandTotal = 0;
	public String dateTime="";
	public int orderId;

	public void addItem(OrderItem order) {
		for (OrderItem orderr : orderList) {
			if (orderr.getFoodItem().getFoodId() == order.getFoodItem().getFoodId()) {
				orderr.quantity = order.getQuantity();
				System.out.println("\n✅ " + order.quantity + " x " + order.foodItem.getFoodName() + " is updated.");
				return;
			}
		}

		orderList.add(order);
		System.out.println("\n✅ " + order.quantity + " x " + order.foodItem.getFoodName() + " added to your order 🛒");

	}

	public Order displayOrderList() {
		System.out.println("\n\n===============================================================================");
		System.out.println("OrderId:"+orderId+"\t   AuroFoodService - Final Bill\t\t" + dateTime);
		System.out.println("===============================================================================");
		System.out.printf("%-5s %-25s %-10s %-15s %-10s %-10s\n", "ID", "Name", "Price(₹)", "Discounted(₹)", "Qty",
				"Total(₹)");
		System.out.println("-------------------------------------------------------------------------------");
		grandTotal = 0;
		for (OrderItem orderItem : orderList) {
			System.out.printf("%-5s %-25s ₹%-9.2f ₹%-14.2f %-10d ₹%-9.2f\n", orderItem.foodItem.getFoodId(),
					orderItem.foodItem.getFoodName(), orderItem.foodItem.getFoodPrice(), orderItem.discountAmount,
					orderItem.quantity, orderItem.discountAmount * orderItem.quantity);
			grandTotal += orderItem.discountAmount * orderItem.quantity;
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.printf("%-52s ₹%-9.2f\n", "Total Amount:", grandTotal);
		System.out.println("================================================================================");
		return this;
	}

}
