package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
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
import javafx.fxml.FXMLLoader;

public class ShipmentControlFormController implements Initializable {

	@FXML
	private TextField name;

	@FXML
	private Label isSelectedLabel;

	@FXML
	private TableView<CompanyView> companyView;

	@FXML
	private TableColumn<CompanyView, String> nameColumn;

	@FXML
	private TableColumn<CompanyView, Integer> bulstatColumn;

	private ObservableList<CompanyView> company = FXCollections.observableArrayList();

	private FilteredList<CompanyView> filteredData = new FilteredList<>(company, b -> true);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	this.bulstatColumn.setCellValueFactory(new PropertyValueFactory<>("bulstat"));
		companyView.setItems(company);
		// zarejdane ot bd
		addToListTabel("fff", 555);
		addToListTabel("ffffff", 4444444);
		addToListTabel("aaafff", 5555);
		addToListTabel("bbbffffff", 44444);

		name.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(company -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (company.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}

				else
					return false; // Does not match.
			});
		});

		SortedList<CompanyView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(companyView.comparatorProperty());
		companyView.setItems(sortedData);
	}

	public void addToListTabel(String name, int bulstat) {
		company.add(new CompanyView(name, bulstat));
	}

	@FXML
	public void viewShipmentsList(ActionEvent event) {
		try {
			int bulstat = (companyView.getSelectionModel().getSelectedItems().get(0).getBulstat());
			try {
				FXMLLoader loader = OpenNewForm.openNewForm("ViewShipmentsForm.fxml", "List of shipments");
				ShipmentsViewFormController next = loader.getController();
				next.setChoosedCompany(bulstat);
			} catch (Exception e) {
				System.out.println("loader is null.");
			}

		} catch (Exception e) {
			isSelectedLabel.setText("First select! ");
		}

	}
}
