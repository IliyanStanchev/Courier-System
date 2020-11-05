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
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ClientView;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ExpectedShipmentsFormController implements Initializable {

	private UserService service= new UserServiceImpl();
	
	public void setClient(Client user)
	{
		
		List<ShipmentView> list= user.getExpectedShipments();
		for(ShipmentView shipment : list)
		{
			addToListTabel(shipment);
		}
		shipmentView.setItems(shipments);
	}
	

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
	private void cancelShipment(ActionEvent event) {
		resultLabel.setText("");
		ShipmentView selectedShipment = shipmentView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			service.ChangeShipmentStatus(service.SearchShipmentByID(selectedShipment.getNumber()),status.declined);
			shipmentView.getItems().remove(selectedShipment);
			resultLabel.setText("Status set to declined!");
		} else
			resultLabel.setText("First select.");
	}

	@FXML
	private void trackShipment(ActionEvent event) throws IOException {
		resultLabel.setText("");
		ShipmentView selectedShipment = shipmentView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			FXMLLoader loader = OpenNewForm.openNewForm("TrackShipmentForm.fxml", "Shipment state");
			TrackShipmentFormController next = loader.getController();
			next.setSelectedShipment(service.SearchShipmentByID(selectedShipment.getNumber()).getStatus());
		} else
			resultLabel.setText("First select.");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.shipmentNColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
		this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		
	}

	
	public void addToListTabel(ShipmentView shipment) {
		shipments.add(shipment);
	}

}