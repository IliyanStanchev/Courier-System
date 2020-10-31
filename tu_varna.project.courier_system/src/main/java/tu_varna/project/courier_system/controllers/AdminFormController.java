package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class AdminFormController implements Initializable {

	@FXML
	private AnchorPane workPane;

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void controlClients(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("Create_DeleteClientForm.fxml", workPane);
	}

	@FXML
	private void controlCouriers(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierControlForm.fxml", workPane);
	}

	@FXML
	private void controlCourierSystems(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("Create_DeleteCompanyForm.fxml", workPane);
	}

	@FXML
	private void controlOffices(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("OfficesControlForm.fxml", workPane);
	}

	@FXML
	private void controlShipments(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ShipmentControlForm.fxml", workPane);
	}

	@FXML
	private void aboutCourier(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutCourierForm.fxml", workPane);
	}

	@FXML
	private void aboutCourierCompany(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutCourierCompanyForm.fxml", workPane);
	}

	@FXML
	private void aboutShipment(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutShipmentForm.fxml", workPane);
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}

}
