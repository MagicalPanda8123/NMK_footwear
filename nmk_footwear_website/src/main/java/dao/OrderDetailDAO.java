package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.OrderDetail;

public class OrderDetailDAO implements GenericDAO<OrderDetail> {

	private EntityManager manager;

	public OrderDetailDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(OrderDetail object) {
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
	public void update(OrderDetail object) {
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
	public void delete(OrderDetail object) {
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
	public OrderDetail findById(Object id) {
		return manager.find(OrderDetail.class, id);
	}

	@Override
	public List<OrderDetail> findAll() {
		return manager.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class).getResultList();
	}

}
