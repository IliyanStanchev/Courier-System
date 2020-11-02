package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AboutCourierFormController {

	@FXML
	private AnchorPane workPane;

	@FXML
	private Label companyName;

	@FXML
	private Label nameL;

	@FXML
	private Label username;

	@FXML
	private Label password;

	@FXML
	private Label email;

	@FXML
	private Label phoneN;

	@FXML
	private Label address;

	@FXML
	private DatePicker date;
	@FXML
	private TextField name;
	@FXML
	private TextField phoneNmb;

	@FXML
	private Label resultLabel;

	@FXML
	private Label deliveredShipments;

	@FXML
	private Label phoneNValidationLabel;

	@FXML
	private Label nameValidationLabel;

	@FXML
	public boolean searchCourier(ActionEvent event) {
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		boolean isFound = false;// dbsearch(name, phoneNmb));
		if (isFound) {
			this.date.setValue(LocalDate.now());
		}
		return isFound;
	}

	public void loadInfo(String name, String company, String username, String password, String email, String phoneNmb,
			String country, String city, String streetN, String deliveredShipments) {
		this.nameL.setText(name);
		this.companyName.setText(company);
		this.username.setText(username);
		this.password.setText(password);
		this.email.setText(email);
		this.address.setText(country + " " + city + " " + streetN);
		this.deliveredShipments.setText(deliveredShipments);
	}

}
