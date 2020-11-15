package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class DoYouAcceptShipmentFormController  {

	private UserService service = new UserServiceImpl();
	private Notification selectedNotification;

	@FXML
	private Label notificationInfoLabel;

	public void setSelectedNotification(Notification notification) {
		this.notificationInfoLabel.setText(notification.getNotification_text());
	}

	@FXML
	void acceptShipment(ActionEvent event) {
		service.handleUserNotificationAnswer(true, selectedNotification);

	}

	@FXML
	void declineShipment(ActionEvent event) {
		service.handleUserNotificationAnswer(false, selectedNotification);

	}


}
