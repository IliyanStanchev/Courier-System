package tu_varna.project.courier_system.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Admin;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.User;

public class DatabaseGenerator {

	public static void main(String[] args) {
	
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
		client.setAddress(new Address("address","address","address"));
		client.setPhoneNumber("1111111111");
		client.setEmail("system@gmail.com");
		client.setName("system_client");
		

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Admin.class).addAnnotatedClass(Courier.class).addAnnotatedClass(Client.class)
				.addAnnotatedClass(Company.class).addAnnotatedClass(Office.class).addAnnotatedClass(Shipment.class)
				.addAnnotatedClass(Notification.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(adm);
		session.save(client);
		tx.commit();

	}

}
