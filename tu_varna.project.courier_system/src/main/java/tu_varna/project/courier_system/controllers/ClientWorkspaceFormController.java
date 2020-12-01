package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.NotificationService;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.NotificationServiceImpl;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;

public class ClientWorkspaceFormController {

	private static final Logger logger = LogManager.getLogger(ClientWorkspaceFormController.class);

	private ShipmentService shipmentService = new ShipmentServiceImpl();
	private NotificationService notificationService = new NotificationServiceImpl();

	private Client client;
	private static int id;

	@FXML
	private AnchorPane workPane;
	@FXML
	private Label welcomeUser;
	@FXML
	private ImageView notiIcon;

	@FXML
	private void notificationsView(ActionEvent event) throws IOException {

		setNotiIcon();
		FXMLLoader loader = OpenNewForm.openNewForm("NotificationsForm.fxml", "Notifications");
		NotificationsFormController next = loader.getController();
		next.setNotifications(notificationService.getListNotifications(client));

	}

	@FXML
	private void requestShipment(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("RequestShipmentForm.fxml", "Create shipment");
		RequestShipmentFormController next = loader.getController();
		next.getCompanyForClient(client);
	}

	@FXML
	private void clientShipments(ActionEvent event) throws IOException {
		FXMLLoader loader = BuiltInForm.built_inForm("ExpectedShipmentsForm.fxml", workPane);
		ExpectedShipmentsFormController next = loader.getController();
		next.setExpectedShipments(shipmentService.getExpectedShipments(client));

	}

	@FXML
	private void requestedShipment(ActionEvent event) throws IOException {
		FXMLLoader loader = BuiltInForm.built_inForm("RequestedShipmentsForm.fxml", workPane);
		RequestedShipmentsFormController next = loader.getController();
		next.setRequestedShipments(shipmentService.getRequestedShipments(client));

	}

	@FXML
	private void viewProfile(ActionEvent event) throws IOException {
		FXMLLoader loader = BuiltInForm.built_inForm("ClientProfileForm.fxml", workPane);
		ClientProfileFormController next = loader.getController();
		next.setClientInformation(client);

	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		CloseForm.closeForm(event);
		logger.info("Client with id: " + client.getId() + " successfully logged out!");
		id = 0;
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

	public static int getID() {
		return id;
	}

	public AnchorPane getPane() {
		return workPane;
	}

	public void setNotiIcon() {

		{
			Image iconWithNoNotifications = new Image("tu_varna/project/courier_system/img/noNoti.png");
			Image iconWithNotifications = new Image("tu_varna/project/courier_system/img/withNoti .png");
			if (notificationService.getListNotifications(client).isEmpty()) {
				notiIcon.setImage(iconWithNoNotifications);
			} else
				notiIcon.setImage(iconWithNotifications);

		}
	}

	public void setUser(User user) {
		client = (Client) user;
		id = client.getId();
		welcomeUser.setText("Welcome " + client.getName());
		logger.info("Client with id: " + client.getId() + " successfully logged in!");
		if (this.client.hasNotifications()) {
			notificationService.sendNotification("You have new notifications about shipments!.", this.client);
		}
	}

}
