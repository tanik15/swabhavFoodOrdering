package com.aurionpro.model.payments;

import java.util.Arrays;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.exceptions.payments.NoPaymentOptionFoundException;

public class PaymentService {
	private List<IPayment> paymentservices = (List<IPayment>) Arrays.asList(new CreditCard(), new DebitCard(),
			new Upi());

	public void displayPaymentServices() {
		int i = 1;
		for (IPayment payment : paymentservices) {
			System.out.printf("%d️⃣  %s\n", i, payment.getName());
			i++;
		}
		System.out.println("=======================================\n");
	}

	public static boolean ValidateCard(Scanner scanner, String cardNo, String validThru, String cvv) {
		System.out.println("\n===================================================");
		System.out.println("      💳 Card Payment");
		System.out.println("===================================================");
		System.out.print("👉 Enter 16-digit Card Number:  ");
		cardNo = scanner.nextLine();
		while (!cardNo.matches("\\d{16}")) {
	        System.out.println("❗ Card Number must be exactly 16 digits (numbers only).");
	        System.out.print("👉 Re-enter Card Number: ");
	        cardNo = scanner.nextLine();
	    }
		System.out.print("🔒 Enter 3-digit CVV: ");
		cvv = scanner.nextLine();
		 while (!cvv.matches("\\d{3}")) {
		        System.out.println("❗ Invalid CVV. Must be 3 digits.");
		        System.out.print("🔒 Re-enter CVV: ");
		        cvv = scanner.nextLine();
		    }
		String regex = "^(0[1-9]|1[0-2])/([2-9][5-9]|[3-9][0-9])$";
		 System.out.print("📅 Enter Valid Thru (MM/YY): ");
		validThru = scanner.nextLine();
		  while (!validThru.matches(regex)) {
		        System.out.println("❗ Invalid Valid Thru. Format should be MM/YY and year ≥ 25.");
		        System.out.print("📅 Re-enter Valid Thru: ");
		        validThru = scanner.nextLine();
		    }
			System.out.println("\n===================================================");
//		 System.out.println("\n✅ Card details validated successfully!");
		return true;
	}

	public static void creditCard(Scanner scanner, double amount) {
		String cardNo = "", validThru = "", cvv = "";
		ValidateCard(scanner, cardNo, validThru, cvv);

		IPayment creditCard = new CreditCard();
		creditCard.payAmount(amount);
	}

	private static void upiPayment(Scanner scanner, double amount) {
		// TODO Auto-generated method stub
		boolean flag = true;
		System.out.println();
		while (flag) {
			System.out.println("👉 Enter UPI ID/UPI Number");
			String input = scanner.nextLine();

			final String UPI_REGEX = "^[0-9A-Za-z.-]{2,256}@[A-Za-z]{2,64}$";

			if ((input.contains("@") && input.matches(UPI_REGEX))
					|| (input.length() == 10 && input.matches("^\\d{10}$"))) {
				System.out.println("Enter 6 digit Password");
				String password = scanner.nextLine();
				while (password.length() != 6) {
					System.out.println("Incorrect password\nEnter correct password");
					password = scanner.nextLine();
				}
				IPayment upi = new Upi();
				flag = false;
				upi.payAmount(amount);
				return;
			}
		}
	}

	public static void debitCard(Scanner scanner, double amount) {
		System.out.println("Enter Debit Card Number");
		String cardNo = "", validThru = "", cvv = "";
		ValidateCard(scanner, cardNo, validThru, cvv);

		IPayment debitCard = new DebitCard();
		debitCard.payAmount(amount);
	}

	public void doPayment(int id, double amount, Scanner scanner) throws NoPaymentOptionFoundException {
		if (id == 1) {
			creditCard(scanner, amount);
			return;
		}
		if (id == 2) {
			debitCard(scanner, amount);
			return;
		}
		if (id == 3) {
			upiPayment(scanner, amount);
			return;
		}
		throw new NoPaymentOptionFoundException();
	}

}
