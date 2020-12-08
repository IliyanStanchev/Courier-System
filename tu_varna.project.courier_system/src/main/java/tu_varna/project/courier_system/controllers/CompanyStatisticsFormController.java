package tu_varna.project.courier_system.controllers;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;

public class CompanyStatisticsFormController
{

	private ShipmentService shipmentService = new ShipmentServiceImpl();
	private CompanyService companyService = new CompanyServiceImpl();

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

	public void setCompany(Company com)
	{
		Company company = companyService.getCompanyByID(com.getId());
		this.companyName.setText(company.getCompanyName());
		this.dateOfceation.setText(company.getDateOfCreation().format(DateTimeFormatter.ofPattern("dd.MMMM yyyy")));
		this.couriersNumber.setText(Integer.toString(company.getEmployees().size()));
		this.successfullyOrders.setText(Long.toString(shipmentService.getOrders(company, true)));
		this.unsuccessfullyOrders.setText(Long.toString(shipmentService.getOrders(company, false)));
		this.officesNumber.setText(Integer.toString(company.getOffices().size()));
	}

}
