package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ShipmentsViewFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(ShipmentsViewFormController.class);
	private UserService service = new UserServiceImpl();
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
	private ObservableList<ShipmentView> shipment = FXCollections.observableArrayList();
	private FilteredList<ShipmentView> filteredData = new FilteredList<>(shipment, b -> true);
	private Company choosedCompany;
	public void setChoosedCompany(Company choosedCompany) {
		this.choosedCompany = choosedCompany;

	}

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
			service.deleteShipment(selectedShipment.getNumber());
			remove(selectedShipment);
			logger.info(
					"Shipment with id: " + selectedShipment.getNumber() + " successfully removed by administrator!");
		} else {
			resultLabel.setText("First select");
		}
	}

	public void viewShipmentsList() {

		List<Object[]> list = service.getShipmentsList(choosedCompany.getId());
		for (Object[] column : list) {
			addToListTable((Integer) column[0], (LocalDate) column[1], (String) column[2], (Status.status) column[3]);
		}

		shipmentsView.setItems(shipment);
		wrapListAndAddFiltering();
		shipmentsView.setItems(filteredData);

	}

	private void remove(ShipmentView item) {
		shipment.remove(item);
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

	private void addToListTable(int number, LocalDate column2, String phoneNmb, status column) {
		shipment.add(new ShipmentView(number, column2, phoneNmb, column));
	}

}