package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class ClientWorkspaceFormController implements Initializable {
	private UserService service = new UserServiceImpl();
	@FXML
	private AnchorPane workPane;
	
	 public void setUserID(int id)
	    {
		    this.id=id;
	    	welcomeUser.setText("Welcome "+ service.getUserName(id));
	    	
	    }

 @FXML
	private Label welcomeUser;
 
    private int id;
	
	@FXML
	private void requestShipment(ActionEvent event) throws IOException {
		FXMLLoader loader= OpenNewForm.openNewForm("RequestShipmentForm.fxml", "Create shipment");
		RequestShipmentFormController next = loader.getController();
		next.getCompanyForClient(id);
	}

	@FXML
	private void requestedShipment(ActionEvent event) throws IOException {
		FXMLLoader loader= BuiltInForm.built_inForm("RequestedShipmentsForm.fxml", workPane);
		RequestedShipmentsFormController next = loader.getController();
		next.setClient((Client)service.getUserByID(id));
		
		
	}

	@FXML
	private void clientShipments(ActionEvent event) throws IOException {
		FXMLLoader loader= BuiltInForm.built_inForm("ExpectedShipmentsForm.fxml", workPane);
		ExpectedShipmentsFormController next = loader.getController();
		next.setClient((Client)service.getUserByID(id));
		
	}

	@FXML
	private void viewProfil(ActionEvent event) throws IOException {
		FXMLLoader loader= BuiltInForm.built_inForm("ClientProfileForm.fxml", workPane);
		ClientProfileFormController next = loader.getController();
		next.setClientInformation((Client)service.getUserByID(id));
		
		
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	}

}
