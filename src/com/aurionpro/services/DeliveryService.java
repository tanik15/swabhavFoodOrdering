package com.aurionpro.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.aurionpro.model.delivery.IDelivery;
import com.aurionpro.model.delivery.Swiggy;
import com.aurionpro.model.delivery.Zomato;

public class DeliveryService {
	private List<IDelivery> deliveryServices = (List<IDelivery>) Arrays.asList(new Zomato(), new Swiggy());

	public void AssignDeliveryPartner() {
		Random random = new Random();
		int number = random.nextInt(1, 2);
		if (number == 1) {
			Zomato zomato = new Zomato();
			zomato.assignDevlivery();
			return;
		}
		Swiggy swiggy = new Swiggy();
		swiggy.assignDevlivery();
	}

	public void displayDeliveryPartner() {
		int i = 1;
		for (IDelivery delivery : deliveryServices) {
			System.out.println(i + " " + delivery.getDeliveryPartnerName());
		}			
	}
}
