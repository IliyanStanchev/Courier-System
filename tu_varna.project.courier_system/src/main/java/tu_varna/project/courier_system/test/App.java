package tu_varna.project.courier_system.test;


import java.util.Date;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;

import tu_varna.project.courier_system.embeddable.Address;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.roles.Admin;
import tu_varna.project.courier_system.roles.Client;
import tu_varna.project.courier_system.roles.Courier;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
		
    	Office office= new Office();
    	CourierFirm firm= new CourierFirm();
    	firm.setBulstat("122313");
    	firm.setCompanyName("Sach Firm");
    	Address adr= new Address();
    	adr.setCountry("Bulgaria");
    	adr.setCity("Kotel");
    	adr.setStreet("Luda Kamchia");
    	office.setAddress(adr);
    	office.setFirm(firm);
    	Client client = new Client();
        client.setId(3);
        client.setName("Iliyan");
        client.setEmail("Ench3r@gmail.com");
        client.setLoginPassword("client");
        client.setLoginUsername("client");
        client.setPhoneNumber("0897875640");
        
    	Courier usr = new Courier();
        usr.setId(3);
        usr.setName("Iliyan");
        usr.setEmail("Ench3r@gmail.com");
        usr.setLoginPassword("usr");
        usr.setLoginUsername("usr");
        usr.setPhoneNumber("0897875640");
        usr.setFirm(firm);
        firm.getEmployees().add(usr);
        firm.getOffices().add(office);
        usr.setAddress(adr);
        Shipment ship= new Shipment();
        ship.setClient(client);
        ship.setOffice(office);
        ship.setDateCreated(new Date());
        ship.setDateShipped(new Date());
        ship.setShipmentPrice(25.2);
        ship.setStatus(Status.status.declined);
        ship.setType(Type.type.packet);
        client.getShipments().add(ship);
        
        Configuration con= new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(Admin.class).addAnnotatedClass(Courier.class).addAnnotatedClass(Client.class).addAnnotatedClass(CourierFirm.class).addAnnotatedClass(Office.class).addAnnotatedClass(Shipment.class);
        SessionFactory sf= con.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx= session.beginTransaction();
        session.save(office);
        session.save(usr);
        session.save(firm);
        session.save(client);
        session.save(ship);
       // User another_usr= session.get(User.class, 2);
        tx.commit();
      //  System.out.println(another_usr);
    }
}
