package tu_varna.project.courier_system.test;

import tu_varna.project.courier_system.dao.em.entityManager;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;
import tu_varna.project.courier_system.services.impl.UserServiceImpl;

public class App
{
	public static void main(String[] args)
	{
		entityManager.initEntityManager("persistence");

		UserService serv = new UserServiceImpl();

		CompanyService companyService = new CompanyServiceImpl();
		Company company = new Company(12314, "Econt", "Ivan", "0897875642", "Bulgaria", "Kotel", "Ivan Kitelski #13");
		serv.createClient(new Client("client1", "client1", "Vladimir Kolev", "vlado@gmail.com", "0897266666",
				"Bulgaria", "Burgas", "Rilska #5"));
		companyService.createCompany(company);
		serv.createCourier(new Courier("courier1", "courier1", "Dancho", "dakata@abv.bg", "0923231231", "Bulgaria",
				"Sliven", "Hadji dimitur #5", company));
		serv.createClient(new Client("client2", "client2", "Iliyan Stanchev", "iliyan@gmail.com", "0897875640",
				"Bulgaria", "Kotel", "Luda Kamchia #48"));
		serv.createClient(new Client("client3", "client3", "Boris Petkov", "boris@gmail.com", "0897832323", "Bulgaria",
				"Varna", "Basanovich #15"));
		companyService.createOffice("Econt", "Bulgaria", "Varna", "Seliminski #4", "Desislava Paunova", "021312312", 12314);
		companyService.createOffice("Econt", "Bulgaria", "Varna", "Vasil Stavrev #2", "Iliyan Stanchev", "023211111", 12314);

	}
}
