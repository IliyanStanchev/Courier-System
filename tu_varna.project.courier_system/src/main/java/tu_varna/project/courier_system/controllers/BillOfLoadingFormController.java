package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tu_varna.project.courier_system.entity.Shipment;

public class BillOfLoadingFormController {

	@FXML
	private Label companyName;
	@FXML
	private Label companyBulstat;
	@FXML
	private Label senderName;
	@FXML
	private Label senderAddress;
	@FXML
	private Label receiverName;
	@FXML
	private Label receiverAddress;
	@FXML
	private Label type;
	@FXML
	private Label price;
	@FXML
	private Label shipmentNumer;
	@FXML
	private Label date;
	@FXML
	private Label companyName2;

	public void setCreatedShipment(Shipment shipment) {
		this.companyName.setText(shipment.getFirm().getCompanyName());
		this.companyBulstat.setText(((Integer) shipment.getFirm().getId()).toString());
		this.senderName.setText(shipment.getSender().getName());
		this.senderAddress.setText(shipment.getSender().getAddress().toString());
		this.receiverName.setText(shipment.getReceiver().getName());
		this.receiverAddress.setText(shipment.getReceiver().getAddress().toString());
		this.type.setText(shipment.getType().toString());
		this.price.setText(((Double) shipment.getShipmentPrice()).toString());
		this.shipmentNumer.setText(((Integer) shipment.getId()).toString());
		this.date.setText((LocalDate.now()).toString());
		this.companyName2.setText(shipment.getFirm().getCompanyName());

	}

}
