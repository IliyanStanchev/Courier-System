package tu_varna.project.courier_system.services;

import tu_varna.project.courier_system.entity.User;

public interface LoginService
{

	User authenticateUserLogin(String username, String password);

}