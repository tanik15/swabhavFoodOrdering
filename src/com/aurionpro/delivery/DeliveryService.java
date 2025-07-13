package com.aurionpro.delivery;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeliveryService {
	private List<IDelivery> deliveryServices =  (List<IDelivery>) Arrays.asList(new Zomato(),new Swiggy());
	
	
	public void AssignDeliveryPartner(){
		Random random  = new Random();
		int number = random.nextInt(1,2);
		if(number==1) {
			Zomato zomato = new Zomato();
			zomato.assignDevlivery();
			return;
		}
		Swiggy swiggy = new Swiggy();
		swiggy.assignDevlivery();
	}
	
	public void displayDeliveryPartner() {
		for(IDelivery delivery : deliveryServices) {
			System.out.println(delivery.getDeliveryPartnerName());
		}
	}
}
