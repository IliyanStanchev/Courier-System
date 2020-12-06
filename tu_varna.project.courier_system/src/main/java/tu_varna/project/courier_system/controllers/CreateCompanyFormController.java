package tu_varna.project.courier_system.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import tu_varna.project.courier_system.helper.FieldValidation;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;

public class CreateCompanyFormController
{

	private static final Logger logger = LogManager.getLogger(CreateCompanyFormController.class);

	private CompanyService companyService = new CompanyServiceImpl();

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

	private boolean countryV;
	private boolean cityV;
	private boolean numberV;
	private boolean streetV;
	private boolean nameV;
	private boolean managerV;
	private boolean bulstatV;

	@FXML
	private void bulstatValidation(KeyEvent event)
	{
		bulstatV = FieldValidation.bulstatValidation(this.bulstat, this.bulstatValidationLabel);
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
	void managerValidation(KeyEvent event)
	{
		managerV = FieldValidation.alphabetValidation(this.manager, this.managerValidationLabel);
	}

	@FXML
	void nameValidation(KeyEvent event)
	{
		nameV = FieldValidation.alphabetValidation(this.name, this.nameValidationLabel);
	}

	@FXML
	private void phoneNmbValidation(KeyEvent event)
	{
		numberV = FieldValidation.numberValidation(this.phoneNmb, this.phoneNmbValidationLabel);
	}

	@FXML
	private void streetNValidation(KeyEvent event)
	{
		streetV = FieldValidation.streetNValidation(this.streetN, this.streetNValidationLabel);
	}

	@FXML
	void createCompany(ActionEvent event)
	{
		if (areAllFieldsFull())
		{
			String name = this.name.getText();
			int bulstat = Integer.parseInt(this.bulstat.getText());
			String manager = this.manager.getText();
			String phoneNmb = this.phoneNmb.getText();
			String country = this.country.getText();
			String city = this.city.getText();
			String streetN = this.streetN.getText();
			boolean check = companyService.createCompany(bulstat, name, manager, phoneNmb, country, city, streetN);
			if (check)
			{
				resultLabel.setText("Company created succesfully!");
				logger.info("Company [ " + bulstat + " , " + name + " ] successfully created by administrator! ");
			} else
			{
				resultLabel.setText("Error! Company name or bulstat are already taken!");
			}
		}
	}

	private boolean areAllFieldsFull()
	{
		boolean areCorrect = managerV && bulstatV && streetV && countryV && cityV && numberV && nameV;
		if (!areCorrect)
		{
			this.resultLabel.setText("Fill in all fields!");
		}
		return areCorrect;
	}

}
