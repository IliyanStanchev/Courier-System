package tu_varna.project.courier_system.services;

import java.util.List;

import tu_varna.project.courier_system.dao.impl.UserDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.tabelviewClasses.ClientView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public interface UserService
{

	boolean createClient(Client client);

	boolean createCourier(Courier courier);

	void deleteUser(User usr);

	User getUserByID(int id);

	User getUserByPhone(String phoneNmb);

	void changeUserAddress(User user1, String country, String city, String street);

	void changeUserEmail(User user1, String email);

	void changeUserPassword(User user1, String password);

	boolean changeUserPhone(User user1, String phone);

	List<ClientView> getAllClients();

	List<CourierView> getAllCouriers();

	void setUserDao(UserDaoImpl userDao);

}