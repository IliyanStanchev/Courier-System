package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class CreateCourierFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CreateCourierFormController.class);

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
	private ComboBox<String> companyCombo;
	@FXML
	private Label comboValidationLabel;
	@FXML
	private Label resultLabel;
	private ObservableList<String> companyList = FXCollections.observableArrayList();
	private boolean countryV;
	private boolean passwordV;
	private boolean cityV;
	private boolean numberV;
	private boolean streetV;
	private boolean nameV;
	private boolean emailV;
	private boolean usernameV;
	private boolean confirmPV;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		List<Object[]> list = service.getAllCompanies();
		for (Object[] column : list) {
			fillCompanyCombo((String) column[0]);
		}
		companyCombo.setItems(companyList);

	}

	@FXML
	private void confirmPasswordValidation(KeyEvent event) {
		this.confirmPWValidationLabel.setText("");
		confirmPV = this.password.getText().equals(this.confirmPW.getText());
		if (confirmPV == false) {
			this.confirmPWValidationLabel.setText("The passwords do not match");
		}

	}

	@FXML
	private void countryValidation(KeyEvent event) {
		countryV = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
	}

	@FXML
	void cityValidation(KeyEvent event) {
		cityV = FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);
	}

	@FXML
	private void streetNValidation(KeyEvent event) {
		streetV = FieldValidation.streetNValidation(this.streetN, this.streetNValidationLabel);
	}

	@FXML
	private void emailValidation(KeyEvent event) {
		emailV = FieldValidation.emailValidation(this.email, this.emailValidationLabel);
	}

	@FXML
	private void nameValidation(KeyEvent event) {
		nameV = FieldValidation.alphabetValidation(this.name, this.nameValidationLabel);
	}

	@FXML
	private void passwordValidation(KeyEvent event) {
		passwordV = FieldValidation.passwordLength(this.password, this.passwordValidationLabel);
	}

	@FXML
	private void phoneNmbValidation(KeyEvent event) {
		numberV = FieldValidation.numberValidation(this.phoneNmb, this.phoneNValidationLabel);
	}

	@FXML
	private void usernameValidation(KeyEvent event) {
		usernameV = FieldValidation.usernameValidation(this.username, this.usernameValidationLabel);
	}

	@FXML
	private void createCourier(ActionEvent event) {
		this.resultLabel.setText("");
		this.comboValidationLabel.setText("");
		if (areAllFieldsFull() && isSelectedFromComboBox()) {
			String username = this.username.getText();
			String password = this.password.getText();
			String name = this.name.getText();
			String phoneNmb = this.phoneNmb.getText();
			String email = this.email.getText();
			String country = this.country.getText();
			String city = this.city.getText();
			String streetN = this.streetN.getText();
			String company = this.companyCombo.getSelectionModel().getSelectedItem().toString();
			boolean check = service.CreateCourier(username, password, name, phoneNmb, email, country, city, streetN,
					service.getBulstatByFirmName(company));
			if (check) {
				this.resultLabel.setText("Courier created  succesfully !");
				logger.info("Courier [ " + username + " , " + password + " ] successfully created by administrator! ");

			} else
				this.resultLabel.setText("Error! Courier username or phone are already taken!");
		}
	}

	private boolean areAllFieldsFull() {
		boolean areCorrect = passwordV && confirmPV && usernameV && streetV && countryV && cityV && emailV && nameV
				&& numberV;
		if (!areCorrect) {
			this.resultLabel.setText("Fill in all fields!");
		}
		return areCorrect;
	}

	private boolean isSelectedFromComboBox() {
		boolean isEmpty = this.companyCombo.getSelectionModel().isEmpty();

		if (isEmpty == true) {
			this.comboValidationLabel.setText("First select");
		}
		return !isEmpty;

	}

	private void fillCompanyCombo(String companyName) {
		companyList.add(companyName);
	}

}
