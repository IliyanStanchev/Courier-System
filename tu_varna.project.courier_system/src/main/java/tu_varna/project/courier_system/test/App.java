package tu_varna.project.courier_system.test;

import java.util.List;
import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.User;

public class App 
{
    public static void main( String[] args )
    {
    
    	Client usrUser= new Client();
    	usrUser.setAddress(new Address("Bulgaria","Varna","Basanovich 13"));
    	usrUser.setEmail("email@abv.bg");
    	usrUser.setLoginPassword("pass1");
    	usrUser.setLoginUsername("usr3");
    	usrUser.setName("Nathan Figuera");
    	usrUser.setPhoneNumber("0897225646");	
    	UserDaoImpl user= new UserDaoImpl();
        List<User> list=user.getAll();
    	System.out.println(list);
    	
        User usr1=user.get(2);
        System.out.println(usr1);
        usr1.setEmail("updatedEmail@gmail.com");
        user.update(usr1);
    	User usr2=user.get(2);
    	System.out.println(usr2);
    	user.save(usrUser);
    	
       
    }
}
