package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
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
	public boolean save(CourierFirm t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.persist(t));
		}
		catch(PersistenceException e)
		{
			System.out.println("Error saving object!");
			return false;
		}
		return true;
		
	}

	@Override
	public void update(CourierFirm t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.merge(t));
		}
		catch(PersistenceException e)
		{
			System.out.println("Error updating object!");
		}
		
	}

	@Override
	public void delete(CourierFirm t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.remove(t));
		}
		catch(PersistenceException e)
		{
			System.out.println("Error deleting object!");
		}
		
		
	}

	@Override
	public CourierFirm get(int id) {
		return getEntityManager().find(CourierFirm.class, id);
	}
	
	public CourierFirm getFirmByIdAndName(String name,int bulstat)
	{
		CourierFirm firm;
		try {
			firm= (CourierFirm) getEntityManager().createQuery("FROM Firm WHERE bulstat=: bulstat AND companyName=: name")
					.setParameter("name", name)
					.setParameter("bulstat", bulstat)
					.getSingleResult();
		}catch(NoResultException e)
		{
			firm=null;
		}
		return firm;
	
		
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCompanies() {
		
		  return getEntityManager().createQuery("SELECT f.companyName, f.id FROM Firm f")
	                .getResultList();
		
	}

	public int getBulstatByFirmName(String name) {
		
		return (int) getEntityManager().createQuery("SELECT f.id FROM Firm f WHERE f.companyName=: name")
				.setParameter("name", name)
                .getSingleResult();
	}


}
