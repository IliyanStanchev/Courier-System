package tu_varna.project.courier_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.services.NotificationService;
import tu_varna.project.courier_system.services.impl.NotificationServiceImpl;

public class ReturnedShipmentFormController {

	private NotificationService notificationService = new NotificationServiceImpl();
	private Notification selectedNotification;
	@FXML
	private Label notificationInfoLabel;

	@FXML
	void okay(ActionEvent event) {
		notificationService.setSeenStatus(selectedNotification);
		// notificationService.deleteNotification(selectedNotification);
		CloseForm.closeForm(event);

	}

	public void setSelectedNotification(Notification notification) {
		this.selectedNotification = notification;
		this.notificationInfoLabel.setText(notification.getNotification_text());
	}

}
