package model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long cartId;
	private long productVariantId;

	public CartItemId(long cartId, long productVarianId) {
		super();
		this.cartId = cartId;
		this.productVariantId = productVarianId;
	}

	public CartItemId() {
		super();
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(long productVarianId) {
		this.productVariantId = productVarianId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CartItemId that = (CartItemId) o;
		return Objects.equals(cartId, that.cartId) && Objects.equals(productVariantId, that.productVariantId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, productVariantId);
	}
}
