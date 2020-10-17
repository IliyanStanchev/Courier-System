package tu_varna.project.courier_system.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import tu_varna.project.courier_system.embeddable.Address;

@Entity
@Inheritance(strategy=  InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "user_type")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    
    private String loginUsername;
    
   
    private String loginPassword;
    
   
    private String name;
    
   
    private String email;
    
 
    private String phoneNumber;
    
    private Address address;


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
		return "User [id=" + id + ", loginUsername=" + loginUsername + ", loginPassword=" + loginPassword + ", name="
				+ name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}


	
    
   
  

	
    

}
