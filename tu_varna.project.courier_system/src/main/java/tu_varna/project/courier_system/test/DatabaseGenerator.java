package tu_varna.project.courier_system.test;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Admin;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Manager;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.User;

public class DatabaseGenerator {

	public static void main(String[] args) {
		
    	Office office= new Office();
    	CourierFirm firm= new CourierFirm();
    	firm.setId(122313);
    	firm.setCompanyName("Sach Firm");
    	firm.setManager(new Manager("Desislav","089777777"));
    	firm.setAddress(new Address("Bulgaria","Varna","Mir 17"));
    	Address adr= new Address();
    	adr.setCountry("Bulgaria");
    	adr.setCity("Kotel");
    	adr.setStreet("Luda Kamchia");
    	Address adr1= new Address("Bulgaria","Varna","Vasil Stavrev 2");
    	
    	office.setAddress(adr1);
    	office.setFirm(firm);
    	office.setName("Sach Firm Levski");
    	office.setManager(new Manager("Mitko","083123123"));
    	Client client = new Client();
        client.setName("Iliyan");
        client.setEmail("Ench3r@gmail.com");
        client.setLoginPassword("client");
        client.setLoginUsername("client");
        client.setPhoneNumber("0897875640");
        client.setAddress(adr1);
        
        Client client1 = new Client();
        client1.setName("Boyan");
        client1.setEmail("boyancho@gmail.com");
        client1.setLoginPassword("client11");
        client1.setLoginUsername("client11");
        client1.setPhoneNumber("0897875230");
        client1.setAddress(adr1);
        
        
    	Courier usr = new Courier();
        usr.setId(3);
        usr.setName("Stoyan");
        usr.setEmail("Courier@gmail.com");
        usr.setLoginPassword("usr");
        usr.setLoginUsername("usr");
        usr.setPhoneNumber("0897233342");
        usr.setFirm(firm);
        firm.getEmployees().add(usr);
        firm.getOffices().add(office);
        
        usr.setAddress(adr);
        Shipment ship= new Shipment();
        ship.setReceiver(client);
        ship.setSender(client1);
        ship.setFirm(firm);
        ship.setDateCreated(LocalDate.now());
        ship.setDateShipped(LocalDate.now());
        ship.setShipmentPrice(25.2);
        ship.setStatus(Status.status.delivered);
        ship.setType(Type.type.packet);
        ship.setCourier(usr);
        usr.getShipmentsForDelivery().add(ship);
        client.getReceivedShipments().add(ship);
        firm.getShipments().add(ship);
        Admin adm= new Admin();
        adm.setAddress(new Address("Bulgaria","Varna","Dobrovnik"));
        adm.setEmail("desislava@gmail.com");
        adm.setLoginPassword("admin");
        adm.setLoginUsername("admin");
        adm.setName("Desislava Paunova");
        adm.setPhoneNumber("0897875641");
       
        
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Admin.class).addAnnotatedClass(Courier.class).addAnnotatedClass(Client.class).addAnnotatedClass(CourierFirm.class).addAnnotatedClass(Office.class).addAnnotatedClass(Shipment.class).addAnnotatedClass(Notification.class);
        Notification notif = new Notification();
        notif.setNotification_status("pending");
        notif.setNotification_text("U have no permission to do that");
        notif.setUser(adm);
        SessionFactory sf= con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx= session.beginTransaction();
        session.save(notif);
        session.save(firm);
        session.save(adm);
        session.save(office);
        session.save(usr);
        
        session.save(client1);
        session.save(client);
        session.save(ship);
        tx.commit();
       

	}

}
