package tu_varna.project.courier_system.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tu_varna.project.courier_system.controllers.ClientWorkspaceFormController;
import tu_varna.project.courier_system.controllers.NotificationsFormController;
import tu_varna.project.courier_system.dao.NotificationDao;
import tu_varna.project.courier_system.dao.ShipmentDao;
import tu_varna.project.courier_system.dao.impl.NotificationDaoImpl;
import tu_varna.project.courier_system.dao.impl.ShipmentDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.NotificationService;

public class NotificationServiceImpl implements NotificationService
{

	private NotificationDao notificationDao = new NotificationDaoImpl();
	private ShipmentDao shipmentDao = new ShipmentDaoImpl();

	@Override
	public void sendNotification(String text, Client client)
	{
		ClientWorkspaceFormController next = new ClientWorkspaceFormController();
		Image img = new Image("tu_varna/project/courier_system/img/notification.png");
		Notifications not = Notifications.create();
		not.title("Notification").text(text).hideAfter(new Duration(5000)).hideCloseButton().owner(next.getPane())
				.graphic(new ImageView(img)).onAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						FXMLLoader loader = OpenNewForm.openNewForm("NotificationsForm.fxml", "Notifications");
						NotificationsFormController next = loader.getController();
						next.setNotifications(getListNotifications(client));
					}
				});
		not.show();
	}

	@Override
	public List<Notification> getListNotifications(Client client)
	{
		return notificationDao.getUserNotifications(client.getId());
	}

	@Override
	public void handleUserNotificationAnswer(boolean answer, Notification notification)
	{
		Shipment shipment = notification.getShipment();
		if (answer == true)
		{
			shipment.setStatus(status.accepted);
			shipmentDao.update(shipment);

		} else if (answer == false)
		{
			shipment.setStatus(status.declined);
			shipmentDao.update(shipment);
			Shipment new_shipment = new Shipment(status.in_proccess_of_return, shipment.getType(), LocalDate.now(),
					0.00, shipment.getReceiver(), shipment.getSender(), shipment.getFirm());
			shipmentDao.save(new_shipment);
			notificationDao.save(new Notification(shipment.getSender(),
					shipment.getReceiver().getName() + " declined your shipment! A courier will bring it back to you!",
					false));

		}
		notification.setIsSeen(true);
		notificationDao.update(notification);

	}

	@Override
	public Notification getNotificationByID(int id)
	{
		return notificationDao.get(id);
	}

	@Override
	public void deleteNotification(Notification notification)
	{
		notificationDao.delete(notification);

	}

	@Override
	public void setSeenStatus(Notification notification)
	{
		notification.setIsSeen(true);
		notificationDao.update(notification);

	}

	@Override
	public void setNotificationDao(NotificationDaoImpl notificationDao)
	{

		this.notificationDao = notificationDao;

	}

}
