package tu_varna.project.courier_system.dao;

import java.util.List;

import tu_varna.project.courier_system.entity.User;

public interface UserDao {

	boolean save(User t);

	User get(int id);

	void update(User t);

	void delete(User t);

	User getUserByPhone(String phoneNmb);

	User getUserByUsername(String name);

	List<Object[]> getAllClients();

	List<Object[]> getAllCouriers();

}