package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.ProductVariant;

public class ProductVariantDAO implements GenericDAO<ProductVariant> {

	private EntityManager manager;

	public ProductVariantDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(ProductVariant object) {
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
	public void update(ProductVariant object) {
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
	public void delete(ProductVariant object) {
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
	public ProductVariant findById(Object id) {
		return manager.find(ProductVariant.class, id);
	}

	@Override
	public List<ProductVariant> findAll() {
		return manager.createQuery("from ProductVariant", ProductVariant.class).getResultList();
	}

}
