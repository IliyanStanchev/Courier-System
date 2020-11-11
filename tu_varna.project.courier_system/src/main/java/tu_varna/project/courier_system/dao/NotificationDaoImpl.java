package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import tu_varna.project.courier_system.entity.Notification;

public class NotificationDaoImpl extends entityManager implements BaseDao<Notification> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getAll() {
		Query query = getEntityManager().createQuery("SELECT e FROM Notification e");
		return query.getResultList();
	}

	@Override
	public boolean save(Notification t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public void update(Notification t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Notification t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	@Override
	public Notification get(int id) {
		return getEntityManager().find(Notification.class, id);
	}

}
