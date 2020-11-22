package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.CloseForm;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class AdminFormController {

	private static final Logger logger = LogManager.getLogger(AdminFormController.class);

	@FXML
	private Label welcomeUser;

	@FXML
	private AnchorPane workPane;

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
	private void controlClients(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ControlClientForm.fxml", workPane);
	}

	@FXML
	private void controlCouriers(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierControlForm.fxml", workPane);
	}

	@FXML
	private void controlCourierSystems(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ControlCompanyForm.fxml", workPane);
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
	private void logOut(ActionEvent event) throws IOException {
		CloseForm.closeForm(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
		logger.info("Administrator successfully logged out!");
	}

	public void setUser(User user) {
		welcomeUser.setText("Welcome " + user.getName());
		logger.info("Administrator successfully logged in!");
	}

}
