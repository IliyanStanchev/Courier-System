package tu_varna.project.courier_system.entity;

import javax.persistence.Entity;



@Entity
public class Admin extends User {

	
	@Override
	public String loadView() {
		return "AdministratorWorkspaceForm.fxml";
		
	}
}
