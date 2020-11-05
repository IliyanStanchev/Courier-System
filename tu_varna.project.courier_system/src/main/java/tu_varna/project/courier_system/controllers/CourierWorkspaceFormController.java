package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CourierWorkspaceFormController implements Initializable {
	private UserService service = new UserServiceImpl();

	private int id;
	
	@FXML
	private Label welcomeUser;
	
	public void setUserID(int id)
    {
    	this.id=id;
    	welcomeUser.setText("Welcome "+ service.getUserName(id));
    }
	
	@FXML
	private Label label;
	

	@FXML
	private AnchorPane workPane;

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void loadHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader= BuiltInForm.built_inForm("CourierHomeForm.fxml", workPane);
		CourierHomeFormController next = loader.getController();
		next.setCourierInformation(id);
		
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

	@FXML
	private void viewProfile(ActionEvent event) throws IOException {
		FXMLLoader loader= BuiltInForm.built_inForm("CourierProfileForm.fxml", workPane);
		CourierProfileFormController next = loader.getController();
		next.setCourier((Courier)service.getUserByID(id));
		
	}

}
