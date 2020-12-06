package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.NotificationService;
import tu_varna.project.courier_system.services.impl.NotificationServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.NotificationsView;

public class NotificationsFormController implements Initializable
{

	private NotificationService notificationService = new NotificationServiceImpl();

	@FXML
	private TableColumn<NotificationsView, Integer> numberColumn;
	@FXML
	private TableView<NotificationsView> notificationsTable;
	@FXML
	private TableColumn<NotificationsView, String> notificationColumn;

	private ObservableList<NotificationsView> notifications = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("notificationId"));
		this.notificationColumn.setCellValueFactory(new PropertyValueFactory<>("notificationText"));
		notificationsTable.getItems().clear();

	}

	@FXML
	private void processNoti(MouseEvent event)
	{

		NotificationsView selected = notificationsTable.getSelectionModel().getSelectedItem();
		if (selected != null)
		{
			Notification notification = notificationService.getNotificationByID(selected.getNotificationId());
			if (notification.isForAccept())
			{
				FXMLLoader loader = OpenNewForm.openNewForm("DoYouAcceptShipmentForm.fxml", "New shipment");
				DoYouAcceptShipmentFormController next = loader.getController();
				next.setSelectedNotification(notification);

			} else
			{
				FXMLLoader loader = OpenNewForm.openNewForm("ReturnedShipmentForm.fxml", "Returned shipment");
				ReturnedShipmentFormController next = loader.getController();
				next.setSelectedNotification(notification);

			}
			notifications.remove(selected);
		}
	}

	public void setNotifications(List<Notification> list)
	{

		for (Notification notif : list)
		{
			addToListTable(notif.getId(), notif.getNotification_text());

		}
		notificationsTable.setItems(notifications);
	}

	private void addToListTable(Integer number, String text)
	{
		this.notifications.add(new NotificationsView(number, text));
	}

}
