package model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailId id;

	@ManyToOne
	@MapsId("productVariantId")
	@JoinColumn(name = "product_variant_id", insertable = false, updatable = false)
	private ProductVariant productVariant;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private Order order;

	private int quantity;

	private double price;

	private double subtotal;

	public OrderDetail() {
		super();
	}

	public OrderDetail(OrderDetailId id, ProductVariant productVariant, Order order, int quantity, double price,
			double subtotal) {
		super();
		this.id = id;
		this.productVariant = productVariant;
		this.order = order;
		this.quantity = quantity;
		this.price = price;
		this.subtotal = subtotal;
	}

	public OrderDetailId getId() {
		return id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
