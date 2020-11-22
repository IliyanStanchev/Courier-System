package tu_varna.project.courier_system.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String loginUsername;

	private String loginPassword;

	private String name;

	private String email;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Notification> notifications;

	@Column(unique = true)
	private String phoneNumber;

	private Address address;

	public User() {

	}

	public User(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street) {

		this.loginUsername = loginUsername;
		this.loginPassword = loginPassword;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = new Address(country, city, street);
	}

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public String getName() {
		return name;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public abstract void loadController();

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User id=" + id + ", loginUsername=" + loginUsername + ", loginPassword=" + loginPassword + ", name="
				+ name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + "\n";
	}

	public boolean hasNotifications() {
		for (Notification notification : notifications) {
			if (notification.Isseen() == false) {
				return true;
			}
		}
		return false;
	}

}
