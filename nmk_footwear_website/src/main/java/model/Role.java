package model;

public enum Role {
	ADMIN("admin"), 
	USER("user");

	private final String value;

	Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Role fromString(String value) {
		for (Role userType : Role.values()) {
			if (userType.getValue().equalsIgnoreCase(value)) {
				return userType;
			}
		}
		throw new IllegalArgumentException("Unknown enum type " + value);
	}
}
