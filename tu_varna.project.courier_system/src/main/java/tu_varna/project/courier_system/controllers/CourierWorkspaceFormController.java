package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.helper.BuiltInForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class CourierWorkspaceFormController implements Initializable {

	@FXML
	private AnchorPane workPane;

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void loadHomePage(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierHomeForm.fxml", workPane);
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

	@FXML
	private void viewProfile(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierProfileForm.fxml", workPane);
	}

}
