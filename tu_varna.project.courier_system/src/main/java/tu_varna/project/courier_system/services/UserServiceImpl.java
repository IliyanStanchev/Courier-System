package tu_varna.project.courier_system.services;

import java.util.ArrayList;
import java.util.List;

import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.tabelviewClasses.ClientView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public class UserServiceImpl implements UserService {

	private UserDaoImpl userDao = new UserDaoImpl();

	public void setUserDao(UserDaoImpl userDao) {

		this.userDao = userDao;
	}

	@Override
	public boolean createClient(Client client) {
		return userDao.save(client);

	}

	@Override
	public boolean createCourier(Courier courier) {
		return userDao.save(courier);

	}

	@Override
	public void deleteUser(User usr) {
		userDao.delete(usr);
	}

	@Override
	public User getUserByID(int id) {
		return userDao.get(id);
	}

	@Override
	public User getUserByPhone(String phoneNmb) {

		return userDao.getUserByPhone(phoneNmb);

	}

	@Override
	public List<ClientView> getAllClients() {
		List<ClientView> toReturn = new ArrayList<ClientView>();
		List<Object[]> list = userDao.getAllClients();
		for (Object[] column : list) {
			toReturn.add(new ClientView((String) column[0], (String) column[1]));
		}
		return toReturn;
	}

	@Override
	public List<CourierView> getAllCouriers() {
		List<CourierView> toReturn = new ArrayList<CourierView>();
		List<Object[]> list = userDao.getAllCouriers();
		for (Object[] column : list) {
			toReturn.add(new CourierView((String) column[0], (String) column[1], (String) column[2]));
		}
		return toReturn;
	}

	@Override
	public void changeUserAddress(User user, String country, String city, String street) {

		user.setAddress(new Address(country, city, street));
		userDao.update(user);

	}

	@Override
	public void changeUserEmail(User user, String email) {
		user.setEmail(email);
		userDao.update(user);

	}

	@Override
	public void changeUserPassword(User user, String password) {
		user.setLoginPassword(password);
		userDao.update(user);
	}

	@Override
	public void changeUserPhone(User user, String phone) {
		user.setPhoneNumber(phone);
		userDao.update(user);

	}

}
