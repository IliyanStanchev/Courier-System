package tu_varna.project.courier_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tu_varna.project.courier_system.controllers.ClientWorkspaceFormController;
import tu_varna.project.courier_system.controllers.NotificationsFormController;
import tu_varna.project.courier_system.helper.OpenNewForm;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private User user;

	private String notification_text;

	private boolean for_accept;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Shipment shipment;

	public Notification() {

	}

	public Notification(User user, String notification_text, boolean for_accept, Shipment shipment) {
		this.user = user;
		this.notification_text = notification_text;
		this.for_accept = for_accept;
		this.shipment = shipment;
	}

	public Notification(User sender, String string, boolean b) {
		this.user = sender;
		this.notification_text = string;
		this.for_accept = b;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNotification_text() {
		return notification_text;
	}

	public void setNotification_text(String notification_text) {
		this.notification_text = notification_text;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public static void sendNotification(String text, Client client) {
		ClientWorkspaceFormController next = new ClientWorkspaceFormController();
		Image img = new Image("tu_varna/project/courier_system/img/notification.png");
		Notifications not = Notifications.create();
		not.title("Notification").text(text).hideAfter(new Duration(9999999)).hideCloseButton().owner(next.getPane())
				.graphic(new ImageView(img)).onAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						FXMLLoader loader = OpenNewForm.openNewForm("NotificationsForm.fxml", "Notifications");
						NotificationsFormController next = loader.getController();
						next.setNotifications(client);

					}

				});
		not.show();
	}

	public boolean isForAccept() {
		return for_accept;
	}

	public void setForAccept(boolean for_accept) {
		this.for_accept = for_accept;
	}

}
