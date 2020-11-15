package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Type.type;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class RequestShipmentFormController implements Initializable {

	private static final Logger LOGGER = LogManager.getLogger(RequestShipmentFormController.class);
	private final String SYSTEM_CLIENT = "1111111111";
	private UserService service = new UserServiceImpl();
	private User usr;
	@FXML
	private TextField phoneNmb;
	@FXML
	private TextField name;
	@FXML
	private TextField email;
	@FXML
	private TextField country;
	@FXML
	private TextField city;
	@FXML
	private TextField streetN;
	@FXML
	private TextField price;
	@FXML
	private TextField dueAmount;
	@FXML
	private Label company_name;
	@FXML
	private Label searchResultLabel;
	@FXML
	private Label phoneNvalidationLabel;
	@FXML
	private Label nameValidationLabel;
	@FXML
	private Label emailValidationLabel;
	@FXML
	private ToggleGroup expenseOf;
	@FXML
	private RadioButton radioSender;
	@FXML
	private RadioButton radioReceiver;
	@FXML
	private ToggleGroup sendTo;
	@FXML
	private RadioButton radioOffice;
	@FXML
	private RadioButton radioAddress;
	@FXML
	private Label countryValidationLabel;
	@FXML
	private Label cityValidationLabel;
	@FXML
	private Label streetValidationLabel;
	@FXML
	private Label companyComboValidationLabel;
	@FXML
	private Label typeComboValidationLabel;
	@FXML
	private Label officeComboValidationLabel;
	@FXML
	private Label priceValidationLabel;
	@FXML
	private Label expenseOfGroupValidationLabel;
	@FXML
	private Label sendToGroupValidationLabel;
	@FXML
	private Label resultLabel;
	@FXML
	private Button statedButton;
	@FXML
	private ComboBox<type> typeCombo;
	@FXML
	private ComboBox<String> officeCombo;
	@FXML
	private ComboBox<String> companyCombo;
	@FXML
	private DatePicker dateCreation;
	private int id;
	private boolean isCompanyComboDisabled;
	private ObservableList<type> typeList = FXCollections.observableArrayList(type.bag, type.cargo, type.document,
			type.packet, type.parcel);
	private ObservableList<String> companyList = FXCollections.observableArrayList();
	private ObservableList<String> officeList = FXCollections.observableArrayList();
	private int choosedShipmentID;

	public int getChoosedShipment() {
		return choosedShipmentID;
	}

	public void setChoosedShipment(int number) {
		this.choosedShipmentID = number;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dateCreation.setValue(LocalDate.now());
		this.typeCombo.setItems(typeList);

	}

	private boolean numberValidation() {
		return DataValidation.textNumeric(this.phoneNmb, this.phoneNvalidationLabel, "Wrong numeric format!");

	}

	@FXML
	private void nameValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(this.name, this.nameValidationLabel);

	}

	@FXML
	private void emailValidation(KeyEvent event) {
		FieldValidation.emailValidation(this.email, this.emailValidationLabel);

	}

	@FXML
	private void cityValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);

	}

	@FXML
	private void countryValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
	}

	@FXML
	private void streetNValidation(KeyEvent event) {
		FieldValidation.streetNValidation(this.streetN, this.streetValidationLabel);

	}

	@FXML
	private void priceValidation(KeyEvent event) {
		FieldValidation.priceValidation(this.price, this.priceValidationLabel);

	}

	@FXML
	public void loadClientInfo(ActionEvent event) {
		searchResultLabel.setText("");
		if (numberValidation()) {
			if (isUserFound()) {
				if (this.usr.getId() != this.id) {
					loadInfo(usr.getName(), usr.getEmail(), usr.getAddress().getCountry(), usr.getAddress().getCity(),
							usr.getAddress().getStreet());
					this.phoneNmb.setDisable(true);
					activateShipmentInfoFields();
				} else
					phoneNvalidationLabel.setText("The reciever and  the sender cannot have the same phone numbers !");
			} else {
				searchResultLabel.setText("No client with this phone number.");
				this.phoneNmb.setDisable(true);
				activatePersonalInfoFields();
				activateShipmentInfoFields();
				this.name.requestFocus();
			}
		}
	}

	@FXML
	private void statedShipment(ActionEvent event) {
		this.phoneNvalidationLabel.setText("");
		this.searchResultLabel.setText("");
		this.resultLabel.setText("");

		if (areAllFieldsFull() && isSelectedFromComboBoxes() && isSelectedFromRadioButtons()) {
			RadioButton selectedRadioButtonfromSendTo = (RadioButton) sendTo.getSelectedToggle();
			RadioButton selectedRadioButtonfromExpenseOf = (RadioButton) expenseOf.getSelectedToggle();
			String name = this.name.getText();
			String phoneNmb = this.phoneNmb.getText();
			String email = this.email.getText();
			String country = this.country.getText();
			String city = this.city.getText();
			String streetN = this.streetN.getText();
			Double price = Double.parseDouble(this.price.getText());
			String office;
			String company = this.companyCombo.getSelectionModel().getSelectedItem();
			type type = this.typeCombo.getSelectionModel().getSelectedItem();

			if (selectedRadioButtonfromSendTo.getText().equals("address")) {
				office = null;
			} else {
				office = this.officeCombo.getSelectionModel().getSelectedItem().toString();
			}
			if (selectedRadioButtonfromExpenseOf.getText().equals("receiver")) {
				dueAmount.setText("0.00");
				price = price + type.showPrice();
			} else {
				dueAmount.setText(Double.toString(type.showPrice()));
			}
			if (usr != null && office != null) {
				service.CreateShipment(type, dateCreation.getValue(), price, id, usr,
						service.getBulstatByFirmName(company), service.getIdByOfficeName(office));
			}
			if (usr != null && office == null) {
				service.CreateShipment(type, dateCreation.getValue(), price, id, usr,
						service.getBulstatByFirmName(company));
			}
			if (usr == null && office == null) {
				service.CreateClient(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				service.CreateShipment(type, dateCreation.getValue(), price, id, service.SearchUserByPhone(phoneNmb),
						service.getBulstatByFirmName(company));
			}
			if (usr == null && office != null) {
				service.CreateClient(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				service.CreateShipment(type, dateCreation.getValue(), price, id, service.SearchUserByPhone(phoneNmb),
						service.getBulstatByFirmName(company), service.getIdByOfficeName(office));
			}
			LOGGER.info("Client with id: " + id + " requested a new shipment!");
			resultLabel.setText("You requested shipment succsefully!");
			OpenNewForm.openNewForm("SuccessfulRequestForm.fxml", "Successful request");
		}
	}

	private boolean isSelectedFromComboBoxes() {
		this.typeComboValidationLabel.setText("");
		this.officeComboValidationLabel.setText("");
		this.companyComboValidationLabel.setText("");
		boolean isSelectedFromComboBoxes = true;
		boolean isCompanyComboEmpty = companyCombo.getSelectionModel().isEmpty();
		boolean isOfficeComboEmpty = officeCombo.getSelectionModel().isEmpty();
		boolean isTypeComboEmpty = typeCombo.getSelectionModel().isEmpty();
		if (isCompanyComboEmpty) {
			this.companyComboValidationLabel.setText("First select");
			isSelectedFromComboBoxes = false;
		}
		if (radioOffice.isSelected()) {
			if (isOfficeComboEmpty) {
				this.officeComboValidationLabel.setText("First select");
				isSelectedFromComboBoxes = false;
			}
		} else if (isTypeComboEmpty) {
			this.typeComboValidationLabel.setText("First select");
			isSelectedFromComboBoxes = false;
		}
		return isSelectedFromComboBoxes;
	}

	private boolean isSelectedFromRadioButtons() {

		this.expenseOfGroupValidationLabel.setText("");
		this.sendToGroupValidationLabel.setText("");
		boolean isCheckedFromExpenceofGroup = true;
		boolean isCheckedFromSendtoGroup = true;

		if ((RadioButton) expenseOf.getSelectedToggle() == null) {
			this.expenseOfGroupValidationLabel.setText("First select");
			isCheckedFromExpenceofGroup = false;

		}

		if ((RadioButton) sendTo.getSelectedToggle() == null) {
			this.sendToGroupValidationLabel.setText("First select");
			isCheckedFromSendtoGroup = false;
		}

		return (isCheckedFromExpenceofGroup && isCheckedFromSendtoGroup);

	}

	private boolean areAllFieldsFull() {
		boolean priceV = FieldValidation.priceValidation(this.price, this.priceValidationLabel);
		boolean nameV = FieldValidation.alphabetValidation(this.name, this.nameValidationLabel);
		boolean emailV = FieldValidation.emailValidation(this.email, this.emailValidationLabel);
		boolean countryV = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
		boolean cityV = FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);
		boolean numberV = numberValidation();
		boolean streetNV = FieldValidation.streetNValidation(this.streetN, this.streetValidationLabel);
		return priceV && nameV && emailV && countryV && cityV && streetNV && numberV;
	}

	private void activatePersonalInfoFields() {
		this.name.setDisable(false);
		this.email.setDisable(false);
		this.country.setDisable(false);
		this.city.setDisable(false);
		this.streetN.setDisable(false);

	}

	private void activateShipmentInfoFields() {
		this.companyCombo.setDisable(this.isCompanyComboDisabled);
		this.typeCombo.setDisable(false);
		this.radioAddress.setDisable(false);
		this.radioOffice.setDisable(false);
		this.radioSender.setDisable(false);
		this.radioReceiver.setDisable(false);
		this.price.setDisable(false);
		this.statedButton.setDisable(false);
	}

	private boolean isUserFound() {
		String clientID = this.phoneNmb.getText();
		this.usr = service.SearchUserByPhone(clientID);
		return (this.usr != null);
	}

	public void loadInfo(String name, String email, String country, String city, String streetN) {
		this.name.setText(name);
		this.email.setText(email);
		this.country.setText(country);
		this.city.setText(city);
		this.streetN.setText(streetN);
	}

	public void fillOfficeCombo(String officeName) {
		officeList.add(officeName);
	}

	public void fillCompanyCombo(String companyName) {
		companyList.add(companyName);
	}

	@FXML
	private void activateOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(false);
		officeCombo.getItems().clear();
		List<String> list = service.getOfficesList(service.getBulstatByFirmName(companyCombo.getValue()));
		for (String column : list) {
			fillOfficeCombo(column);
		}
		officeCombo.setItems(officeList);
	}

	@FXML
	private void disabledOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(true);
	}

	public void getCompanyForAdmin(Company choosedCompany) {
		companyList.add(choosedCompany.getCompanyName());
		company_name.setText(choosedCompany.getCompanyName());
		companyCombo.setItems(companyList);
		companyCombo.getSelectionModel().select(choosedCompany.getCompanyName());
		this.isCompanyComboDisabled = true;
		this.id = service.SearchUserByPhone(SYSTEM_CLIENT).getId();

	}

	public void getCompanyForClient(int id) {
		List<Object[]> list = service.getAllCompanies();
		for (Object[] column : list) {
			companyList.add((String) column[0]);
		}
		companyCombo.setItems(companyList);
		this.isCompanyComboDisabled = false;
		companyCombo.getSelectionModel().selectFirst();
		this.id = id;

	}

}
