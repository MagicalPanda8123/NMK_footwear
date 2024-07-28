package model;

import java.io.Serializable;
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
@Table(name = "products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;

	private String brand;

	private String name;

	@Column(name = "is_featured")
	private boolean isFeatured;

	private String description;

	@Column(name = "base_price")
	private double basePrice;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<ProductVariant> variants;

//	@ManyToMany(mappedBy = "products")
//	private Set<Category> categories;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Product() {
		super();
	}

	public Product(long productId, String brand, String name, boolean isFeatured, String description, double basePrice,
			Set<ProductVariant> variants, Category category) {
		super();
		this.productId = productId;
		this.brand = brand;
		this.name = name;
		this.isFeatured = isFeatured;
		this.description = description;
		this.basePrice = basePrice;
		this.variants = variants;
		this.category = category;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public Set<ProductVariant> getVariants() {
		return variants;
	}

	public void setVariants(Set<ProductVariant> variants) {
		this.variants = variants;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

}
