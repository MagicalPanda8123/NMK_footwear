package model;

import java.io.Serializable;

public class CartItemSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProductVariant productVariant;
	private int quantity;
	private double price;
	private double subtotal;

	public CartItemSession(ProductVariant productVariant, int quantity, double price) {
		this.productVariant = productVariant;
		this.quantity = quantity;
		this.price = price;
		this.calculateSubtotal();
	}

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubtotal() {
		calculateSubtotal();
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void calculateSubtotal() {
		this.subtotal = this.quantity * this.price;
	}
}
