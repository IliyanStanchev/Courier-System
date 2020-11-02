package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShipmentsViewFormController implements Initializable {

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

	private ObservableList<ShipmentView> shipment = FXCollections.observableArrayList();

	private FilteredList<ShipmentView> filteredData = new FilteredList<>(shipment, b -> true);

	private int choosedCompanyID;

	public int getCompany() {
		return choosedCompanyID;
	}

	public void setChoosedCompany(int bulstat) {
		this.choosedCompanyID = bulstat;
		System.out.println(this.choosedCompanyID);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		shipmentsView.setItems(shipment);
		shipmentsView.setItems(shipment);
		// DBfunkciq(choosedCompanyID) --> izpolzwa addToList;
		addToList(1, new Date(5, 5, 2000), "0896145500", "in progress");
		addToList(2, new Date(5, 10, 1988), "0116145500", "delivered");

		/*number.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(shipment -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (((Integer) shipment.getNumber()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}

				else
					return false; // Does not match.
			});
		});

		SortedList<ShipmentView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(shipmentsView.comparatorProperty());
		shipmentsView.setItems(sortedData);
*/
	}

	public void addToList(int number, Date date, String phoneNmb, String status) {
		shipment.add(new ShipmentView(number, date, phoneNmb, status));
	}

	@FXML
	public void createShipment(ActionEvent event) {
	
			//int number = (shipmentsView.getSelectionModel().getSelectedItems().get(0).getNumber());
			try {
				FXMLLoader loader = OpenNewForm.openNewForm("RequestShipmentForm.fxml", "Create Shipment");
				RequestShipmentFormController next = loader.getController();
				//next.setChoosedShipment(number);
			} catch (Exception e) {
				System.out.println("loader is null.");
				//e.printStackTrace();
			}

		
		

	}

	@FXML
	public void deleteShipment(ActionEvent event) {
		ShipmentView selectedShipment = shipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			System.out.println(selectedShipment.getNumber());
			// tabwat exsepsani tuk tam mai
			// if DBremoveCorier(String selectedCourier.getPhoneNmb()) == true{ .. else
			// neuspeshno iztriwane i ne se trie ot tablicata
			shipmentsView.getItems().remove(selectedShipment);
		} else
			resultLabel.setText("First select");
	}

}