package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.CourierFirm;

public class CompanyStatisticsFormController implements Initializable {
		
	public void setCompany(CourierFirm company)
	{
		
		 this.companyName.setText(company.getCompanyName());
		 this.dateOfceation.setText(company.getDateOfCreation().format(DateTimeFormatter.ofPattern("dd.MMMM yyyy")));
		 this.couriersNumber.setText(Integer.toString(company.getEmployees().size()));
		 this.successfullyOrders.setText(Integer.toString(company.getSuccesfulOrders()));
		 this.unsuccessfullyOrders.setText(Integer.toString(company.getUnsuccesfulOrders()));
		 this.officesNumber.setText(Integer.toString(company.getOffices().size()));
	}

	

	@FXML
	private Label companyName;

	@FXML
	private TextField dateOfceation;

	@FXML
	private TextField couriersNumber;

	@FXML
	private TextField successfullyOrders;

	@FXML
	private TextField officesNumber;

	@FXML
	private TextField unsuccessfullyOrders;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
