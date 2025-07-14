package com.aurionpro.model.delivery;

public interface IDelivery {
	void setAvailabililtyStatus(boolean isActive);

	String getAvailabilityStatus();

	void assignDevlivery();

	String getDeliveryPartnerName();
}
