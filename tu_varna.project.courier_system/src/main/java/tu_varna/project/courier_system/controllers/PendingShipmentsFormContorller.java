package tu_varna.project.courier_system.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class PendingShipmentsFormContorller {

	@FXML
	private void shipmentDetails(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("ShipmentDetailsForm.fxml", "Details");
	}
}
