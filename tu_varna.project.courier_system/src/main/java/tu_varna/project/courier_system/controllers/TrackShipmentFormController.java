package tu_varna.project.courier_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Status.status;

public class TrackShipmentFormController {

	@FXML
	private Label stateLabel;

	public void setSelectedShipment(status status) {
		stateLabel.setText(status.toString());
	}

}
