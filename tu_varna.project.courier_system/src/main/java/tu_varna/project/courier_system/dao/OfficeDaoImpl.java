package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import tu_varna.project.courier_system.entity.Office;

public class OfficeDaoImpl implements BaseDao<Office> {

	@Override
	public boolean save(Office t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public Office get(int id) {
		return entityManager.getEntityManager().find(Office.class, id);
	}

	@Override
	public void update(Office t) {
		entityManager.executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Office t) {
		entityManager.executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllOffices() {
		return entityManager.getEntityManager().createQuery("SELECT o.id, o.manager, o.firm, o.address FROM Office o")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficesByFirm(int bulstat) {

		return entityManager.getEntityManager()
				.createQuery("SELECT o.id, o.name FROM Office o, Firm f WHERE o.firm = f.id AND f.id=: bulst")
				.setParameter("bulst", bulstat).getResultList();
	}

}
