package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;
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
import javafx.scene.input.KeyEvent;

public class CourierControlFormController implements Initializable {

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

	//private FilteredList<CourierView> filteredData = new FilteredList<>(couriers, b -> true);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.phoneNColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb")); // kak raboti?
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));

		courierView.setItems(couriers);
		addToListTabel("aa", "ddd", "fff");
		addToListTabel("bbb", "ddd", "fff");
		
	}

	/*@FXML
	private void filter(KeyEvent event) {

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
					return false; // Does not match.
			});
		});
		SortedList<CourierView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(courierView.comparatorProperty());
		courierView.setItems(sortedData);
	}*/

	@FXML
	void removeCourier(ActionEvent event) {
		CourierView selectedCourier = courierView.getSelectionModel().getSelectedItem();
		if(selectedCourier != null) {
		System.out.println(selectedCourier.getPhoneNmb());
		// tabwat exsepsani tuk tam mai
		//if DBremoveCorier(String selectedCourier.getPhoneNmb()) == true{ .. else neuspeshno iztriwane i ne se trie ot tablicata
		courierView.getItems().remove(selectedCourier);
		}
		else
			resultLabel.setText("First select");
	}

	@FXML
	private void addCourier(ActionEvent event) {
		OpenNewForm.openNewForm("CreateCourierForm.fxml", "Create Courier");

	}

	public void addToListTabel(String name, String phoneNmb, String company) {
		couriers.add(new CourierView(name, phoneNmb, company));
	}

}
