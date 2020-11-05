package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.Type.type;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;
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

public class ShipmentsViewFormController implements Initializable {
	
	private UserService service= new UserServiceImpl();

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

	private CourierFirm choosedCompany;


	public void setChoosedCompany(CourierFirm choosedCompany) {
		this.choosedCompany = choosedCompany;
		
	}
    public void viewShipmentsList()
    {
		
    	List<Object[]> list= service.getShipmentsList(choosedCompany.getId());
		for(Object[] column: list)
		{
			addToList((Integer)column[0], (LocalDate)column[1], (String)column[2], (Status.status)column[3]);
		}
		
		
        /*
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
					return false; // Does not match.
			});
		});

		SortedList<ShipmentView> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(shipmentsView.comparatorProperty());
		shipmentsView.setItems(sortedData);
		*/

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		shipmentsView.setItems(shipment);
		
		}

	public void addToList(int number, LocalDate column2, String phoneNmb, status column) {
		shipment.add(new ShipmentView(number, column2, phoneNmb, column));
	}

	@FXML
	public void createShipment(ActionEvent event) {
	        
			//int number = (shipmentsView.getSelectionModel().getSelectedItems().get(0).getNumber());
			try {
				FXMLLoader loader = OpenNewForm.openNewForm("RequestShipmentForm.fxml", "Create Shipment");
				RequestShipmentFormController next = loader.getController();
				next.getCompanyForAdmin(choosedCompany);
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("loader is null.");
				//e.printStackTrace();
			}

		
		

	}

	@FXML
	public void deleteShipment(ActionEvent event) {
		ShipmentView selectedShipment = shipmentsView.getSelectionModel().getSelectedItem();
		if (selectedShipment != null) {
			System.out.println(selectedShipment.getNumber());
			service.deleteShipment(selectedShipment.getNumber());
			shipmentsView.getItems().remove(selectedShipment);
		} else
			resultLabel.setText("First select");
	}

}