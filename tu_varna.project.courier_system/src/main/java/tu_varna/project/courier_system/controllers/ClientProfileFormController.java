package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class ClientProfileFormController implements Initializable {
	
	UserService service= new UserServiceImpl();
	
	private Client client;
	

	@FXML
	private PasswordField password;

	@FXML
	private TextField email;

	@FXML
	private TextField phoneN;

	@FXML
	private TextField city;

	@FXML
	private Label name;

	@FXML
	private TextField streetN;

	@FXML
	private TextField country;
	@FXML
	private Button passwordSaveB;

	@FXML
	private Button emailSaveB;

	@FXML
	private Button phoneNSaveB;

	@FXML
	private Button addressSaveB;

	@FXML
	private Label username;

	@FXML
	private Label passwordValidationLabel;

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
	private void editAddress(ActionEvent event) {
		this.country.setDisable(false);
		this.city.setDisable(false);
		this.streetN.setDisable(false);
	}

	@FXML
	private void editEmail(ActionEvent event) {
		this.email.setDisable(false);
	}

	@FXML
	private void editPassword(ActionEvent event) {
		this.password.setDisable(false);
	}

	@FXML
	private void editPhoneN(ActionEvent event) {
		this.phoneN.setDisable(false);
	}

	@FXML
	private void saveAddress(ActionEvent event) {

		service.ChangeUserAddress(client,country.getText(),city.getText(),streetN.getText());
		this.country.setDisable(true);
		this.city.setDisable(true);
		this.streetN.setDisable(true);
		this.addressSaveB.setDisable(true);
	}

	@FXML
	private void saveEmail(ActionEvent event) {
		service.ChangeUserEmail(client,email.getText());
		this.email.setDisable(true);
		this.emailSaveB.setDisable(true);
	}

	@FXML
	private void savePassword(ActionEvent event) {
		service.ChangeUserPassword(client,password.getText());
		this.password.setDisable(true);
		this.passwordSaveB.setDisable(true);
	}

	@FXML
	private void savePhoneN(ActionEvent event) {
		service.ChangeUserPhone(client,phoneN.getText());
		this.phoneN.setDisable(true);
		this.phoneNSaveB.setDisable(true);
	}

	@FXML
	private void passwordValidation(KeyEvent event) {
		this.passwordSaveB.setDisable(true);
		boolean isEmpty = DataValidation.textFieldisEmpty(this.password, this.passwordValidationLabel,
				"The field is empty.");
		if (!isEmpty)
			this.passwordSaveB.setDisable(false);
	}

	@FXML
	private void emailValidation(KeyEvent event) {
		this.emailSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.emailValidation(this.email, this.emailValidationLabel);
		if (isCorrect)
			this.emailSaveB.setDisable(false);

	}

	@FXML
	private void phoneNmbValidation(KeyEvent event) {
		this.phoneNSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.numberValidation(this.phoneN, this.phoneNValidationLabel);
		if (isCorrect)
			this.phoneNSaveB.setDisable(false);
	}

	@FXML
	private void countryValidation(KeyEvent event) {
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);
	}

	@FXML
	private void cityValidation(KeyEvent event) {
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);

	}

	@FXML
	private void streetNValidation(KeyEvent event) {
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.streetNValidation(this.streetN, this.streetNValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// name.setText(DBGetUserName());
		// username.setText(DBGetUserUsername());
		// password.setText(DBGetUSerPassword());
		// email.setText(DBGetUserEmail());
		// phoneN.setText(DBGetUSerPhoneN());
		// country.setText(DBGetUserCountry());
		// city.
		// streetN.

	}
	
	public void setClientInformation(Client client)
	{
		         this.client=client;
		         name.setText(client.getName());
				 username.setText(client.getLoginUsername());
				 password.setText(client.getLoginPassword());
				 email.setText(client.getEmail());
				 phoneN.setText(client.getPhoneNumber());
				 country.setText(client.getAddress().getCountry());
				 city.setText(client.getAddress().getCity());
				 streetN.setText(client.getAddress().getStreet());
    }

	
}
