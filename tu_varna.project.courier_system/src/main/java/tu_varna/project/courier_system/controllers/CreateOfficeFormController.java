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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class CreateOfficeFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CreateOfficeFormController.class);

	UserService service = new UserServiceImpl();
	@FXML
	private TextField country;
	@FXML
	private TextField city;
	@FXML
	private TextField streetN;
	@FXML
	private TextField agent;
	@FXML
	private Label resultLabel;
	@FXML
	private TextField phoneNmb;
	@FXML
	private Label phoneNmbValidationLabel;
	@FXML
	private ComboBox<String> companyCombo;
	@FXML
	private Label comboLabel;
	@FXML
	private Label agentValidationLabel;
	@FXML
	private Label streetNValidationLabel;
	@FXML
	private Label countryValidationLabel;
	@FXML
	private Label cityValidationLabel;
	private ObservableList<String> companyList = FXCollections.observableArrayList();
	private boolean countryV;
	private boolean cityV;
	private boolean streetV;
	private boolean agentV;
	private boolean numberV;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<Object[]> list = service.getAllCompanies();
		for (Object[] column : list) {
			fillCompanyCombo((String) column[0]);
		}
		companyCombo.setItems(companyList);
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
	private void agentValidation(KeyEvent event) {
		agentV = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
	}

	@FXML
	private void phoneNValidation(KeyEvent event) {
		numberV = FieldValidation.numberValidation(this.phoneNmb, this.phoneNmbValidationLabel);
	}

	@FXML
	void createOffice(ActionEvent event) {
		resultLabel.setText("");
		this.comboLabel.setText("");
		if (areAllFieldsFull() && isSelectedFromComboBox()) {
			String country = this.country.getText();
			String city = this.city.getText();
			String streetN = this.streetN.getText();
			String agent = this.agent.getText();
			String phoneNmb = this.phoneNmb.getText();
			String company = this.companyCombo.getSelectionModel().getSelectedItem().toString();
			boolean check = service.CreateOffice(company, country, city, streetN, agent, phoneNmb,
					service.getBulstatByFirmName(company));
			if (check) {
				resultLabel.setText("Courier succesfully created!");
				logger.info("Office [ " + company + " , " + streetN + " ] successfully created by administrator! ");
			} else {
				resultLabel.setText("Error! Office cannot be created!");
			}
		}
	}

	private boolean areAllFieldsFull() {
		boolean areCorrect = streetV && countryV && cityV && numberV && agentV;
		if (!areCorrect) {
			this.resultLabel.setText("Fill in all fields!");
		}
		return areCorrect;
	}

	private boolean isSelectedFromComboBox() {
		boolean isEmpty = this.companyCombo.getSelectionModel().isEmpty();
		if (isEmpty == true) {
			this.comboLabel.setText("First select");
		}
		return !isEmpty;

	}

	public void fillCompanyCombo(String companyName) {
		companyList.add(companyName);
	}

}
