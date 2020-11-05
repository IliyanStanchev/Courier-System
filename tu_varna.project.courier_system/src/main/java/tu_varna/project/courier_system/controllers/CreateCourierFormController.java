package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class CreateCourierFormController implements Initializable {
	
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

	@FXML
	public void createCourier(ActionEvent event) {
		String username = this.username.getText();
		String password = this.password.getText();
		String confirmPW = this.confirmPW.getText();
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		String email = this.email.getText();
		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		String company = this.companyCombo.getSelectionModel().getSelectedItem().toString();	
		
		boolean check=service.CreateCourier(username, password, name, phoneNmb, email,
		 country, city, streetN, service.getBulstatByFirmName(company));
		if(check)
		{
			resultLabel.setText("Courier succesfully created!");
		}
		else
		{
			resultLabel.setText("Error!Courier username or phone are already taken!");
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		List<Object[]> list= service.getAllCompanies();
		for(Object[] column: list)
		{
			fillCompanyCombo((String)column[0]);
		}
		companyCombo.setItems(companyList);

	}

	public void fillCompanyCombo(String companyName) {
		companyList.add(companyName);
	}

}
