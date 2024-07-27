package dao;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import model.Promotion;

public class PromotionDAO implements GenericDAO<Promotion> {

	private EntityManager manager;

	public PromotionDAO(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public void save(Promotion object) {
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
	public void update(Promotion object) {
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
	public void delete(Promotion object) {
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
	public Promotion findById(Object id) {
		return manager.find(Promotion.class, id);
	}

	@Override
	public List<Promotion> findAll() {
		return manager.createQuery("from Promotion", Promotion.class).getResultList();
	}

	public Promotion findByCode(String code) {
		try {
			return manager.createQuery("from Promotion where code = :code", Promotion.class).setParameter("code", code)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean isValidPromotion(Promotion promotion, double totalAmount) {
		LocalDate currentDate = LocalDate.now(); // retrieves the current date

		return promotion != null
				&& (promotion.getStartDate().isEqual(currentDate) || promotion.getStartDate().isBefore(currentDate))
				&& promotion.getEndDate().isAfter(currentDate) && promotion.getQuantity() > 0
				&& promotion.getApplicableTotalAmount() <= totalAmount;
	}

}
