package model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CartItemId id;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id", insertable = false, updatable = false)
	private Cart cart;

	@ManyToOne
	@MapsId("productVariantId")
	@JoinColumn(name = "product_variant_id", insertable = false, updatable = false)
	private ProductVariant productVariant;

	private int quantity;

	private double price;

	private double subtotal;

	public CartItem() {
		super();
	}

	public CartItem(CartItemId id, Cart cart, ProductVariant productVariant, int quantity, double price,
			double subtotal) {
		super();
		this.id = id;
		this.cart = cart;
		this.productVariant = productVariant;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}

	public CartItemId getId() {
		return id;
	}

	public void setId(CartItemId id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
