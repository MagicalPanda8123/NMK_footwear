package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Delivery;

public class DeliveryDAO implements GenericDAO<Delivery> {

	private EntityManager manager;

	public DeliveryDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(Delivery object) {
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
	public void update(Delivery object) {
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
	public void delete(Delivery object) {
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
	public Delivery findById(Object id) {
		return manager.find(Delivery.class, id);
	}

	@Override
	public List<Delivery> findAll() {
		return manager.createQuery("from Delivery", Delivery.class).getResultList();
	}

}
