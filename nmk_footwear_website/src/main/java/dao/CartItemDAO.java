package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.CartItem;
import model.CartItemId;

public class CartItemDAO implements GenericDAO<CartItem> {

	private EntityManager manager;

	public CartItemDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(CartItem object) {
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(object);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(CartItem object) {
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(object);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void delete(CartItem object) {
		EntityTransaction transaction = null;
		System.out.println("CartItem ENTER DELETE");

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(object);
			transaction.commit();
			System.out.println("CartItem deleted.");
		} catch (Exception e) {

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public CartItem findById(Object id) {
		CartItemId cartItemId = (CartItemId) id;

		try {
			return manager.createQuery(
					"from CartItem where cart.cartId = :cartId and productVariant.productVariantId = :productVariantId",
					CartItem.class).setParameter("cartId", cartItemId.getCartId())
					.setParameter("productVariantId", cartItemId.getProductVariantId()).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<CartItem> findAll() {
		return manager.createQuery("from CartItem", CartItem.class).getResultList();
	}

}
