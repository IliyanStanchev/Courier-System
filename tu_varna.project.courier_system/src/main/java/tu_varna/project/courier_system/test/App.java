package tu_varna.project.courier_system.test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;


public class App 
{
    public static void main( String[] args )
    {
    	UserService serv= new UserServiceImpl();
    	serv.CreateClient("new_user", "new_user", "Vlado", "Meow@gmail.com", "089766666", "Bulgaria", "Burgas", "Rilska 5");
        serv.CreateCourierFirm(12314, "Econt", "Ivan", "0897875642", "Bulgaria", "Kotel", "Ivan Kitelski 13");
        CourierFirm firm=serv.SearchCourierFirm(12314);
        System.out.println(firm);
        System.out.println(serv.getShipmentsList(122313));
        System.out.println(serv.SearchUser("Vlado", "089766666"));
        serv.CreateCourier("courier1", "courier1", "Dancho", "dakata@abv.bg", "09232312312", "Bulgaria", "Sliven", "Hadji dimitur 5",12314);
        serv.DeleteUser(serv.SearchUser("Vlado", "089766666"));
        System.out.println(serv.SearchShipment(1, "0897875640"));
        System.out.println(serv.getOfficesList(122313));
        serv.CreateShipment(Type.type.document, LocalDate.now(), 25.5, 4, serv.SearchUserByPhone("0897875230"),122313,1 );
        serv.CreateShipment(Type.type.document, LocalDate.now(), 25.5, 4, serv.SearchUser("Boyan","0897875230"),122313,1 );
        serv.CreateShipment(Type.type.document, LocalDate.now(), 25.5, 4, serv.SearchUser("Boyan","0897875230"),122313,1 );
        Client usr= (Client) serv.SearchUserByPhone("0897875230");
        System.out.println(usr.getReceivedShipments());
        System.out.println(usr.getSendShipments());
       
        
    }
}
