package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public class CompanyControlFormController implements Initializable {

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

	@FXML
	void deleteCompany(ActionEvent event) {
		CompanyView selectedCompany = companyView.getSelectionModel().getSelectedItem();
		if (selectedCompany != null) {
			System.out.println(selectedCompany.getBulstat());
			// tabwat exsepsani tuk tam mai
			// if DBremoveCorier(String selectedCourier.getPhoneNmb()) == true{ .. else
			// neuspeshno iztriwane i ne se trie ot tablicata
			companyView.getItems().remove(selectedCompany);
		} else
			resultLabel.setText("First select");
	}

	public void createCompany(ActionEvent event) {
		OpenNewForm.openNewForm("CreateCompanyForm.fxml", "Create company");
	}

	public void addToListTabel(String name, int bulstat) {
		companies.add(new CompanyView(name, bulstat));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.bulstatColumn.setCellValueFactory(new PropertyValueFactory<>("bulstat"));
		addToListTabel("cc", 5);
		addToListTabel("bc", 1);	
		companyView.setItems(companies);

	}

}
