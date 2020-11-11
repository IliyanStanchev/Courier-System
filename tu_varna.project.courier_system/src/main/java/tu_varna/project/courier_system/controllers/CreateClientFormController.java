package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class CreateClientFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CreateClientFormController.class);

	private UserService service = new UserServiceImpl();

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private PasswordField confirmPW;

	@FXML
	private TextField name;

	@FXML
	private TextField email;

	@FXML
	private TextField phoneNmb;

	@FXML
	private TextField country;

	@FXML
	private TextField city;

	@FXML
	private TextField streetN;

	@FXML
	private Label passwordValidationLabel;

	@FXML
	private Label usernameValidationLabel;

	@FXML
	private Label confirmPWValidationLabel;

	@FXML
	private Label nameValidationLabel;

	@FXML
	private Label emailValidationLabel;

	@FXML
	private Label phoneNValidationLabel;

	@FXML
	private Label countryValidationLabel;

	@FXML
	private Label cityValidationLabel;

	@FXML
	private Label streetNValidationLabel;

	@FXML
	private Label resultLabel;

	@FXML
	public void createClient(ActionEvent event) {
		String username = this.username.getText();
		String password = this.password.getText();
		// String confirmPW = this.confirmPW.getText();
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		String email = this.email.getText();
		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		System.out.println(password);

		boolean check = service.CreateClient(username, password, name, email, phoneNmb, country, city, streetN);
		if (check) {
			resultLabel.setText("Client created succesfully!");
			logger.info("Client [ " + username + " , " + password + " ] successfully created by administrator! ");

		} else {
			resultLabel.setText("Error!Username or phone number already exist!");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}