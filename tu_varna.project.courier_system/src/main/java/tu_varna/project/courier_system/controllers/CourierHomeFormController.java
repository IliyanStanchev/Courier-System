package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class CourierHomeFormController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void pendingShipments(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("PendingShipmentsForm.fxml", "Pending Shipments");
	}

	@FXML
	public void clientStatistics(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("ClientStatisticsForm.fxml", "Client Statistics");
	}

	@FXML
	public void deliveredShipments(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("DeliveredShipmentsForm.fxml", "Delivered Shipments");
	}

	@FXML
	public void companyStatistics(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("CompanyStatisticsForm.fxml", "Company Statistics");
	}

	@FXML
	public void dailyProgress(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("DailyProgressForm.fxml", "Daily Progress");
	}

}
