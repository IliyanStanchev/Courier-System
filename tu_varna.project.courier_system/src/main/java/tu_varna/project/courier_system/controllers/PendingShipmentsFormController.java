package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PendingShipmentsFormController implements Initializable {
	
	UserService service= new UserServiceImpl();
	
	private int courier_id;

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
	void acceptRequest(ActionEvent event) {
		ShipmentView selectedShipment = requestedShipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			acceptedShipments.add(selectedShipment);
			service.setShipmentCourier(service.SearchShipmentByID(selectedShipment.getNumber()),courier_id);
			requestedShipmentsView.getItems().remove(selectedShipment);
		} else
			acceptValidationLabel.setText("First select");

	}

	@FXML
	private void shipmentDetails(ActionEvent event) throws IOException {
		ShipmentView selectedShipment = acceptedShipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			FXMLLoader loader = OpenNewForm.openNewForm("ShipmentDetailsForm.fxml", "Details");
			ShipmentDetailFormController next = loader.getController();
			Shipment shipment= service.SearchShipmentByID(selectedShipment.getNumber());
		    next.setChoosedShipment(shipment);
		} else
			detailValidationLabel.setText("First select");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.fromColumn.setCellValueFactory(new PropertyValueFactory<>("from")); // kak raboti?
		this.toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		this.numberAColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.fromAColumn.setCellValueFactory(new PropertyValueFactory<>("from")); // kak raboti?
		this.toAColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
		requestedShipmentsView.setItems(requestedShipments);
		acceptedShipmentsView.setItems(acceptedShipments);

	}

	public void addToListTabel(int number, Address from, Address to) {
		requestedShipments.add(new ShipmentView(number, from, to));
	}
	
	public void addToCourierShipmentsTable(int number, Address from, Address to) {
		acceptedShipments.add(new ShipmentView(number, from, to));
	}
	
	public void getPendingShipments(int courier_id,int company_id)
	{
		Courier courier = (Courier)service.getUserByID(courier_id);
		this.courier_id=courier_id;
		for(Shipment s :service.SearchCourierFirm(company_id).getPendingOrders())
		{
			addToListTabel(s.getId(),s.getSender().getAddress(),s.getReceiver().getAddress());
		}
		for(Shipment s : courier.getShipmentsForDelivery())
		{
			addToCourierShipmentsTable(s.getId(),s.getSender().getAddress(),s.getReceiver().getAddress());
		}
		
		
	}

}
