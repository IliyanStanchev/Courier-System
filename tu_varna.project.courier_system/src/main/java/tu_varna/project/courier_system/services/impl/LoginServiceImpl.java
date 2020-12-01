package tu_varna.project.courier_system.services.impl;

import tu_varna.project.courier_system.dao.UserDao;
import tu_varna.project.courier_system.dao.impl.UserDaoImpl;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.services.LoginService;

public class LoginServiceImpl implements LoginService {

	private UserDao userDao = new UserDaoImpl();

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User authenticateUserLogin(String username, String password) {
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			if (user.getLoginPassword().equals(password)) {
				return user;
			}
			return null;
		}
		return null;
	}

}
