package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class ClientWorkspaceFormController {

	@FXML
	private AnchorPane workPane;

	@FXML
	private void requestShipment(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("RequestShipmentForm.fxml", workPane);
	}

	@FXML
	private void requestedShipment(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("RequestedShipmentsForm.fxml", workPane);
	}

	@FXML
	private void clientShipments(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ExpectedShipmentsForm.fxml", workPane);
	}

	@FXML
	private void viewProfil(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ClientProfileForm.fxml", workPane);
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}
}
