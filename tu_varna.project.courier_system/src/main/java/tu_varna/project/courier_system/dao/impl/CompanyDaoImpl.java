package tu_varna.project.courier_system.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import tu_varna.project.courier_system.dao.CompanyDao;
import tu_varna.project.courier_system.dao.em.entityManager;
import tu_varna.project.courier_system.entity.Company;

public class CompanyDaoImpl implements CompanyDao  {

	
	@Override
	public Company get(int id) {
		return entityManager.getEntityManager().find(Company.class, id);
	}

	
	@Override
	public boolean save(Company t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	
	@Override
	public void update(Company t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.merge(t));
		} catch (PersistenceException e) {
			System.out.println("Error updating object!");
		}

	}

	
	@Override
	public void delete(Company t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.remove(t));
		} catch (PersistenceException e) {
			System.out.println("Error deleting object!");
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCompanies() {

		return entityManager.getEntityManager().createQuery("SELECT f.id, f.companyName FROM Firm f").getResultList();

	}

}
