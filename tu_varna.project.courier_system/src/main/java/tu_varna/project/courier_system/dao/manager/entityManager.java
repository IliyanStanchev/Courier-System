package tu_varna.project.courier_system.dao.manager;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class entityManager {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
	private static final EntityManager entityManager = emf.createEntityManager();

	public static void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

}
