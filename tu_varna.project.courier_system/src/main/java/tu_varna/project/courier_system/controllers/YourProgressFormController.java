package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.ShipmentServiceImpl;

public class YourProgressFormController {

	ShipmentService shipmentService = new ShipmentServiceImpl();

	@FXML
	private Label deliveredShipments;
	@FXML
	private Label cancelledShipments;
	@FXML
	private Label inProcess;
	@FXML
	private TextField date;

	public void setCourier(Courier courier) {
		int activeShipments = shipmentService.getCourierActiveShipments(courier).size();
		int deliveredShipments = shipmentService.getCourierDeliveredShipments(courier).size();
		this.cancelledShipments.setText(Integer.toString(courier.getCancelledShipments()));
		this.deliveredShipments.setText(Integer.toString(deliveredShipments));
		this.inProcess.setText(Integer.toString(activeShipments));
		this.date.setText(LocalDate.now().toString());

	}

}
