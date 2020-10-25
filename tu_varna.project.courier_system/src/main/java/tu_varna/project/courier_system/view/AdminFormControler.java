package tu_varna.project.courier_system.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import tu_varna.project.courier_system.helper.BuiltInForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class AdminFormControler implements Initializable {

	@FXML
	private AnchorPane workPane;

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void controlClients(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("Create_DeleteClientForm.fxml", workPane);
	}

	@FXML
	private void controlCouriers(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierControlForm.fxml", workPane);
	}

	@FXML
	private void controlCourierSystems(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("Create_DeleteCompanyForm.fxml", workPane);
	}

	@FXML
	private void controlOffices(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("OfficesControlForm.fxml", workPane);
	}

	@FXML
	private void controlShipments(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ShipmentControlForm.fxml", workPane);
	}

}
