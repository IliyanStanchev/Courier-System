package tu_varna.project.courier_system.dao;

import java.util.List;

import tu_varna.project.courier_system.entity.Notification;

public interface NotificationDao {

	Notification get(int id);

	boolean save(Notification t);

	void update(Notification t);

	void delete(Notification t);

	List<Notification> getUserNotifications(int id);

}