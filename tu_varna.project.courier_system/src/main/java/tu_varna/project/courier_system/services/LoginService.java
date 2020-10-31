package tu_varna.project.courier_system.services;

import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.User;

public class LoginService {

	private UserDaoImpl userDao = new UserDaoImpl();

	public User authenticateUserLogin(String username, String password) {
		User user = userDao.getUserByName(username);
		if (user != null) {
			if (user.getLoginPassword().equals(password)) {
				return user;
			}
			return null;
		}
		return null;
	}

}
