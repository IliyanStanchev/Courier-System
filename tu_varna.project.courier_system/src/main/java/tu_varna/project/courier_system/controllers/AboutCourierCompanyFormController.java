package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;


public class AboutCourierCompanyFormController {
	
	private UserService service= new UserServiceImpl();

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
	private DatePicker date;

	@FXML
	public void searchCompany(ActionEvent event) {
		int bulstat = Integer.parseInt(this.bulstat.getText());
		CourierFirm firm= service.getCompanyByID(bulstat);
		if(firm!=null)
		{
			loadInfo(firm.getCompanyName(),firm.getId(),firm.getEmployees().size(),firm.getOffices().size(),firm.getManager().getManagerName(),firm.getManager().getManagerPhone(),firm.getAddress().getCountry(),firm.getAddress().getCity(),firm.getAddress().getStreet(),
					firm.getSuccesfulOrders(),firm.getUnsuccesfulOrders(),firm.getActiveOrders());
		}
		else
		{
			resultLabel.setText("Company not found!");
		}
	}

	public void loadInfo(String name, int bulstat, int couriersNumber, int officesNumber, String manager, String phoneNmb,
			String country, String city, String streetN, int successfulShipments,int unsuccessfulShipments, int activeShipments) {
		this.name.setText(name);
		this.bulstatL.setText(String.valueOf(bulstat));
		this.couriersNumber.setText(String.valueOf(couriersNumber));
		this.officesNumber.setText(String.valueOf(officesNumber));
		this.manager.setText(manager);
		this.phoneNmb.setText(phoneNmb);
		this.address.setText(country+" "+city+" "+streetN);
		this.successfulShipments.setText(String.valueOf(successfulShipments));
		this.unsuccessfulShipments.setText(String.valueOf(unsuccessfulShipments));
		this.activeShipments.setText(String.valueOf(activeShipments));
		this.date.setValue(LocalDate.now());
}
}
