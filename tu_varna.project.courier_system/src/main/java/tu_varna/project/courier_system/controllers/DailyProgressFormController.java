package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class DailyProgressFormController implements Initializable {

	@FXML
	private Label deliveredShipments;

	@FXML
	private Label cancelledShipments;

	@FXML
	private TextField date;

	@FXML
	private TableView<ShipmentView> shipmentsView;

	@FXML
	private TableColumn<ShipmentView, Integer> numberColumn;

	private ObservableList<ShipmentView> shipmentsInProcess = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		shipmentsView.setItems(shipmentsInProcess);
		this.date.setText(LocalDate.now().toString());
	}

	public void setCourier(Courier courier) {
		this.cancelledShipments.setText(Integer.toString(courier.getCancelledShipments()));
		this.deliveredShipments.setText(Integer.toString(courier.getDeliveredOrders().size()));
		for (Shipment shipment : courier.getShipmentsInProgress()) {
			addToListTabel(shipment.getId());
		}

	}

	public void addToListTabel(int number) {
		shipmentsInProcess.add(new ShipmentView(number));
	}

}
