package tu_varna.project.courier_system.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.User;

@RunWith(JUnitPlatform.class)
class NotificationDaoImplTest {
	
	UserDaoImpl userDao;
	
	NotificationDaoImpl notificationDao;


	@BeforeEach
	void init()
	{
		entityManager.initEntityManager("persistence_test");
		notificationDao= new NotificationDaoImpl();
		userDao=new UserDaoImpl();
		
	}
	

	@Test
	void testSaveAndThenGet() {
		Notification notification = new Notification();
		notificationDao.save(notification);
		Notification dbNotification = notificationDao.get(1);
		assertEquals(notification,dbNotification);
	}


	
	@Test
	void testUpdateAndThenGet() {
		Notification notification = new Notification();
		notificationDao.save(notification);
		Notification dbNotification = notificationDao.get(1);
		dbNotification.setNotification_text("message");
		notificationDao.update(dbNotification);
		Notification updatedNotification = notificationDao.get(1);
		assertEquals("message",updatedNotification.getNotification_text());

	}

	@Test
	void testDelete() {
		Notification notification = new Notification();
		notificationDao.save(notification);
		Notification dbNotification = notificationDao.get(1);
		notificationDao.delete(dbNotification);
		assertEquals(null,notificationDao.get(1));
		
	}

	@Test
	void testGetUserNotifications() {
		User user= new Client();
		userDao.save(user);
		Notification notification1 = new Notification();
		notification1.setUser(user);
	    notification1.setIsSeen(false);
		notificationDao.save(notification1);
		Notification notification2 = new Notification();
		notification2.setUser(user);
		notification2.setIsSeen(false);
		notificationDao.save(notification2);
		assertEquals(2,notificationDao.getUserNotifications(1).size());
		
	}


}
