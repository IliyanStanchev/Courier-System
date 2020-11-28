package tu_varna.project.courier_system.services;

import java.time.LocalDate;

import javafx.application.Platform;
import tu_varna.project.courier_system.controllers.ClientWorkspaceFormController;
import tu_varna.project.courier_system.dao.NotificationDaoImpl;
import tu_varna.project.courier_system.dao.ShipmentDaoImpl;
import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;

public class ShipmentDeliveryService implements Runnable {

	
	private Shipment shipment = new Shipment();
	
	private ShipmentDaoImpl shipmentDao = new ShipmentDaoImpl();
	private NotificationDaoImpl notificationDao = new NotificationDaoImpl();
	private UserDaoImpl userDao = new UserDaoImpl();
	private NotificationService notificationService = new NotificationServiceImpl();

	public ShipmentDeliveryService(Shipment shipment) {
		this.shipment = shipment;
		
	}

	@Override
	public void run() {
		boolean isAcceptable = true;
		if (shipment.getStatus() == status.in_proccess_of_return) {
			isAcceptable = false;
		}
		try {
			int deliveryTime= calculateDeliveryTime();
			shipment.setStatus(status.taken_by_courier);
			shipmentDao.update(shipment);
			Thread.sleep(deliveryTime);
			shipment.setStatus(status.in_proccess_of_delivery);
			shipmentDao.update(shipment);
			Thread.sleep(deliveryTime);
			shipment.setStatus(status.delivered);
			shipment.setDateShipped(LocalDate.now());
			shipmentDao.update(shipment);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		if (isAcceptable == true) {
			sendNotification("A " + this.shipment.getType().toString() + " from " + this.shipment.getSender().getName()
					+ " has been send to you. Due amount: " + this.shipment.getShipmentPrice(), true);
		} else {
			sendNotification("A " + this.shipment.getType().toString() + " from " + this.shipment.getSender().getName()
					+ " has been returned back to you. ", false);
			shipment.setStatus(status.accepted);
			shipmentDao.update(shipment);

		}

	}

	private void sendNotification(String text, boolean isAcceptable) {

		Notification notification = new Notification(this.shipment.getReceiver(), text, isAcceptable, this.shipment);
		notificationDao.save(notification);
		notification = new Notification(this.shipment.getSender(),
				"Your shipment to " + shipment.getReceiver().getName() + " was successfully delivered!", false);
		notificationDao.save(notification);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (ClientWorkspaceFormController.getID() == userDao.get(shipment.getReceiver().getId()).getId()) {
					notificationService.sendNotification("A shipment was delivered to you right now!",
							(Client) userDao.get(shipment.getReceiver().getId()));
				}
				if (ClientWorkspaceFormController.getID() == userDao.get(shipment.getSender().getId()).getId()) {
					notificationService.sendNotification(
							"Your shipment to " + shipment.getReceiver().getName() + " was successfully delivered!",
							(Client) userDao.get(shipment.getSender().getId()));
				}
			}

		});
	}
	
	private int calculateDeliveryTime()
	{
		Address senderAddress= this.shipment.getSender().getAddress();
		Address receiverAddress= this.shipment.getReceiver().getAddress();
		if(senderAddress.getCountry().equalsIgnoreCase(receiverAddress.getCountry()))
		{
			if(senderAddress.getCity().equalsIgnoreCase(receiverAddress.getCity()))
			{
				return 5000;
			}
			return 10000;
		}
		return 15000;
	}
	
	public void startDelivery()
    {
		Thread t = new Thread(this);
		t.start();
	}
  
}
