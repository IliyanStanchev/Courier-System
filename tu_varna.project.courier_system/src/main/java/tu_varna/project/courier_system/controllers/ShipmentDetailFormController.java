package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Shipment;

public class ShipmentDetailFormController implements Initializable {

	@FXML
	private Label dateOfOrdering;

	@FXML
	private Label from;

	@FXML
	private Label to;

	@FXML
	private Label senderPhone;

	@FXML
	private Label receiverPhone;

	@FXML
	private Label type;

	@FXML
	private Label dueAmount;

	@FXML
	private Label number;

	@FXML
	private Label senderName;

	@FXML
	private Label receiverName;

	public void setChoosedShipment(Shipment shipment) {

		number.setText(Integer.toString(shipment.getId()));
		dateOfOrdering.setText(shipment.getDateCreated().toString());
		from.setText(shipment.getSender().getAddress().toString());
		to.setText(shipment.getReceiver().getAddress().toString());
		senderPhone.setText(shipment.getSender().getPhoneNumber());
		senderName.setText(shipment.getSender().getName());
		receiverPhone.setText(shipment.getReceiver().getPhoneNumber());
		receiverName.setText(shipment.getReceiver().getName());
		type.setText(shipment.getType().toString());
		dueAmount.setText(Double.toString(shipment.getShipmentPrice()));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
