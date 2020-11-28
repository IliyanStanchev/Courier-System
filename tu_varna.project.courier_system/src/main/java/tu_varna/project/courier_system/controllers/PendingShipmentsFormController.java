package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.ShipmentDeliveryService;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.ShipmentServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class PendingShipmentsFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(PendingShipmentsFormController.class);

	private ShipmentService shipmentService = new ShipmentServiceImpl();
	private ShipmentDeliveryService delivery;

	private Courier courier;

	@FXML
	private TableView<ShipmentView> requestedShipmentsView;
	@FXML
	private TableView<ShipmentView> acceptedShipmentsView;
	@FXML
	private TableColumn<ShipmentView, Integer> numberColumn;
	@FXML
	private TableColumn<ShipmentView, Address> fromColumn;
	@FXML
	private TableColumn<ShipmentView, Address> toColumn;
	@FXML
	private TableColumn<ShipmentView, Integer> numberAColumn;
	@FXML
	private TableColumn<ShipmentView, Address> fromAColumn;
	@FXML
	private TableColumn<ShipmentView, Address> toAColumn;
	@FXML
	private Label acceptValidationLabel;
	@FXML
	private Label detailValidationLabel;

	private ObservableList<ShipmentView> requestedShipments = FXCollections.observableArrayList();
	private ObservableList<ShipmentView> acceptedShipments = FXCollections.observableArrayList();

	@FXML
	private void acceptRequest(ActionEvent event) {

		ShipmentView selectedShipment = requestedShipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			acceptedShipments.add(selectedShipment);
			Shipment shipment = shipmentService.getShipmentByID(selectedShipment.getNumber());
			shipmentService.setCourierOfShipment(shipment, courier);
			logger.info(
					"Shipment with id: " + shipment.getId() + " has been taken by courier with id: " + courier.getId());
			delivery =new ShipmentDeliveryService(shipment);
			delivery.startDelivery();
			requestedShipmentsView.getItems().remove(selectedShipment);
		} else
			acceptValidationLabel.setText("First select");

	}

	private void addToCourierShipmentsTable(ShipmentView shipment) {
		acceptedShipments.add(shipment);
	}

	private void addToListTable(ShipmentView shipment) {
		requestedShipments.add(shipment);
	}

	public void getPendingShipments(Courier courier, Company company) {

		this.courier = courier;

		for (ShipmentView shipment : shipmentService.getPendingOrders(company)) {
			addToListTable(shipment);
		}
		for (ShipmentView shipment : shipmentService.getCourierActiveShipments(courier)) {
			addToCourierShipmentsTable(shipment);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
		this.toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		this.numberAColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.fromAColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
		this.toAColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		requestedShipmentsView.setItems(requestedShipments);
		acceptedShipmentsView.setItems(acceptedShipments);

	}

	@FXML
	private void shipmentDetails(ActionEvent event) throws IOException {

		ShipmentView selectedShipment = acceptedShipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			FXMLLoader loader = OpenNewForm.openNewForm("ShipmentDetailsForm.fxml", "Details");
			ShipmentDetailFormController next = loader.getController();
			next.setChoosedShipment(shipmentService.getShipmentByID(selectedShipment.getNumber()));
		} else
			detailValidationLabel.setText("First select");

	}
}
