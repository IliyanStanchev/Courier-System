package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;

public class AboutCourierCompanyFormController
{

	private CompanyService companyService = new CompanyServiceImpl();

	@FXML
	private TextField bulstat;
	@FXML
	private Label bulstatL;
	@FXML
	private Label resultLabel;
	@FXML
	private Label name;
	@FXML
	private Label couriersNumber;
	@FXML
	private Label officesNumber;
	@FXML
	private Label successfulShipments;
	@FXML
	private Label unsuccessfulShipments;
	@FXML
	private Label activeShipments;
	@FXML
	private Label manager;
	@FXML
	private Label phoneNmb;
	@FXML
	private Label address;
	@FXML
	private Label date;

	private void loadInfo(Company firm)
	{

		this.name.setText(firm.getCompanyName());
		this.bulstatL.setText(Integer.toString(firm.getId()));
		this.couriersNumber.setText(Integer.toString(firm.getEmployees().size()));
		this.officesNumber.setText(Integer.toString(firm.getOffices().size()));
		this.manager.setText(firm.getManager().getManagerName());
		this.phoneNmb.setText(firm.getManager().getManagerPhone());
		this.address.setText(firm.getAddress().toString());
		this.successfulShipments.setText(Integer.toString(firm.getSuccesfulOrders()));
		this.unsuccessfulShipments.setText(Integer.toString(firm.getUnsuccesfulOrders()));
		this.activeShipments.setText(Integer.toString(firm.getActiveOrders()));
		this.date.setText(LocalDate.now().toString());
	}

	@FXML
	private void searchCompany(ActionEvent event)
	{
		this.cleanFields();
		resultLabel.setText("");
		try
		{
			Company firm = companyService.getCompanyByID(Integer.parseInt(this.bulstat.getText()));
			if (firm != null)
			{
				loadInfo(firm);
			} else
			{
				resultLabel.setText("Company not found!");
			}
		} catch (Exception e)
		{
			resultLabel.setText("Company not found!");
		}
	}

	private void cleanFields()
	{
		this.name.setText("");
		this.bulstatL.setText("");
		this.couriersNumber.setText("");
		this.officesNumber.setText("");
		this.manager.setText("");
		this.phoneNmb.setText("");
		this.address.setText("");
		this.successfulShipments.setText("");
		this.unsuccessfulShipments.setText("");
		this.activeShipments.setText("");
		this.date.setText("");
	}
}
