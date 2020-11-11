package tu_varna.project.courier_system.test;

import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class App {
	public static void main(String[] args) {

		UserService serv = new UserServiceImpl();
		serv.CreateClient("client1", "client1", "Vladimir Kolev", "vlado@gmail.com", "0897266666", "Bulgaria", "Burgas",
				"Rilska 5");
		serv.CreateCourierFirm(12314, "Econt", "Ivan", "0897875642", "Bulgaria", "Kotel", "Ivan Kitelski 13");
		serv.CreateCourier("courier1", "courier1", "Dancho", "dakata@abv.bg", "0923231231", "Bulgaria", "Sliven",
				"Hadji dimitur 5", 12314);
		serv.CreateClient("client2", "client2", "Iliyan Stanchev", "iliyan@gmail.com", "0897875640", "Bulgaria", "Kotel",
				"Luda Kamchia 48");
		serv.CreateClient("client3", "client3", "Boris Petkov", "boris@gmail.com", "0897832323", "Bulgaria", "Varna",
				"Basanovich 15");
		
		
		
	
	}
}
