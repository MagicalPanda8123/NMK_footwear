package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_variants")
public class ProductVariant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_variant_id")
	private long productVariantId;

	private String size;

	private String color;

	private double price;

	private String imageURL;

	@Column(name = "stock_quantity")
	private int stockQuantity;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	// bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
	private Set<CartItem> cartItems;

	// bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails;

	public ProductVariant() {
		super();
	}

	public ProductVariant(long productVariantId, String size, String color, double price, String imageURL,
			int stockQuantity, Product product, Set<CartItem> cartItems, Set<OrderDetail> orderDetails) {
		super();
		this.productVariantId = productVariantId;
		this.size = size;
		this.color = color;
		this.price = price;
		this.imageURL = imageURL;
		this.stockQuantity = stockQuantity;
		this.product = product;
		this.cartItems = cartItems;
		this.orderDetails = orderDetails;
	}

	public long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(long productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ProductVariant))
			return false;
		ProductVariant other = (ProductVariant) obj;
		return productVariantId != 0 && productVariantId == other.getProductVariantId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(productVariantId);
	}

}
