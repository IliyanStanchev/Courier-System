package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;

public class CourierHomeFormController {

	private ShipmentService shipmentService = new ShipmentServiceImpl();

	private Courier courier;
	private Company company;

	@FXML
	private void clientStatistics(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("ClientStatisticsForm.fxml", "Client Statistics");
		ClientStatisticsFormController next = loader.getController();
		next.setCompanyID(company.getId());

	}

	@FXML
	private void companyStatistics(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("CompanyStatisticsForm.fxml", "Company Statistics");
		CompanyStatisticsFormController next = loader.getController();
		next.setCompany(company);

	}

	@FXML
	private void dailyProgress(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("YourProgressForm.fxml", "Your Progress");
		YourProgressFormController next = loader.getController();
		next.setCourier(courier);

	}

	@FXML
	private void deliveredShipments(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("DeliveredShipmentsForm.fxml", "Delivered Shipments");
		DeliveredShipmentsFormController next = loader.getController();
		next.setCourierDeliveredShipments(shipmentService.getCourierDeliveredShipments(courier));

	}

	@FXML
	private void pendingShipments(ActionEvent event) throws IOException {
		FXMLLoader loader = OpenNewForm.openNewForm("PendingShipmentsForm.fxml", "Pending Shipments");
		PendingShipmentsFormController next = loader.getController();
		next.getPendingShipments(courier, company);
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
		this.company = courier.getFirm();
	}

}
