package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import model.User;

public class UserDAO implements GenericDAO<User> {

	EntityManager manager;

	public UserDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(User object) {
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
	public void update(User object) {
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
	public void delete(User object) {
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
	public User findById(Object id) {
		return manager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return manager.createQuery("from User", User.class).getResultList();
	}

	public User getUserByUsername(String username) {

		User user = null;

		try {
			user = manager.createQuery("from User u where u.username = :username", User.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			// in case of no result, return null
			user = null;
		}
		return user;
	}

}
