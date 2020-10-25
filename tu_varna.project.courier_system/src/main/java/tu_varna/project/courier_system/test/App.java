package tu_varna.project.courier_system.test;

import java.util.List;

import tu_varna.project.courier_system.dao.OfficeDaoImpl;
import tu_varna.project.courier_system.dao.ShipmentDaoImpl;
import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.User;

public class App 
{
    public static void main( String[] args )
    {
        OfficeDaoImpl office= new OfficeDaoImpl();
    	Client usrUser= new Client();
    	usrUser.setAddress(new Address("Bulgaria","Varna","Basanovich 13"));
    	usrUser.setEmail("email@abv.bg");
    	usrUser.setLoginPassword("pass1");
    	usrUser.setLoginUsername("usr9");
    	usrUser.setName("Nathan Figuera");
    	usrUser.setPhoneNumber("0297825046");
    	Shipment ship= new Shipment();
    	UserDaoImpl user= new UserDaoImpl();
    	ship.setOffice(office.get(1));
    	ship.setSender((Client) user.get(3));
    	ship.setReceiver(usrUser);
    	usrUser.getSendShipments().add(ship);
    	
    	ShipmentDaoImpl ship1= new ShipmentDaoImpl();
        List<User> list=user.getAll();
    	System.out.println(list);
    	
        User usr1=user.get(2);
        usr1.loadView();
        System.out.println(usr1);
        usr1.setEmail("updatedEmail@gmail.com");
        user.update(usr1);
    	User usr2=user.get(2);
    	System.out.println(usr2);
    	user.save(usrUser);
    	ship1.save(ship);
    	System.out.println(user.getUserByName("admin"));
    	
       
    }
}
