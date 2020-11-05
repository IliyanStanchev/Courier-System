package tu_varna.project.courier_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import javafx.fxml.FXMLLoader;

@Entity
@Inheritance(strategy=  InheritanceType.JOINED)
public abstract class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column(unique=true)
    private String loginUsername;
    
   
    private String loginPassword;
    
   
    private String name;
    
   
    private String email;
    
    @OneToOne(mappedBy="user")
    private Notification notification;
    
	@Column(unique=true)
    private String phoneNumber;
    
	private Address address;
    
    public abstract String loadView();
    
    public abstract void loadController(FXMLLoader loader);
    
    
    
    public User() {
    
    }
    
    public User(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street) {
		
		this.loginUsername = loginUsername;
		this.loginPassword = loginPassword;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = new Address(country,city,street);
	}




	public Notification getNotification() {
		return notification;
	}


	public void setNotification(Notification notification) {
		this.notification = notification;
	}





	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLoginUsername() {
		return loginUsername;
	}


	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}


	public String getLoginPassword() {
		return loginPassword;
	}


	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "User id=" + id + ", loginUsername=" + loginUsername + ", loginPassword=" + loginPassword + ", name="
				+ name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + "\n";
	}

}
