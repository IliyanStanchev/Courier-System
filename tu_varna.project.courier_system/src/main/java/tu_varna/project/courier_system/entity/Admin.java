package tu_varna.project.courier_system.entity;

import javax.persistence.Entity;

import javafx.fxml.FXMLLoader;
import tu_varna.project.courier_system.controllers.AdminFormController;


@Entity
public class Admin extends User {

	public Admin() {
		
	}
	
	public Admin(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street) {
		super(loginUsername, loginPassword, name, email, phoneNumber, country, city, street);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String loadView() {
		return "AdministratorWorkspaceForm.fxml";
		
	}

	@Override
	public void loadController(FXMLLoader loader) {
		AdminFormController next=loader.getController();
		next.setUserID(this.getId());
		
	}
}
