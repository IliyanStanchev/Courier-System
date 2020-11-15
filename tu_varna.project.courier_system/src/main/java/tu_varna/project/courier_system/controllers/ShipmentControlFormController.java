package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;


public class ShipmentControlFormController implements Initializable {

	private UserService service = new UserServiceImpl();
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
		List<Object[]> list = service.getAllCompanies();
		for (Object[] column : list) {
			addToListTabel((String) column[0], (Integer) column[1]);
		}

		SortedList<CompanyView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(companyView.comparatorProperty());

		companyView.setItems(company);
		wrapListAndAddFiltering();
		companyView.setItems(filteredData);

	}

	@FXML
	private void viewShipmentsList(ActionEvent event) {
		isSelectedLabel.setText("");
		CompanyView selectedCompany = companyView.getSelectionModel().getSelectedItem();
		if (selectedCompany != null) {
			try {
				FXMLLoader loader = OpenNewForm.openNewForm("ViewShipmentsForm.fxml", "List of shipments");
				ShipmentsViewFormController next = loader.getController();
				next.setChoosedCompany(service.getCompanyByID(selectedCompany.getBulstat()));
				next.viewShipmentsList();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("loader is null.");
			}
		}

		else
			isSelectedLabel.setText("First select");

	}
	
	private void wrapListAndAddFiltering() {

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
					return false;
			});
		});
	}

	
	private void addToListTabel(String name, int bulstat) {
		company.add(new CompanyView(name, bulstat));
	}
}
