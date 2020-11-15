package tu_varna.project.courier_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class ReturnedShipmentFormController {

	private UserService service = new UserServiceImpl();
	@FXML
	private Label notificationInfoLabel;

	public void setSelectedNotification(int notificationID) {
		this.notificationInfoLabel.setText(service.SearchNotificationByID(notificationID).getNotification_text());
	}
	   @FXML
	    void okay(ActionEvent event) {

	    }

}

