package tu_varna.project.courier_system.dao;

import java.util.List;
import javax.persistence.Query;
import tu_varna.project.courier_system.entity.CourierFirm;


public class CourierFirmDaoImpl extends entityManager implements BaseDao<CourierFirm>{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourierFirm> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM CourierFirm e");
        return query.getResultList();
	}

	@Override
	public void save(CourierFirm t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
	}

	@Override
	public void update(CourierFirm t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
		
	}

	@Override
	public void delete(CourierFirm t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
		
	}

	@Override
	public CourierFirm get(int id) {
		return getEntityManager().find(CourierFirm.class, id);
	}


}
