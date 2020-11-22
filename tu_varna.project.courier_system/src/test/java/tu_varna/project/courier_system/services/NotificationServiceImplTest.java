package tu_varna.project.courier_system.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tu_varna.project.courier_system.dao.NotificationDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class NotificationServiceImplTest {

	NotificationServiceImpl notificationService;

	@Mock
	NotificationDaoImpl notificationDao;

	@BeforeEach
	void init() {
		notificationService = new NotificationServiceImpl();
		notificationService.setNotificationDao(notificationDao);
	}

	@Test
	void testGetNotificationByID() {
		final Notification notification = new Notification();
		notification.setId(1);
		when(notificationDao.get(1)).thenReturn(notification);
		Notification returnedNotification = notificationService.getNotificationByID(1);
		assertNotNull(returnedNotification);
		verify(notificationDao).get(1);

	}

	@Test
	void testDeleteNotification() {

		final Notification notification = notificationDao.get(1);
		notificationService.deleteNotification(notification);
		verify(notificationDao).delete(notification);
	}

	@Test
	void testGetListNotifications() {

		Client client = new Client();
		client.setId(1);

		List<Notification> notifications = new ArrayList<Notification>();
		notifications.add(new Notification());
		notifications.add(new Notification());
		notifications.add(new Notification());
		when(notificationDao.getUserNotifications(1)).thenReturn(notifications);
		assertEquals(notifications, notificationService.getListNotifications(client));
	}

}
