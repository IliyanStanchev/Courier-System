package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.NotificationsView;

public class NotificationsFormController implements Initializable {

	private UserService service = new UserServiceImpl();

	@FXML
	private TableColumn<NotificationsView, Integer> numberColumn;
	@FXML
	private TableView<NotificationsView> notificationsTable;

	@FXML
	private TableColumn<NotificationsView, String> notificationColumn;

	private ObservableList<NotificationsView> notifications = FXCollections.observableArrayList();

	@FXML
	private Label resultLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("notificationId"));
		this.notificationColumn.setCellValueFactory(new PropertyValueFactory<>("notificationText"));
		notificationsTable.setItems(notifications);

	}

	@FXML
	void acceptShipment(ActionEvent event) {

		resultLabel.setText("");
		NotificationsView selected = notificationsTable.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Notification notification = service.SearchNotificationByID(selected.getNotificationId());
			service.handleUserNotificationAnswer(true, notification);
			notifications.remove(selected);

		} else
			resultLabel.setText("First select");
	}

	@FXML
	void cancelShipment(ActionEvent event) {
		resultLabel.setText("");
		NotificationsView selected = notificationsTable.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Notification notification = service.SearchNotificationByID(selected.getNotificationId());
			service.handleUserNotificationAnswer(false, notification);
			notifications.remove(selected);

		} else
			resultLabel.setText("First select");
	}

	public void addToListTabel(Integer number, String text) {
		notifications.add(new NotificationsView(number, text));
	}

	public void setNotifications(Client client) {
		for (Notification notif : client.getNotifications()) {

			addToListTabel(notif.getId(), notif.getNotification_text());
		}

	}
}
