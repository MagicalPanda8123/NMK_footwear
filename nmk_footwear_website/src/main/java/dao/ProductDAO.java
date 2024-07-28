package dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Product;
import utility.JPAUtil;

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
		return manager.createQuery("SELECT p FROM Product p", Product.class).setFirstResult(offset).setMaxResults(limit)
				.getResultList();
	}

	// Get the total number of products.
	public int getProductCount() {
		return ((Number) manager.createQuery("SELECT COUNT(p) FROM Product p").getSingleResult()).intValue();
	}

	// Get products by category.
	public List<Product> getProductsByCategory(String categoryName) {
		return manager.createQuery("SELECT p FROM Product p WHERE p.category.name = :categoryName", Product.class)
				.setParameter("categoryName", categoryName).getResultList();
	}

	// Get featured products.
	public List<Product> getFeaturedProducts() {
		return manager.createQuery("SELECT p FROM Product p WHERE p.isFeatured = true", Product.class).getResultList();
	}

	public List<Product> getFilteredProducts(Map<String, String> filters, int offset, int limit) {
		StringBuilder queryString = new StringBuilder("SELECT p FROM Product p WHERE 1=1");

		if (filters.containsKey("type") && !filters.get("type").equals("All")) {
			queryString.append(" AND p.category.name = :categoryName");
		}
		if (filters.containsKey("name") && filters.get("name") != null) {
			queryString.append(" AND p.name LIKE :name");
		}
		if (filters.containsKey("brand") && !filters.get("brand").equals("All")) {
			queryString.append(" AND p.brand = :brand");
		}
		if (filters.containsKey("price")) {
			if (filters.get("price").equals("asc")) {
				queryString.append(" ORDER BY p.basePrice ASC");
			} else {
				queryString.append(" ORDER BY p.basePrice DESC");
			}
		}

		// Create query
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		TypedQuery<Product> query = manager.createQuery(queryString.toString(), Product.class);

		if (filters.containsKey("type") && !filters.get("type").equals("All")) {
			query.setParameter("categoryName", filters.get("type"));
		}
		if (filters.containsKey("name")) {
			query.setParameter("name", "%" + filters.get("name") + "%");
		}
		if (filters.containsKey("brand") && !filters.get("brand").equals("All")) {
			query.setParameter("brand", filters.get("brand"));
		}

		return query.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

}
