package tu_varna.project.courier_system.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.impl.UserServiceImpl;

public class CourierProfileFormController
{

	private static final Logger logger = LogManager.getLogger(CourierProfileFormController.class);

	UserService userService = new UserServiceImpl();
	private Courier courier;

	@FXML
	private Label company;
	@FXML
	private Label name;
	@FXML
	private PasswordField password;
	@FXML
	private TextField email;
	@FXML
	private TextField phoneN;
	@FXML
	private TextField streetN;
	@FXML
	private TextField city;
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
	private Label phoneNValidationLabel;
	@FXML
	private Label emailValidationLabel;
	@FXML
	private Label passwordValidationLabel;
	@FXML
	private Label countryValidationLabel;
	@FXML
	private Label cityValidationLabel;
	@FXML
	private Label streetNValidationLabel;

	@FXML
	private void editAddress(ActionEvent event)
	{
		this.country.setDisable(false);
		this.city.setDisable(false);
		this.streetN.setDisable(false);
	}

	@FXML
	private void editEmail(ActionEvent event)
	{
		this.email.setDisable(false);
	}

	@FXML
	private void editPassword(ActionEvent event)
	{
		this.password.setDisable(false);

	}

	@FXML
	private void editPhoneN(ActionEvent event)
	{
		this.phoneN.setDisable(false);
	}

	@FXML
	private void emailValidation(KeyEvent event)
	{
		this.emailSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.emailValidation(this.email, this.emailValidationLabel);
		if (isCorrect)
			this.emailSaveB.setDisable(false);

	}

	@FXML
	private void passwordValidation(KeyEvent event)
	{
		this.passwordSaveB.setDisable(true);
		boolean isEmpty = DataValidation.textFieldisEmpty(this.password, this.passwordValidationLabel,
				"The field is empty.");
		if (!isEmpty)
			this.passwordSaveB.setDisable(false);
	}

	@FXML
	private void phoneNmbValidation(KeyEvent event)
	{
		this.phoneNSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.numberValidation(this.phoneN, this.phoneNValidationLabel);
		if (isCorrect)
			this.phoneNSaveB.setDisable(false);
	}

	@FXML
	private void streetNValidation(KeyEvent event)
	{
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.streetNValidation(this.streetN, this.streetNValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);

	}

	@FXML
	private void cityValidation(KeyEvent event)
	{
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);

	}

	@FXML
	private void countryValidation(KeyEvent event)
	{
		this.addressSaveB.setDisable(true);
		boolean isCorrect = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
		if (isCorrect)
			this.addressSaveB.setDisable(false);
	}

	@FXML
	private void saveAddress(ActionEvent event)
	{

		userService.changeUserAddress(courier, country.getText(), city.getText(), streetN.getText());
		logger.info("Courier with id: " + courier.getId() + " updated his address!");
		this.country.setDisable(true);
		this.city.setDisable(true);
		this.streetN.setDisable(true);
		this.addressSaveB.setDisable(true);
	}

	@FXML
	private void saveEmail(ActionEvent event)
	{

		userService.changeUserEmail(courier, email.getText());
		logger.info("Courier with id: " + courier.getId() + " updated his email!");
		this.email.setDisable(true);
		this.emailSaveB.setDisable(true);

	}

	@FXML
	private void savePassword(ActionEvent event)
	{

		userService.changeUserPassword(courier, password.getText());
		logger.info("Courier with id: " + courier.getId() + " updated his password!");
		this.password.setDisable(true);
		this.passwordSaveB.setDisable(true);
	}

	@FXML
	private void savePhoneN(ActionEvent event)
	{

		userService.changeUserPhone(courier, phoneN.getText());
		logger.info("Courier with id: " + courier.getId() + " updated his phone number!");
		this.phoneN.setDisable(true);
		this.phoneNSaveB.setDisable(true);

	}

	public void setCourier(Courier courier)
	{
		this.courier = courier;
		name.setText(courier.getName());
		company.setText(courier.getFirm().getCompanyName());
		password.setText(courier.getLoginPassword());
		email.setText(courier.getEmail());
		phoneN.setText(courier.getPhoneNumber());
		country.setText(courier.getAddress().getCountry());
		city.setText(courier.getAddress().getCity());
		streetN.setText(courier.getAddress().getStreet());

	}

}
