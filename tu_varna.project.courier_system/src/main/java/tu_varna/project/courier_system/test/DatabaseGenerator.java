package tu_varna.project.courier_system.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Admin;
import tu_varna.project.courier_system.entity.Client;

public class DatabaseGenerator
{

	public static void main(String[] args)
	{

		Admin adm = new Admin();
		adm.setAddress(new Address("Bulgaria", "Varna", "Dobrovnik"));
		adm.setEmail("desislava@gmail.com");
		adm.setLoginPassword("admin");
		adm.setLoginUsername("admin");
		adm.setName("Desislava Paunova");
		adm.setPhoneNumber("0897875641");

		Client client = new Client();
		client.setLoginPassword("system");
		client.setLoginUsername("system");
		client.setAddress(new Address("address", "address", "address #1"));
		client.setPhoneNumber("1111111111");
		client.setEmail("system@gmail.com");
		client.setName("system_client");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(adm);
		entityManager.persist(client);
		tx.commit();

	}

}
