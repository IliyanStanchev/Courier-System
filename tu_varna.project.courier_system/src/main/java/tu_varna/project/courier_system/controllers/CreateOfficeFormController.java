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

	@FXML
	void createOffice(ActionEvent event) {

		String country = this.country.getText();
		String city = this.city.getText();
		String streetN = this.streetN.getText();
		String agent = this.agent.getText();
		String phoneNmb = this.phoneNmb.getText();
		String company = this.companyCombo.getSelectionModel().getSelectedItem().toString();
		System.out.println(company);

		boolean check = service.CreateOffice(company, country, city, streetN, agent, phoneNmb,
				service.getBulstatByFirmName(company));
		if (check) {
			resultLabel.setText("Courier succesfully created!");
			logger.info("Office [ " + company + " , " + streetN + " ] successfully created by administrator! ");
		} else {
			resultLabel.setText("Error!Office cannot be created!");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		List<Object[]> list = service.getAllCompanies();
		for (Object[] column : list) {
			fillCompanyCombo((String) column[0]);
		}
		companyCombo.setItems(companyList);

	}

	public void fillCompanyCombo(String companyName) {
		companyList.add(companyName);
	}

}
