package model;

public enum PaymentMethod {
	CREDIT_CARD("credit card"), 
	BANK_TRANSFER("bank transfer"), 
	CASH_ON_DELIVERY("cash on delivery");

	private final String displayName;

	PaymentMethod(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
