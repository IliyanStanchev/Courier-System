package tu_varna.project.courier_system.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.services.NotificationService;
import tu_varna.project.courier_system.services.impl.NotificationServiceImpl;

public class DoYouAcceptShipmentFormController {

	private static final Logger logger = LogManager.getLogger(DoYouAcceptShipmentFormController.class);

	private NotificationService notificationService = new NotificationServiceImpl();

	private Notification selectedNotification;

	@FXML
	private Label notificationInfoLabel;

	@FXML
	void acceptShipment(ActionEvent event) {

		notificationService.handleUserNotificationAnswer(true, selectedNotification);
		logger.info("Client with id:" + selectedNotification.getShipment().getReceiver().getId()
				+ " accepted shipment with id: " + selectedNotification.getShipment().getId());
		CloseForm.closeForm(event);

	}

	@FXML
	void declineShipment(ActionEvent event) {

		notificationService.handleUserNotificationAnswer(false, selectedNotification);
		logger.info("Client with id:" + selectedNotification.getShipment().getReceiver().getId()
				+ " declined shipment with id: " + selectedNotification.getShipment().getId());
		CloseForm.closeForm(event);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Declined shipment");
		alert.setContentText("You declined a shipment!\n The return to sender will cost you: "
				+ selectedNotification.getShipment().getType().showPrice());
		alert.show();
	}

	public void setSelectedNotification(Notification notification) {
		this.selectedNotification = notification;
		this.notificationInfoLabel.setText(notification.getNotification_text());
	}

}
