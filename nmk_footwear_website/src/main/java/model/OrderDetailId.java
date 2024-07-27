package model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long orderId;
	private long productVariantId;

	public OrderDetailId() {
		super();
	}

	public OrderDetailId(long orderId, long productVariantId) {
		super();
		this.orderId = orderId;
		this.productVariantId = productVariantId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(long productVariantId) {
		this.productVariantId = productVariantId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productVariantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof OrderDetailId))
			return false;
		OrderDetailId other = (OrderDetailId) obj;
		return orderId == other.orderId && productVariantId == other.productVariantId;
	}

}
