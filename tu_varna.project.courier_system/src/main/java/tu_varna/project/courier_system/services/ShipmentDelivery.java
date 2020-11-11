package tu_varna.project.courier_system.services;

import java.time.LocalDate;

import javafx.application.Platform;
import tu_varna.project.courier_system.controllers.ClientWorkspaceFormController;
import tu_varna.project.courier_system.dao.NotificationDaoImpl;
import tu_varna.project.courier_system.dao.ShipmentDaoImpl;
import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;

public class ShipmentDelivery implements Runnable {

	private ShipmentDaoImpl ship = new ShipmentDaoImpl();
	private NotificationDaoImpl notif = new NotificationDaoImpl();
	private UserDaoImpl user = new UserDaoImpl();
	private Shipment shipment = new Shipment();

	public ShipmentDelivery(Shipment shipment) {
		this.shipment = shipment;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		boolean isAcceptable=true;
		if (shipment.getStatus() == status.in_proccess_of_return) {
			isAcceptable=false;
		}
		try {

				Thread.sleep(5000);
				ship.ChangeShipmentStatus(this.shipment, status.in_proccess_of_delivery);
				Thread.sleep(5000);
				ship.ChangeShipmentStatus(this.shipment, status.delivered);
				ship.setShipmentDateShipped(this.shipment, LocalDate.now());
			

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		if(isAcceptable==true)
		{
		sendNotification("A " + this.shipment.getType().toString() + " from " + this.shipment.getSender().getName()
				+ " has been send to you. Due amount: " + this.shipment.getShipmentPrice(),true);
		}
		else
		{
			sendNotification("A " + this.shipment.getType().toString() + " from " + this.shipment.getSender().getName()
					+ " has been returned back to you. ",false);
			ship.ChangeShipmentStatus(shipment, status.accepted);
		}
		
	}

	public void sendNotification(String text,boolean isAcceptable) {
		
		Notification notification = new Notification(this.shipment.getReceiver(),text,isAcceptable, this.shipment);
		notif.save(notification);
		notification = new Notification(this.shipment.getSender(),
				"Your shipment to " + shipment.getReceiver().getName() + " was successfully delivered!", false);
		notif.save(notification);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (ClientWorkspaceFormController.getID() == user.get(shipment.getReceiver().getId()).getId()) {
					Notification.sendNotification("A shipment was delivered to you right now!",
							(Client) user.get(shipment.getReceiver().getId()));
				} else if (ClientWorkspaceFormController.getID() == user.get(shipment.getSender().getId()).getId()) {
					Notification.sendNotification(
							"Your shipment to " + shipment.getReceiver().getName() + " was successfully delivered!",
							(Client) user.get(shipment.getSender().getId()));
				}
			}

		});
	}
}
