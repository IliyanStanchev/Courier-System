package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public class CourierControlFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CourierControlFormController.class);

	private UserService userService = new UserServiceImpl();

	@FXML
	private TableView<CourierView> courierView;
	@FXML
	private TableColumn<CourierView, String> nameColumn;
	@FXML
	private TableColumn<CourierView, String> phoneNColumn;
	@FXML
	private TableColumn<CourierView, String> companyColumn;
	@FXML
	private TextField name;
	@FXML
	private Label resultLabel;

	private ObservableList<CourierView> couriers = FXCollections.observableArrayList();
	private FilteredList<CourierView> filteredData = new FilteredList<>(couriers, b -> true);

	@FXML
	private void addCourier(ActionEvent event) {
		OpenNewForm.openNewForm("CreateCourierForm.fxml", "Create Courier");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.phoneNColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

		for (CourierView courier : userService.getAllCouriers()) {
			addToListTable(courier);
		}
		SortedList<CourierView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(courierView.comparatorProperty());

		courierView.setItems(couriers);
		wrapListAndAddFiltering();
		courierView.setItems(filteredData);

	}

	@FXML
	void removeCourier(ActionEvent event) {

		CourierView selectedCourier = courierView.getSelectionModel().getSelectedItem();
		if (selectedCourier != null) {
			userService.deleteUser(userService.getUserByPhone(selectedCourier.getPhoneNmb()));
			remove(selectedCourier);
			logger.info("Courier [" + selectedCourier.getName() + " , " + selectedCourier.getPhoneNmb()
					+ " ] successfully deleted by administrator!");
		} else
			resultLabel.setText("First select");
	}

	private void wrapListAndAddFiltering() {

		name.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(courier -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (courier.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}

				else
					return false;

			});
		});
	}

	private void remove(CourierView item) {
		couriers.remove(item);
		wrapListAndAddFiltering();
		courierView.setItems(filteredData);
	}

	public void addToListTable(CourierView courier) {
		couriers.add(courier);
	}

}
