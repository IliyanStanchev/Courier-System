package tu_varna.project.courier_system.controllers;

import java.net.URL;
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
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;

public class CreateOfficeFormController implements Initializable
{

	private static final Logger logger = LogManager.getLogger(CreateOfficeFormController.class);

	CompanyService companyService = new CompanyServiceImpl();

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
	private ComboBox<CompanyView> companyCombo;
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
	private ObservableList<CompanyView> companyList = FXCollections.observableArrayList();

	private boolean countryV;
	private boolean cityV;
	private boolean streetV;
	private boolean agentV;
	private boolean numberV;

	@FXML
	private void agentValidation(KeyEvent event)
	{
		agentV = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
	}

	private boolean areAllFieldsFull()
	{
		boolean areCorrect = streetV && countryV && cityV && numberV && agentV;
		if (!areCorrect)
		{
			this.resultLabel.setText("Fill in all fields!");
		}
		return areCorrect;
	}

	@FXML
	void cityValidation(KeyEvent event)
	{
		cityV = FieldValidation.alphabetValidation(this.city, this.cityValidationLabel);
	}

	@FXML
	private void countryValidation(KeyEvent event)
	{
		countryV = FieldValidation.alphabetValidation(this.country, this.countryValidationLabel);
	}

	@FXML
	void createOffice(ActionEvent event)
	{

		resultLabel.setText("");
		this.comboLabel.setText("");
		if (areAllFieldsFull() && isSelectedFromComboBox())
		{
			String country = this.country.getText();
			String city = this.city.getText();
			String streetN = this.streetN.getText();
			String agent = this.agent.getText();
			String phoneNmb = this.phoneNmb.getText();
			CompanyView company = this.companyCombo.getSelectionModel().getSelectedItem();
			boolean check = companyService.createOffice(company.getName(), country, city, streetN, agent, phoneNmb,
					company.getBulstat());
			if (check)
			{
				resultLabel.setText("Office succesfully created!");
				logger.info("Office [ " + company + " , " + streetN + " ] successfully created by administrator! ");
			} else
			{
				resultLabel.setText("There is office on this address!");
			}
		}
	}

	public void fillCompanyCombo(CompanyView companyName)
	{
		companyList.add(companyName);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		for (CompanyView company : companyService.getAllCompanies())
		{
			fillCompanyCombo(company);
		}
		companyCombo.setItems(companyList);
	}

	private boolean isSelectedFromComboBox()
	{
		boolean isEmpty = this.companyCombo.getSelectionModel().isEmpty();
		if (isEmpty == true)
		{
			this.comboLabel.setText("First select");
		}
		return !isEmpty;

	}

	@FXML
	private void phoneNValidation(KeyEvent event)
	{
		numberV = FieldValidation.numberValidation(this.phoneNmb, this.phoneNmbValidationLabel);
	}

	@FXML
	private void streetNValidation(KeyEvent event)
	{
		streetV = FieldValidation.streetNValidation(this.streetN, this.streetNValidationLabel);
	}

}
