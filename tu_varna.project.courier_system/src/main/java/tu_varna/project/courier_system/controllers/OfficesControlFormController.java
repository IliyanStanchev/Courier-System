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
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.OfficeView;

public class OfficesControlFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(OfficesControlFormController.class);

	CompanyService companyService = new CompanyServiceImpl();

	@FXML
	private TableView<OfficeView> officeView;
	@FXML
	private TableColumn<OfficeView, Integer> codeColumn;
	@FXML
	private TableColumn<OfficeView, String> agentColumn;
	@FXML
	private TableColumn<OfficeView, String> companyColumn;
	@FXML
	private TableColumn<OfficeView, String> cityColumn;
	@FXML
	private TextField company;
	@FXML
	private TextField city;
	@FXML
	private Label resultLabel;

	private ObservableList<OfficeView> offices = FXCollections.observableArrayList();
	private FilteredList<OfficeView> filteredData = new FilteredList<>(offices, b -> true);

	@FXML
	private void createOffice(ActionEvent event) {
		OpenNewForm.openNewForm("CreateOfficeForm.fxml", "Create Office");
	}

	@FXML
	private void deleteOffice(ActionEvent event) {

		OfficeView selectedOffice = officeView.getSelectionModel().getSelectedItem();
		if (selectedOffice != null) {
			companyService.deleteOffice(companyService.getOfficeById(selectedOffice.getCode()));
			remove(selectedOffice);
			logger.info("Office [ " + selectedOffice.getCompany() + " , " + selectedOffice.getCode()
					+ " ] successfully deleted by administrator!");
		} else
			resultLabel.setText("First select");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		this.agentColumn.setCellValueFactory(new PropertyValueFactory<>("agent"));
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
		this.cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

		for (OfficeView office : companyService.getAllOffices()) {

			addToListTabel(office);
		}

		officeView.setItems(offices);
		wrapListAndAddFiltering();
		officeView.setItems(filteredData);
	}

	private void remove(OfficeView item) {
		offices.remove(item);
		wrapListAndAddFiltering();
		officeView.setItems(filteredData);
	}

	private void wrapListAndAddFiltering() {
		city.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(office -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (office.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else
					return false;
			});
		});

	}

	public void addToListTabel(OfficeView office) {
		offices.add(office);
	}

}
