package com.aurionpro.services;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.aurionpro.model.delivery.IDelivery;
import com.aurionpro.model.delivery.Swiggy;
import com.aurionpro.model.delivery.Zomato;

public class DeliveryService {
	private List<IDelivery> deliveryServices = (List<IDelivery>) Arrays.asList(new Zomato(), new Swiggy());
	private List<IDelivery> AvailabledeliveryServices = (List<IDelivery>) Arrays.asList(new Zomato(), new Swiggy());

	public void AssignDeliveryPartner() {
		Random random = new Random();
		int number = random.nextInt(1, AvailabledeliveryServices.size());
		for (int i = 0; i < AvailabledeliveryServices.size(); i++) {
			if (i == number) {
				AvailabledeliveryServices.get(i).assignDevlivery();
			}
		}
	}

	public void displayDeliveryPartner() {
		int i = 1;
		for (IDelivery delivery : deliveryServices) {
			System.out.println(
					i + " " + delivery.getDeliveryPartnerName() + " Status : " + delivery.getAvailabilityStatus());
			i++;
		}
	}

	public void setDeliveryPartner(int id, boolean isActive) {
		for (int i = 0; i < deliveryServices.size(); i++) {
			if (i == id) {
				deliveryServices.get(i).setAvailabililtyStatus(isActive);
				System.out.println(deliveryServices.get(i).getDeliveryPartnerName()+ " "+ deliveryServices.get(i).getAvailabilityStatus());
			}
		}
	}
}
