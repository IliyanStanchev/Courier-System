package tu_varna.project.courier_system.dao;

import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class entityManager {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
	private EntityManager entityManager= emf.createEntityManager();
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
	}


}
