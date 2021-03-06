package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class RequestedShipmentsFormController implements Initializable
{

	ShipmentService shipmentService = new ShipmentServiceImpl();

	@FXML
	private TableView<ShipmentView> shipmentView;
	@FXML
	private TableColumn<ShipmentView, Integer> shipmentNColumn;
	@FXML
	private TableColumn<ShipmentView, String> receiverColumn;
	@FXML
	private TableColumn<ShipmentView, String> companyColumn;
	@FXML
	private Label resultLabel;

	private ObservableList<ShipmentView> shipments = FXCollections.observableArrayList();

	private void addToListTabel(ShipmentView shipment)
	{
		shipments.add(shipment);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.shipmentNColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiver"));
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
	}

	public void setRequestedShipments(List<ShipmentView> list)
	{
		for (ShipmentView shipment : list)
		{
			addToListTabel(shipment);
		}
		shipmentView.setItems(shipments);
	}

	@FXML
	private void trackShipment(ActionEvent event) throws IOException
	{
		resultLabel.setText("");
		ShipmentView selectedShipment = shipmentView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null)
		{
			FXMLLoader loader = OpenNewForm.openNewForm("TrackShipmentForm.fxml", "Shipment state");
			TrackShipmentFormController next = loader.getController();
			next.setSelectedShipment(shipmentService.getShipmentByID(selectedShipment.getNumber()).getStatus());
		} else
			resultLabel.setText("First select.");
	}

}
