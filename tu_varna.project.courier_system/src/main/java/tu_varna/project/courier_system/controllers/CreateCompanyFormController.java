package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateCompanyFormController implements Initializable {

	@FXML
	private TextField name;

	@FXML
	private TextField bulstat;

	@FXML
	private TextField manager;

	@FXML
	private TextField phoneNmb;

	@FXML
	private TextField country;

	@FXML
	private TextField city;

	@FXML
	private TextField streetN;

	@FXML
	private Label resultLabel;

	@FXML
	private Label phoneNmbValidationLabel;

	@FXML
	private Label managerValidationLabel;

	@FXML
	private Label bulstatValidationLabel;

	@FXML
	private Label nameValidationLabel;

	@FXML
	private Label countryValidationLabel;

	@FXML
	private Label cityValidationLabel;

	@FXML
	private Label streetNValidationLabel;

	@FXML
	void createCompany(ActionEvent event) {

		String name = this.name.getText();
		int bulstat = Integer.parseInt(this.bulstat.getText());
		String manager = this.manager.getText();
		String phoneNmb = this.phoneNmb.getText();
		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		// gbcreatecompany(name,bulstat, manager, phoneNmb, country, city, streetN);
		System.out.println(bulstat + "  " + city);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
