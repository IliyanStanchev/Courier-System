package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import tu_varna.project.courier_system.entity.Notification;

public class NotificationDaoImpl implements BaseDao<Notification> {

	@Override
	public Notification get(int id) {
		return entityManager.getEntityManager().find(Notification.class, id);
	}

	@Override
	public boolean save(Notification t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public void update(Notification t) {
		entityManager.executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Notification t) {
		entityManager.executeInsideTransaction(
				entityManager -> entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t)));

	}

	@SuppressWarnings("unchecked")
	public List<Notification> getUserNotifications(int id) {
		return entityManager.getEntityManager()
				.createQuery("SELECT n FROM Notification n, User u WHERE n.user=u.id AND u.id =: id AND n.is_seen=0")
				.setParameter("id", id).getResultList();
	}

}
