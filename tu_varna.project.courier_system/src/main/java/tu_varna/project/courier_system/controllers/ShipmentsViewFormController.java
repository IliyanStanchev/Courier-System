package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ShipmentsViewFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(ShipmentsViewFormController.class);

	private ShipmentService shipmentService = new ShipmentServiceImpl();

	@FXML
	private Label company_id;
	@FXML
	private TextField number;
	@FXML
	private Label resultLabel;
	@FXML
	private TableView<ShipmentView> shipmentsView;
	@FXML
	private TableColumn<ShipmentView, Integer> numberColumn;
	@FXML
	private TableColumn<ShipmentView, Date> dateColumn;
	@FXML
	private TableColumn<ShipmentView, String> phoneNmbColumn;
	@FXML
	private TableColumn<ShipmentView, String> statusColumn;

	private ObservableList<ShipmentView> shipments = FXCollections.observableArrayList();
	private FilteredList<ShipmentView> filteredData = new FilteredList<>(shipments, b -> true);
	private Company choosedCompany;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

	}

	@FXML
	private void createShipment(ActionEvent event) {
		try {
			FXMLLoader loader = OpenNewForm.openNewForm("RequestShipmentForm.fxml", "Create Shipment");
			RequestShipmentFormController next = loader.getController();
			next.getCompanyForAdmin(choosedCompany);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loader is null.");
		}
	}

	@FXML
	private void deleteShipment(ActionEvent event) {

		resultLabel.setText("");
		ShipmentView selectedShipment = shipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			shipmentService.deleteShipment(shipmentService.getShipmentByID(selectedShipment.getNumber()));
			remove(selectedShipment);
			logger.info(
					"Shipment with id: " + selectedShipment.getNumber() + " successfully removed by administrator!");
		} else {
			resultLabel.setText("First select");
		}
	}

	public void setChoosedCompany(Company choosedCompany) {
		this.choosedCompany = choosedCompany;

	}

	public void viewShipmentsList() {

		for (ShipmentView shipment : shipmentService.getShipmentsByCompany(choosedCompany)) {
			addToListTable(shipment);
		}

		shipmentsView.setItems(shipments);
		wrapListAndAddFiltering();
		shipmentsView.setItems(filteredData);

	}

	private void wrapListAndAddFiltering() {

		number.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(shipment -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (((Integer) shipment.getNumber()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}

				else
					return false;
			});
		});

	}

	private void remove(ShipmentView item) {
		shipments.remove(item);
		wrapListAndAddFiltering();
		shipmentsView.setItems(filteredData);
	}

	private void addToListTable(ShipmentView shipment) {
		shipments.add(shipment);
	}

}