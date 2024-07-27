package model;

public enum DeliveryStatus {
	PENDING("pending"),
	DELIVERING("delivering"),
	DELIVERED("delivered"),
	RETURNED("returned"),
	CANCELLED("cancelled");
	
	private final String value;
	
	DeliveryStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static DeliveryStatus fromString(String value) {
		for (DeliveryStatus status : DeliveryStatus.values()) {
			if (status.getValue().equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown enum type " + value);
	}
}
