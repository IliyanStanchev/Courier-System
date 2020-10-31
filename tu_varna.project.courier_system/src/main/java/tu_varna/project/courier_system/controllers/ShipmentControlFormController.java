package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import tu_varna.project.courier_system.helper.OpenNewForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class ShipmentControlFormController implements Initializable {

	@FXML
	public void viewShipmentsList(ActionEvent event) throws IOException {
		OpenNewForm.openNewForm("ViewShipmentsForm.fxml","Shipments list");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}