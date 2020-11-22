package tu_varna.project.courier_system.services;

import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.User;

public class LoginServiceImpl implements LoginService {

	private UserDaoImpl userDao = new UserDaoImpl();

	public void setUserDao(UserDaoImpl userDao) {
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
