package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class DeliveredShipmentsFormController implements Initializable {

	@FXML
	private TableView<ShipmentView> deliveredShipmentsView;
	@FXML
	private TableColumn<ShipmentView, Integer> numberColumn;
	@FXML
	private TableColumn<ShipmentView, Address> fromColumn;
	@FXML
	private TableColumn<ShipmentView, Address> toColumn;
	@FXML
	private TableColumn<ShipmentView, Address> deliveryDateColumn;

	private ObservableList<ShipmentView> deliveredShipments = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
		this.toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		this.deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

	}

	public void setCourierDeliveredShipments(List<ShipmentView> list) {
		for (ShipmentView shipment : list) {
			addToListTable(shipment);
		}
		this.deliveredShipmentsView.setItems(deliveredShipments);
	}

	private void addToListTable(ShipmentView shipment) {
		deliveredShipments.add(shipment);
	}

}
