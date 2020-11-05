package tu_varna.project.courier_system.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.User;


public class UserDaoImpl extends entityManager implements BaseDao<User>{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM User e");
        return query.getResultList();
	}

	@Override
	public boolean save(User t) {
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
	public void update(User t) {
		
		try {
			executeInsideTransaction(entityManager -> entityManager.merge(t));
		}
		catch(PersistenceException e)
		{
			System.out.println("Error updating object!");
		}
	}

	@Override
	public void delete(User t) {
		
		try {
			executeInsideTransaction(entityManager -> entityManager.remove(t));
		}
		catch(PersistenceException e)
		{
			System.out.println("Error deleting object!");
		}
		
	}

	@Override
	public User get(int id) {
		User user;
		
		try {
			user= getEntityManager().find(User.class, id);
		}
		catch(NoResultException e)
		{
			user=null;
		}
		return user;
	}

	
	public User getUserByUsername(String name)
	{
		User user;
		try {
			user= (User) getEntityManager().createQuery("FROM User WHERE loginUsername=: name")
					.setParameter("name", name)
					.getSingleResult();
		}catch(NoResultException e)
		{
			user=null;
		}
		return user;
	
		
	}
	
	public User getUserByNameAndPhone(String name,String phone)
	{
		User user;
		try {
			user= (User) getEntityManager().createQuery("FROM User WHERE name=: name AND phoneNumber=: phone")
					.setParameter("name", name)
					.setParameter("phone", phone)
					.getSingleResult();
		}catch(NoResultException e)
		{
			user=null;
		}
		return user;
	
		
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCouriers() {
		
        return getEntityManager().createQuery("SELECT u.name, u.phoneNumber, f.companyName FROM User u, Courier c, Firm f WHERE u.id = c.id AND c.firm = f.id")
                .getResultList();
       
	}

	public User getUserByPhone(String phoneNmb) {
		User user;
		try {
			user= (User) getEntityManager().createQuery("FROM User WHERE phoneNumber=: phoneNmb")
					.setParameter("phoneNmb", phoneNmb)
					.getSingleResult();
		}catch(NoResultException e)
		{
			user=null;
		}
		return user;
		
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllClients() {
		return getEntityManager().createQuery("SELECT u.name, u.phoneNumber FROM User u, Client c WHERE u.id = c.id")
               .getResultList();
	}

	public String getUserName(int id) {
		return (String) getEntityManager().createQuery("SELECT u.name FROM User u WHERE u.id =: id")
		.setParameter("id",id)
        .getSingleResult();
	}

	public void ChangeUserAddress(User user, String country, String city, String street) {
	  
		user.setAddress(new Address(country,city,street));
		update(user);
	    
		
	}

	public void ChangeUserPhone(User user, String phone) {
		user.setPhoneNumber(phone);
		update(user);
		
	}

	public void ChangeUserPassword(User user, String password) {
		user.setLoginPassword(password);
	    update(user);
		
	}

	public void ChangeUserEmail(User user, String email) {
		user.setEmail(email);
		update(user);
		
	}
	public int getBulstatByCourier(int id) {
		
		return (int) getEntityManager().createQuery("SELECT f.id FROM Courier c,User u,Firm f WHERE c.id=u.id AND u.id=: id AND f.id=c.firm")
				.setParameter("id",id)
		        .getSingleResult();
		
	}

	
		
	

}
