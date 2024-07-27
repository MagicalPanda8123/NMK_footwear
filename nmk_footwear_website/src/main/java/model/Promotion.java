package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "promotions")
public class Promotion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	private long promotionId;

	private String code;

	@Column(name = "discount_percentage")
	private double discountPercentage;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "applicable_total_amount")
	private double applicableTotalAmount;

	private int quantity;

	@OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
	private List<Order> orders;

	public Promotion() {
		super();
	}

	public Promotion(long promotionId, String code, double discountPercentage, LocalDate startDate, LocalDate endDate,
			double applicableTotalAmount, int quantity, List<Order> orders) {
		super();
		this.promotionId = promotionId;
		this.code = code;
		this.discountPercentage = discountPercentage;
		this.startDate = startDate;
		this.endDate = endDate;
		this.applicableTotalAmount = applicableTotalAmount;
		this.quantity = quantity;
		this.orders = orders;
	}

	public long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(long promotionId) {
		this.promotionId = promotionId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getApplicableTotalAmount() {
		return applicableTotalAmount;
	}

	public void setApplicableTotalAmount(double applicableTotalAmount) {
		this.applicableTotalAmount = applicableTotalAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
