package tu_varna.project.courier_system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	private String notification_text;
	private String notification_status;
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
	public String getNotification_status() {
		return notification_status;
	}
	public void setNotification_status(String notification_status) {
		this.notification_status = notification_status;
	}
	
	

}
