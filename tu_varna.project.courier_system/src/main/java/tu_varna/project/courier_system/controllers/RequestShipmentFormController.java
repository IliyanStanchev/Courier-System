package tu_varna.project.courier_system.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.helper.FieldValidation;

public class RequestShipmentFormController implements Initializable {

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
	private Label fieldsValidationLabel;
	@FXML
	private ComboBox<String> typeCombo;

	@FXML
	private ComboBox<String> officeCombo;

	@FXML
	private ComboBox<String> companyCombo;
	@FXML
	private DatePicker dateCreation;

	private ObservableList<String> typeList = FXCollections.observableArrayList("bag", "packet", "parcel", "cargo",
			"document");
	private ObservableList<String> companyList = FXCollections.observableArrayList();
	private ObservableList<String> officeList = FXCollections.observableArrayList();

	private int choosedShipmentID;

	public int getChoosedShipment() {
		return choosedShipmentID;
	}

	public void setChoosedShipment(int number) {
		this.choosedShipmentID = number;
		System.out.println(choosedShipmentID);
	}

	private boolean numberValidation() {
		return FieldValidation.numberValidation(phoneNmb, phoneNvalidationLabel);

	}

	@FXML
	private void nameValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(name, nameValidationLabel);

	}

	@FXML
	private void emailValidation(KeyEvent event) {
		FieldValidation.emailValidation(email, emailValidationLabel);

	}

	@FXML
	private void cityValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(city, cityValidationLabel);

	}

	@FXML
	private void countryValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(country, countryValidationLabel);
	}

	@FXML
	private void streetNValidation(KeyEvent event) {
		FieldValidation.alphabetValidation(streetN, streetValidationLabel);

	}

	@FXML
	private void priceValidation(KeyEvent event) {
		FieldValidation.priceValidation(price, priceValidationLabel);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		dateCreation.setValue(LocalDate.now());
		typeCombo.setItems(typeList);
		// DBfillCompanyCombo polzwa fillCompanyCombo();
		// DBfillOfficCombo polzwa fillOfficeCombo();

	}

	@FXML
	public void loadClientInfo(ActionEvent event) {

		searchResultLabel.setText("");
		if (numberValidation()) {

			String clientID = phoneNmb.getText();
			System.out.println(clientID);
			// if(DBsearchCLient(clientID)==false){
			searchResultLabel.setText("No client with this phone number.");
			name.requestFocus();
		}

		// -->a ako go nameri shte izvika v sebe si loadInfo();
		// else searchResultLabel.setText("Found in the client list.");
	}

	@FXML
	private void statedShipment(ActionEvent event) {
		resultLabel.setText("");
		RadioButton selectedRadioButtonfromSendTo = (RadioButton) sendTo.getSelectedToggle();

		RadioButton selectedRadioButtonfromExpenseOf = (RadioButton) expenseOf.getSelectedToggle();
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		String email = this.email.getText();
		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		Double price = Double.parseDouble(this.price.getText());
		String company = this.companyCombo.getSelectionModel().getSelectedItem();
		// String sendTo = selectedRadioButtonfromSendTo.getText();
		// String expenseOf = selectedRadioButtonfromExpenseOf.getText();
		String office = this.officeCombo.getSelectionModel().getSelectedItem().toString();
		String type = this.typeCombo.getSelectionModel().getSelectedItem();
		System.out.println(sendTo);

		boolean combo = checkComboBoxes(); // vrushta false ako ima neizbrano
		boolean radio = checkRadioButtons(); // vrushta false ako ima necheknato
		boolean field = checkFields(); // vrushta true ako ima prazno pole
		if (combo && radio && !field) { // tuka ne e taka
			resultLabel.setText("ffffff");
			// GBFunckiq ( wsichki poleta da se prashtat);
		} else
			resultLabel.setText("Fill in all fields correct.");

	}

	@FXML
	private void activateOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(false);
	}

	@FXML
	private void disabledOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(true);
	}

	private boolean checkComboBoxes() {
		this.typeComboValidationLabel.setText("");
		this.officeComboValidationLabel.setText("");
		this.companyComboValidationLabel.setText("");
		boolean isSelectedFromComboBoxes = true;
		boolean isCompanyComboEmpty = (companyCombo.getValue() == null); // getSelectionModel().isEmpty();
		boolean isOfficeComboEmpty = (officeCombo.getValue() == null); // getSelectionModel().isEmpty();
		boolean isTypeComboEmpty = (typeCombo.getValue() == null); // getSelectionModel().isEmpty();
		if (isCompanyComboEmpty) {
			this.companyComboValidationLabel.setText("First select");
			isSelectedFromComboBoxes = false;
		}
		if (radioOffice.isSelected()) {
			if (isOfficeComboEmpty) {
				this.officeComboValidationLabel.setText("First select");
				isSelectedFromComboBoxes = false;
			}
		}
		if (isTypeComboEmpty) {
			this.typeComboValidationLabel.setText("First select");
			isSelectedFromComboBoxes = false;
		}
		return isSelectedFromComboBoxes;
	}

	private boolean checkRadioButtons() {

		this.expenseOfGroupValidationLabel.setText("");
		this.sendToGroupValidationLabel.setText("");
		boolean isCheckedFromExpenceofGroup = true;
		boolean isCheckedFromSendtoGRoup = true;
		boolean isSenderRadioChecked = radioSender.isSelected();
		boolean isReceiverRadioChecked = radioReceiver.isSelected();
		boolean isAddressRadioChecked = radioAddress.isSelected();
		boolean isOfficeRadioChecked = radioOffice.isSelected();

		if (!isSenderRadioChecked && !isReceiverRadioChecked) {
			this.expenseOfGroupValidationLabel.setText("First select");
			isCheckedFromExpenceofGroup = false;
		}
		if (!isAddressRadioChecked && !isOfficeRadioChecked) {
			this.sendToGroupValidationLabel.setText("First select");
			isCheckedFromSendtoGRoup = false;
		}
		return (isCheckedFromExpenceofGroup && isCheckedFromSendtoGRoup);

	}

	public void loadInfo(String name, String email, String country, String city, String streetN) {
		this.name.setText(name);
		this.email.setText(email);
		this.country.setText(country);
		this.city.setText(city);
		this.streetN.setText(streetN); // zarejda poletata ako e otkrit client;
	}

	private boolean checkFields() {
		return (name.getText().isEmpty() && phoneNmb.getText().isEmpty() && email.getText().isEmpty()
				&& country.getText().isEmpty() && city.getText().isEmpty() && streetN.getText().isEmpty()
				&& price.getText().isEmpty());
	}

	public void fillOfficeCombo(String officeName) {
		officeList.add(officeName); // pulni kombooffice trqbwa da se ivika v init;
	}

	public void fillCompanyCombo(String companyName) { // pulni kombocompany w init pak;
		companyList.add(companyName);
	}

}
