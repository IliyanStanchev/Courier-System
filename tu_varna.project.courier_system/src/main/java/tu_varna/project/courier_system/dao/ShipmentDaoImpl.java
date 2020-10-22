package tu_varna.project.courier_system.dao;

import java.util.List;
import javax.persistence.Query;
import tu_varna.project.courier_system.entity.Shipment;

public class ShipmentDaoImpl extends entityManager implements BaseDao<Shipment> {
 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Shipment> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM Shipment e");
        return query.getResultList();
	}

	@Override
	public void save(Shipment t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
	}

	@Override
	public void update(Shipment t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
		
	}

	@Override
	public void delete(Shipment t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
		
	}

	@Override
	public Shipment get(int id) {
		return getEntityManager().find(Shipment.class, id);
	}



	
}
