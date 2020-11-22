package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.services.CompanyServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;

public class CompanyControlFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CompanyControlFormController.class);

	private CompanyService companyService = new CompanyServiceImpl();

	@FXML
	private TableView<CompanyView> companyView;
	@FXML
	private TableColumn<CompanyView, String> nameColumn;
	@FXML
	private TableColumn<CompanyView, Integer> bulstatColumn;
	@FXML
	private TextField bulstat;
	@FXML
	private Label resultLabel;

	private ObservableList<CompanyView> companies = FXCollections.observableArrayList();
	private FilteredList<CompanyView> filteredData = new FilteredList<>(companies, b -> true);

	@FXML
	private void createCompany(ActionEvent event) {
		OpenNewForm.openNewForm("CreateCompanyForm.fxml", "Create company");
	}

	@FXML
	private void deleteCompany(ActionEvent event) {
		CompanyView selectedCompany = companyView.getSelectionModel().getSelectedItem();
		if (selectedCompany != null) {
			companyService.deleteCompany(companyService.getCompanyByID(selectedCompany.getBulstat()));
			remove(selectedCompany);
			logger.info("Company with id: " + selectedCompany.getBulstat() + " successfully removed from database!");
		} else
			resultLabel.setText("First select");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.bulstatColumn.setCellValueFactory(new PropertyValueFactory<>("bulstat"));

		for (CompanyView company : companyService.getAllCompanies()) {
			addToListTabel(company);
		}

		companyView.setItems(companies);
		wrapListAndAddFiltering();
		companyView.setItems(filteredData);

	}

	private void remove(CompanyView item) {
		companies.remove(item);
		wrapListAndAddFiltering();
		companyView.setItems(filteredData);
	}

	private void wrapListAndAddFiltering() {
		bulstat.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(company -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (((Integer) company.getBulstat()).toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});
	}

	public void addToListTabel(CompanyView company) {
		companies.add(company);
	}

}
