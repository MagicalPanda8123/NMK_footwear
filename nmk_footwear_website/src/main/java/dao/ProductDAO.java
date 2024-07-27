package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Product;

public class ProductDAO implements GenericDAO<Product> {

	private EntityManager manager;
	
	public ProductDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void save(Product object) {
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
	public void update(Product object) {
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
	public void delete(Product object) {
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
	public Product findById(Object id) {
		return manager.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		return manager.createQuery("from Product", Product.class).getResultList();
	}
	
	// Get products with pagination.
	public List<Product> getProducts(int offset, int limit) {
		return manager.createQuery("SELECT p FROM Product p", Product.class)
				.setFirstResult(offset)
				.setMaxResults(limit)
				.getResultList();
	}
	
	// Get the total number of products.
	public int getProductCount() {
		return ((Number) manager.createQuery("SELECT COUNT(p) FROM Product p").getSingleResult()).intValue();
	}

}
