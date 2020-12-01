package tu_varna.project.courier_system.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import tu_varna.project.courier_system.dao.UserDao;
import tu_varna.project.courier_system.dao.em.entityManager;
import tu_varna.project.courier_system.entity.User;

public class UserDaoImpl implements UserDao  {

	@Override
	public boolean save(User t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}


	@Override
	public User get(int id) {
		User user;

		try {
			user = entityManager.getEntityManager().find(User.class, id);
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}


	@Override
	public void update(User t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.merge(t));
		} catch (PersistenceException e) {
			System.out.println("Error updating object!");
		}
	}

	
	@Override
	public void delete(User t) {

		try {
			entityManager.executeInsideTransaction(
					entityManager -> entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t)));
		} catch (PersistenceException e) {
			System.out.println("Error deleting object!");
		}

	}

	@Override
	public User getUserByPhone(String phoneNmb) {
		User user;
		try {
			user = (User) entityManager.getEntityManager().createQuery("FROM User WHERE phoneNumber=: phoneNmb")
					.setParameter("phoneNmb", phoneNmb).getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;

	}

	@Override
	public User getUserByUsername(String name) {
		User user;
		try {
			user = (User) entityManager.getEntityManager().createQuery("FROM User WHERE loginUsername=: name")
					.setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllClients() {
		return entityManager.getEntityManager()
				.createQuery("SELECT u.name, u.phoneNumber FROM User u, Client c WHERE u.id = c.id").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCouriers() {

		return entityManager.getEntityManager().createQuery(
				"SELECT u.name, u.phoneNumber, f.companyName FROM User u, Courier c, Firm f WHERE u.id = c.id AND c.firm = f.id")
				.getResultList();

	}

}
