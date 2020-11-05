package tu_varna.project.courier_system.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Manager;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.Type.type;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class RequestShipmentFormController implements Initializable {
	
	private UserService service= new UserServiceImpl();
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
	private Label fieldsValidationLabel;
	@FXML
	private ComboBox<type> typeCombo;

	@FXML
	private ComboBox<String> officeCombo;

	@FXML
	private ComboBox<String> companyCombo;
	@FXML
	private DatePicker dateCreation;
	
	private int id;
	

	private ObservableList<type> typeList = FXCollections.observableArrayList(type.bag,type.cargo,type.document,type.packet,type.parcel);
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
			this.usr=service.SearchUserByPhone(clientID);
			if(usr!=null)
			{
				loadInfo(usr.getName(), usr.getEmail(), usr.getAddress().getCountry(), usr.getAddress().getCity(), usr.getAddress().getStreet());
				
			}
			else
			{
				searchResultLabel.setText("No client with this phone number.");
				name.requestFocus();
			}
			
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
		String office;
		String company = this.companyCombo.getSelectionModel().getSelectedItem();
		if(selectedRadioButtonfromSendTo.getText().equals("address"))
		{
			office=null;
		}
		else {
			
		    office = this.officeCombo.getSelectionModel().getSelectedItem().toString();
			
		}
		
		type type = this.typeCombo.getSelectionModel().getSelectedItem();
		boolean combo = checkComboBoxes(); // vrushta false ako ima neizbrano
		boolean radio = checkRadioButtons(); // vrushta false ako ima necheknato
		boolean field = checkFields(); // vrushta true ako ima prazno pole
		if (combo && radio && !field) { // tuka ne e taka
			if(selectedRadioButtonfromExpenseOf.getText().equals("receiver"))
			{
				dueAmount.setText("0.00");
				price= price+type.showPrice();
			}
			else
			{
				dueAmount.setText(Double.toString(type.showPrice()));
			}
			if(usr!=null && office!=null)
			{
			service.CreateShipment(type, dateCreation.getValue(), price, id, usr,service.getBulstatByFirmName(company),service.getIdByOfficeName(office));
			}
			if(usr!=null && office==null)
			{
				service.CreateShipment(type, dateCreation.getValue(), price, id, usr,service.getBulstatByFirmName(company));
			}
			if(usr==null && office==null)
			{
				service.CreateClient(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				service.CreateShipment(type, dateCreation.getValue(), price, id, service.SearchUserByPhone(phoneNmb),service.getBulstatByFirmName(company));
			}
			if(usr==null && office!=null)
			{
				service.CreateClient(phoneNmb, phoneNmb, name, email, phoneNmb, country, city, streetN);
				service.CreateShipment(type, dateCreation.getValue(), price, id, service.SearchUserByPhone(phoneNmb),service.getBulstatByFirmName(company),service.getIdByOfficeName(office));
			}
		} else
			resultLabel.setText("Fill in all fields correct.");

	}

	@FXML
	private void activateOfficeCombo(ActionEvent event) {
		officeCombo.setDisable(false);
		officeCombo.getItems().clear();
		List <String> list = service.getOfficesList(service.getBulstatByFirmName(companyCombo.getValue()));
		for(String column : list)
		{
		    fillOfficeCombo(column);
		}
		officeCombo.setItems(officeList);	
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
	
	public void getCompanyForAdmin(CourierFirm choosedCompany)
	{
		companyList.add(choosedCompany.getCompanyName());
		company_name.setText(choosedCompany.getCompanyName());
		companyCombo.setItems(companyList);
		companyCombo.getSelectionModel().select(choosedCompany.getCompanyName());
		companyCombo.setDisable(true);
		this.id=3;
			
	}
	
	public void getCompanyForClient(int id)
	{
		List<Object[]> list = service.getAllCompanies();
		for(Object[] column : list)
		{
			companyList.add((String)column[0]);
		}
		companyCombo.setItems(companyList);
		companyCombo.getSelectionModel().selectFirst();
		this.id=id;
	   
	}

}
