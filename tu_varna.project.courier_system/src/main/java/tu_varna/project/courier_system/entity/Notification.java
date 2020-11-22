package tu_varna.project.courier_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private User user;

	private String notification_text;

	private boolean for_accept;

	private boolean is_seen;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Shipment shipment;

	public Notification() {

	}

	public Notification(User sender, String string, boolean b) {
		this.user = sender;
		this.notification_text = string;
		this.for_accept = b;
		this.is_seen = false;
	}

	public Notification(User user, String notification_text, boolean for_accept, Shipment shipment) {
		this.user = user;
		this.notification_text = notification_text;
		this.for_accept = for_accept;
		this.shipment = shipment;
		this.is_seen = false;
	}

	public int getId() {
		return id;
	}

	public String getNotification_text() {
		return notification_text;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public User getUser() {
		return user;
	}

	public boolean isForAccept() {
		return for_accept;
	}

	public void setForAccept(boolean for_accept) {
		this.for_accept = for_accept;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNotification_text(String notification_text) {
		this.notification_text = notification_text;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean Isseen() {
		return is_seen;
	}

	public void setIsSeen(boolean is_seen) {
		this.is_seen = is_seen;
	}

}
