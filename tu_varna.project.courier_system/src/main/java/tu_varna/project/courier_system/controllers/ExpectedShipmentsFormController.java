package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ExpectedShipmentsFormController implements Initializable
{

	private static final Logger logger = LogManager.getLogger(ExpectedShipmentsFormController.class);

	private ShipmentService shipmentService = new ShipmentServiceImpl();

	@FXML
	private TableView<ShipmentView> shipmentView;
	@FXML
	private TableColumn<ShipmentView, String> senderColumn;
	@FXML
	private TableColumn<ShipmentView, Integer> shipmentNColumn;
	@FXML
	private TableColumn<ShipmentView, Double> priceColumn;
	@FXML
	private TableColumn<ShipmentView, String> companyColumn;
	@FXML
	private Label resultLabel;
	private ObservableList<ShipmentView> shipments = FXCollections.observableArrayList();

	@FXML
	private void cancelShipment(ActionEvent event)
	{
		resultLabel.setText("");
		ShipmentView selectedShipment = shipmentView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null)
		{
			if (selectedShipment.getStatus() == status.pending)
			{
				shipmentService.changeShipmentStatus(shipmentService.getShipmentByID(selectedShipment.getNumber()),
						status.declined);
				shipmentView.getItems().remove(selectedShipment);
				resultLabel.setText("Status set to declined!");
				logger.info("Shipment with id: " + selectedShipment.getNumber()
						+ " has been declined by client with phone: " + selectedShipment.getPhoneNmb());
			} else
			{
				resultLabel.setText("You cannot decline shipments that are already in proccess of delivery! ");
			}
		} else
			resultLabel.setText("First select.");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		this.shipmentNColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
		this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

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

	public void setExpectedShipments(List<ShipmentView> list)
	{
		for (ShipmentView shipment : list)
		{
			addToListTable(shipment);
		}
		shipmentView.setItems(shipments);
	}

	private void addToListTable(ShipmentView shipment)
	{
		shipments.add(shipment);
	}

}
