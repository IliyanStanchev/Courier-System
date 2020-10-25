package tu_varna.project.courier_system.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tu_varna.project.courier_system.helper.OpenNewForm;

public class CourierHomeFormControler implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	public void createClient(ActionEvent event) throws IOException {

		OpenNewForm.openNewForm("CreateClientForm.fxml", "Create client");

	}

	@FXML
	public void createShipment(ActionEvent event) throws IOException {

		OpenNewForm.openNewForm("CreateShipmentForm.fxml", "Create shipment");

	}

}
