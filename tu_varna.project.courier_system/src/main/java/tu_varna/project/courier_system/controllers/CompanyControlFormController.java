package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
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
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;

public class CompanyControlFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(CompanyControlFormController.class);

	private UserService serv = new UserServiceImpl();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.bulstatColumn.setCellValueFactory(new PropertyValueFactory<>("bulstat"));
		List<Object[]> list = serv.getAllCompanies();
		for (Object[] column : list) {
			addToListTabel((String) column[0], (Integer) column[1]);
		}
		companyView.setItems(companies);
		wrapListAndAddFiltering();
		companyView.setItems(filteredData);

	}

	@FXML
	private void createCompany(ActionEvent event) {
		OpenNewForm.openNewForm("CreateCompanyForm.fxml", "Create company");
	}

	@FXML
	private void deleteCompany(ActionEvent event) {
		CompanyView selectedCompany = companyView.getSelectionModel().getSelectedItem();
		if (selectedCompany != null) {
			serv.DeleteCompany(selectedCompany.getBulstat());
			remove(selectedCompany);
			logger.info("Company with id: " + selectedCompany.getBulstat() + " successfully removed from database!");
		} else
			resultLabel.setText("First select");
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

	private void remove(CompanyView item) {
		companies.remove(item);
		wrapListAndAddFiltering();
		companyView.setItems(filteredData);
	}

	public void addToListTabel(String name, int bulstat) {
		companies.add(new CompanyView(name, bulstat));
	}

}
