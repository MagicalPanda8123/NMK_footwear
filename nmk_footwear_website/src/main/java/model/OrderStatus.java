package model;

public enum OrderStatus {
    PENDING("pending"),
    CONFIRMED("confirmed"),
    DELIVERY_PROCESSING("delivery processing"),
    CANCELLED("cancelled"),
    DONE("done");

    private final String displayName;

    OrderStatus(String displayName) {
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

