package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class CourierHomeFormController implements Initializable {

	UserService service = new UserServiceImpl();

	private int courier_id;
	private int company_id;

	public void setCourierInformation(int id) {

		this.courier_id = id;
		this.company_id = service.getBulstatByCourier(id);

	}

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void pendingShipments(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("PendingShipmentsForm.fxml", "Pending Shipments");
		PendingShipmentsFormController next = loader.getController();
		next.getPendingShipments(courier_id, company_id);

	}

	@FXML
	public void clientStatistics(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("ClientStatisticsForm.fxml", "Client Statistics");
		ClientStatisticsFormController next = loader.getController();
		next.setCompanyID(company_id);

	}

	@FXML
	public void deliveredShipments(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("DeliveredShipmentsForm.fxml", "Delivered Shipments");
		DeliveredShipmentsFormController next = loader.getController();
		next.setCourier((Courier) service.getUserByID(courier_id));

	}

	@FXML
	public void companyStatistics(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("CompanyStatisticsForm.fxml", "Company Statistics");
		CompanyStatisticsFormController next = loader.getController();
		next.setCompany(service.getCompanyByID(company_id));

	}

	@FXML
	public void dailyProgress(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("DailyProgressForm.fxml", "Daily Progress");
		DailyProgressFormController next = loader.getController();
		next.setCourier((Courier) service.getUserByID(courier_id));

	}

}
