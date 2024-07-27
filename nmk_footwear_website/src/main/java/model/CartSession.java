package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CartSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<CartItemSession> cartItems;
	private double totalPrice;

	public CartSession() {
		this.cartItems = new HashSet<>();
	}

	public Set<CartItemSession> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItemSession> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void updateItemQuantity(ProductVariant productVariant, int quantity) {
		CartItemSession item = cartItems.stream().filter(i -> i.getProductVariant().equals(productVariant)).findFirst()
				.orElse(null);
		if (item != null) {
			item.setQuantity(quantity);
			item.calculateSubtotal();
		} else {
			// Add new item if it doesn't exist
			cartItems.add(new CartItemSession(productVariant, quantity, productVariant.getPrice()));
		}
		calculateTotalPrice();
	}
	
	public void addItem(CartItemSession cartItemSession) {
		cartItems.add(cartItemSession);
		calculateTotalPrice();
	}

	public void removeItem(CartItemSession cartItemSession) {
		cartItems.remove(cartItemSession);
		calculateTotalPrice();
	}

	public void calculateTotalPrice() {
		this.totalPrice = cartItems.stream().mapToDouble(CartItemSession::getSubtotal).sum();
	}

	public CartItemSession findByProductVariantId(long id) {
		return cartItems.stream()
						.filter(i -> i.getProductVariant()
						.getProductVariantId() == id)
						.findFirst()
						.orElse(null);
	}
}
