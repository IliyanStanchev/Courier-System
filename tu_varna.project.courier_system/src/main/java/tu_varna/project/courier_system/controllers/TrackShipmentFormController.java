package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Status.status;

public class TrackShipmentFormController implements Initializable {
	
	
	@FXML
	private Label stateLabel;
	
	public void setSelectedShipment(status status) {
	stateLabel.setText(status.toString());
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
