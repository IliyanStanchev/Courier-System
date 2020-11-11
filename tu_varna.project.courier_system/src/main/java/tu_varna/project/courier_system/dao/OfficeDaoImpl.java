package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import tu_varna.project.courier_system.entity.Office;

public class OfficeDaoImpl extends entityManager implements BaseDao<Office> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Office> getAll() {
		Query query = getEntityManager().createQuery("SELECT e FROM Office e");
		return query.getResultList();
	}

	@Override
	public boolean save(Office t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public void update(Office t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Office t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	@Override
	public Office get(int id) {
		return getEntityManager().find(Office.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<String> getOfficesByFirm(int bulstat) {

		return getEntityManager()
				.createQuery("SELECT o.name FROM Office o, Firm f WHERE o.firm = f.id AND f.id=: bulst")
				.setParameter("bulst", bulstat).getResultList();
	}

	public int getIdByOfficeName(String name) {

		return (int) getEntityManager().createQuery("SELECT o.id FROM Office o WHERE o.name=: name")
				.setParameter("name", name).getSingleResult();

	}

	public Office getOfficesByName(String name) {
		return (Office) getEntityManager().createQuery("FROM Office o WHERE o.name=: name").setParameter("name", name)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllOffices() {
		return getEntityManager().createQuery("SELECT o.id, o.manager, o.firm, o.address FROM Office o")
				.getResultList();

	}
}
