package tu_varna.project.courier_system.dao;

import java.util.List;
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
	public void save(Office t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
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

		

}
