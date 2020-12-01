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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Type.type;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.DataValidation;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;
import tu_varna.project.courier_system.services.impl.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.OfficeView;

public class RequestShipmentFormController implements Initializable {

	private static final Logger LOGGER = LogManager.getLogger(RequestShipmentFormController.class);
	private final String SYSTEM_CLIENT = "1111111111";

	private UserService userService = new UserServiceImpl();
	private CompanyService companyService = new CompanyServiceImpl();
	private ShipmentService shipmentService = new ShipmentServiceImpl();

	private User sender;
	private User receiver;
	private boolean isCompanyComboDisabled;

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
	private ComboBox<OfficeView> officeCombo;
	@FXML
	private ComboBox<CompanyView> companyCombo;
	@FXML
	private DatePicker dateCreation;

	private ObservableList<type> typeList = FXCollections.observableArrayList(type.bag, type.cargo, type.document,
			type.packet, type.parcel);
	private ObservableList<CompanyView> companyList = FXCollections.observableArrayList();
	private ObservableList<OfficeView> officeList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.dateCreation.setValue(LocalDate.now());
		this.typeCombo.setItems(typeList);
		this.typeCombo.getSelectionModel().select(0);

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
	private void emailValidation(KeyEvent event) {
		FieldValidation.emailValidation(this.email, this.emailValidationLabel);

	}

	@FXML
	private void nameValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(this.name, this.nameValidationLabel);

	}

	@FXML
	private void priceValidation(KeyEvent event) {
		FieldValidation.priceValidation(this.price, this.priceValidationLabel);

	}

	@FXML
	private void streetNValidation(KeyEvent event) {
		FieldValidation.streetNValidation(this.streetN, this.streetValidationLabel);

	}

	@FXML
	private void disabledOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(true);
	}

	@FXML
	private void activateOfficeCombo(ActionEvent event) {

		officeCombo.setDisable(false);
		officeCombo.getItems().clear();

		List<OfficeView> list = companyService.getOfficesList(companyCombo.getValue().getBulstat());
		for (OfficeView column : list) {
			fillOfficeCombo(column);
		}

		officeCombo.setItems(officeList);
	}

	@FXML
	public void loadClientInfo(ActionEvent event) {
		searchResultLabel.setText("");
		if (numberValidation()) {
			if (isUserFound()) {
				if (this.receiver.getId() != this.sender.getId()) {
					loadInfo(receiver.getName(), receiver.getEmail(), receiver.getAddress().getCountry(),
							receiver.getAddress().getCity(), receiver.getAddress().getStreet());
					this.phoneNmb.setDisable(true);
					activateShipmentInfoFields();
				} else
					phoneNvalidationLabel.setText("The receiver and  the sender cannot have the same phone numbers !");
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
			OfficeView officeView;
			CompanyView companyView = this.companyCombo.getSelectionModel().getSelectedItem();
			Company company = companyService.getCompanyByID(companyView.getBulstat());
			type type = this.typeCombo.getSelectionModel().getSelectedItem();
			Shipment shipment = null;
			Office office = null;

			if (selectedRadioButtonfromSendTo.getText().equals("address")) {
				officeView = null;
			} else {
				officeView = this.officeCombo.getSelectionModel().getSelectedItem();
				office = companyService.getOfficeById(officeView.getCode());
			}
			if (selectedRadioButtonfromExpenseOf.getText().equals("receiver")) {
				dueAmount.setText("0.00");
				price = price + type.showPrice();
			} else {
				dueAmount.setText(Double.toString(type.showPrice()));
			}
			if (receiver != null && officeView != null) {

				shipment = new Shipment(type, dateCreation.getValue(), price, sender, receiver, company, office);
				shipmentService.createShipment(shipment);
			}
			if (receiver != null && officeView == null) {
				shipment = new Shipment(type, dateCreation.getValue(), price, sender, receiver, company);
				shipmentService.createShipment(shipment);
			}
			if (receiver == null && officeView != null) {
				receiver = new Client(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				userService.createClient((Client) receiver);
				shipment = new Shipment(type, dateCreation.getValue(), price, sender, receiver, company, office);
				shipmentService.createShipment(shipment);
			}
			if (receiver == null && officeView == null) {
				receiver = new Client(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				userService.createClient((Client) receiver);
				shipment = new Shipment(type, dateCreation.getValue(), price, sender, receiver, company);
				shipmentService.createShipment(shipment);
			}

			LOGGER.info("Client with id: " + sender.getId() + " requested a new shipment!");
			resultLabel.setText("You requested shipment succsefully!");
			FXMLLoader loader = OpenNewForm.openNewForm("BillOfLoadingForm.fxml", "Successful request");
			BillOfLoadingFormController next = loader.getController();
			next.setCreatedShipment(shipment);
		}
	}

	private boolean numberValidation() {
		return DataValidation.textNumeric(this.phoneNmb, this.phoneNvalidationLabel, "Wrong numeric format!");

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

	public void loadInfo(String name, String email, String country, String city, String streetN) {
		this.name.setText(name);
		this.email.setText(email);
		this.country.setText(country);
		this.city.setText(city);
		this.streetN.setText(streetN);
	}

	private boolean isUserFound() {
		String clientID = this.phoneNmb.getText();
		this.receiver = userService.getUserByPhone(clientID);
		return (this.receiver != null);
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

	public void fillCompanyCombo(CompanyView company) {
		companyList.add(company);
	}

	public void fillOfficeCombo(OfficeView office) {
		officeList.add(office);
	}

	public void getCompanyForAdmin(Company choosedCompany) {
		CompanyView company = new CompanyView(choosedCompany.getId(), choosedCompany.getCompanyName());
		companyList.add(company);
		company_name.setText(choosedCompany.getCompanyName());
		companyCombo.setItems(companyList);
		companyCombo.getSelectionModel().select(company);
		this.isCompanyComboDisabled = true;
		this.sender = userService.getUserByPhone(SYSTEM_CLIENT);

	}

	public void getCompanyForClient(User sender) {

		List<CompanyView> list = companyService.getAllCompanies();
		for (CompanyView company : list) {
			companyList.add(company);
		}
		companyCombo.setItems(companyList);
		this.isCompanyComboDisabled = false;
		companyCombo.getSelectionModel().selectFirst();
		this.sender = sender;

	}

}
