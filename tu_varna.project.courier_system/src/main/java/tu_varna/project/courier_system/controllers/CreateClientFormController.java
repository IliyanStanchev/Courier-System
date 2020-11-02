package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateClientFormController implements Initializable {

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
		String confirmPW = this.confirmPW.getText();
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		String email = this.email.getText();
		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		System.out.println(password);

		// dbcreateClient(username, password, confirmPW, name, phoneNmb, email,
		// country, city, streetN);
		// ako se dobawi uspeshno resultLabel = "Uspeshno!" ako ne - neuspeshno
		// suzdawane"
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}