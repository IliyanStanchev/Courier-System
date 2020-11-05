package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Manager;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.OfficeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class OfficesControlFormController implements Initializable {
	
	UserService service= new UserServiceImpl();

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

	@FXML
	void createOffice(ActionEvent event) {
		OpenNewForm.openNewForm("CreateOfficeForm.fxml", "Create Office");

	}

	@FXML
	void deleteOffice(ActionEvent event) {
		OfficeView selectedOffice = officeView.getSelectionModel().getSelectedItem();
		if (selectedOffice != null) {
			service.deleteOffice(selectedOffice.getCode());
			officeView.getItems().remove(selectedOffice);
		} else
			resultLabel.setText("First select");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		this.agentColumn.setCellValueFactory(new PropertyValueFactory<>("agent")); // kak raboti?
		this.companyColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
		this.cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        List<Object[]> list= service.getAllOffices();
        for(Object[] column : list)
        {
        	Manager manager= (Manager) column[1];
        	Address address= (Address) column[3];
        	CourierFirm firm= (CourierFirm) column[2];
        	addToListTabel((Integer)column[0],manager.getManagerName(),firm.getCompanyName(),address.getCity());
        }
		officeView.setItems(offices);
		
		

	}

	public void addToListTabel(int code, String name, String phoneNmb, String company) {
		offices.add(new OfficeView(code, name, phoneNmb, company));
	}

}
