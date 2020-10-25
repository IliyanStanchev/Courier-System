package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;



import tu_varna.project.courier_system.entity.User;

public class UserDaoImpl extends entityManager implements BaseDao<User>{
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM User e");
        return query.getResultList();
	}

	@Override
	public void save(User t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
		
	}

	@Override
	public void update(User t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
		
	}

	@Override
	public void delete(User t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
		
	}

	@Override
	public User get(int id) {
		return getEntityManager().find(User.class, id);
	}

	
	public User getUserByName(String name)
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
		
	

}
