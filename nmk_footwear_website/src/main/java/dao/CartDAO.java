package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Cart;
import model.CartItem;

public class CartDAO implements GenericDAO<Cart> {

	private EntityManager manager;

	public CartDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(Cart object) {
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(object);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Cart object) {
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
	public void delete(Cart object) {
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(object);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	@Override
	public Cart findById(Object id) {
		return manager.find(Cart.class, id);
	}

	@Override
	public List<Cart> findAll() {
		return manager.createQuery("from Cart", Cart.class).getResultList();
	}

	// find cart by user id, return null if not found.
	public Cart findByUserId(long userId) {
		try {
			return manager.createQuery("from Cart c where c.user.userId = :userId", Cart.class)
					.setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void addCartItem(Cart cart, CartItem cartItem) {
		EntityTransaction transaction = null;

		try {
			transaction = manager.getTransaction();
			transaction.begin();

			manager.merge(cart);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
//
//	public void removeCartItem(Cart cart, CartItem cartItem) {
//		EntityTransaction transaction = null;
//
//		try {
//			transaction = manager.getTransaction();
//			transaction.begin();
//
//			cart.getCartItems().remove(cartItem);
//			cart.calculateTotalPrice();
//			
//			manager.merge(cart);
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null && transaction.isActive()) {
//				transaction.rollback();
//			}
//		}
//	}

}
