package tu_varna.project.courier_system.services;

import java.util.List;

import tu_varna.project.courier_system.dao.impl.NotificationDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;

public interface NotificationService
{

	void sendNotification(String text, Client client);

	List<Notification> getListNotifications(Client client);

	void handleUserNotificationAnswer(boolean answer, Notification notification);

	Notification getNotificationByID(int id);

	void deleteNotification(Notification notification);

	void setSeenStatus(Notification selectedNotification);

	void setNotificationDao(NotificationDaoImpl notificationDao);

}