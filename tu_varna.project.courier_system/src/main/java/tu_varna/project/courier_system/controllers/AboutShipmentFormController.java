package tu_varna.project.courier_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;

public class AboutShipmentFormController {

	private ShipmentService shipmentService = new ShipmentServiceImpl();
	@FXML
	private TextField number;
	@FXML
	private Label company;
	@FXML
	private Label from;
	@FXML
	private Label oORa;
	@FXML
	private Label senderName;
	@FXML
	private Label receiverName;
	@FXML
	private Label price;
	@FXML
	private Label to;
	@FXML
	private Label dateOfOrdering;
	@FXML
	private Label state;
	@FXML
	private Label type;
	@FXML
	private Label resultLabel;

	public void loadInfo(Shipment shipment) {

		String officeOrAddress = "address";
		if (shipment.getToOffice() != null) {
			officeOrAddress = "office";
		}
		this.state.setText(shipment.getStatus().toString());
		this.company.setText(shipment.getFirm().getCompanyName());
		this.dateOfOrdering.setText(shipment.getDateCreated().toString());
		this.from.setText(shipment.getSender().getAddress().toString());
		this.to.setText(shipment.getReceiver().getAddress().toString());
		this.oORa.setText(officeOrAddress);
		this.senderName.setText(shipment.getSender().getName());
		this.receiverName.setText(shipment.getReceiver().getName());
		this.type.setText(shipment.getType().toString());
		this.price.setText(Double.toString(shipment.getShipmentPrice()));
	}

	@FXML
	private void searchShipment(ActionEvent event) {
		this.cleanFields();
		resultLabel.setText("");
		try {
			Shipment shipment = shipmentService.getShipmentByID(Integer.parseInt(this.number.getText()));
			if (shipment != null) {
				loadInfo(shipment);
			} else {
				resultLabel.setText("Shipment not found!");
			}
		} catch (Exception e) {
			resultLabel.setText("Shipment not found!");
		}
	}

	private void cleanFields() {
		this.state.setText("");
		this.company.setText("");
		this.dateOfOrdering.setText("");
		this.from.setText("");
		this.to.setText("");
		this.oORa.setText("");
		this.senderName.setText("");
		this.receiverName.setText("");
		this.type.setText("");
		this.price.setText("");

	}
}
