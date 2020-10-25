package tu_varna.project.courier_system.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.LogOut;

public class ClientWorkspaceFormController {

	@FXML
	private AnchorPane workPane;

	@FXML
	private void clientShipments(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ClientShipmentsForm.fxml", workPane);
	}

	
	@FXML
	private void viewProfil(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ClientProfilForm.fxml", workPane);
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
	}
}
