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
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ClientStatisticsFormController implements Initializable {
	
	UserService service= new UserServiceImpl();

	@FXML
	private TextField name;

	@FXML
	private TextField phoneNmb;

	@FXML
	private TextField cancelledShipments;

	@FXML
	private TextField recievedShipments;

	@FXML
	private TableView<ShipmentView> shipmentsInProcessView;

	@FXML
	private TableColumn<ShipmentView, Integer> numberColumn;

	@FXML
	private TableColumn<ShipmentView, String> courierColumn;

	@FXML
	private Label resultLabel;

	private ObservableList<ShipmentView> shipmentsInProcess = FXCollections.observableArrayList();

	private Client wantedClient;
	
	private int company_id;

	@FXML
	void searchClient(ActionEvent event) {

		/// ZABEESKA LQNCE - da izvejda samo tezi shipmenti na klienta, koito sa kum
		/// tekushatat kompaniq;
		// obicam te mece <3
         try {
        	 this.wantedClient = (Client) service.SearchUser(this.name.getText(), this.phoneNmb.getText());
        	 if (this.wantedClient != null) {
        		 if(wantedClient.getShipmentInProcess()!=null)
        		 {
     			for(ShipmentView s: wantedClient.getShipmentInProcess())
     			{
     				addToListTabel(s.getNumber(),s.getCourier());
     			}
        		 }
     			
     			this.cancelledShipments.setText(Integer.toString(wantedClient.getDeclinedShipments(company_id)));
     			 this.recievedShipments.setText(Integer.toString(wantedClient.getReceivedShipments(company_id)));
     			this.shipmentsInProcessView.setItems(shipmentsInProcess);

     		
            }else
     			resultLabel.setText("The client doesn't exist. Check client's phone number and name.");
     		name.requestFocus();
         }
         catch(ClassCastException e)
         {
        	 resultLabel.setText("User with this username and phone is not Client!");
        	 
         }
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		this.courierColumn.setCellValueFactory(new PropertyValueFactory<>("courier"));
		this.shipmentsInProcessView.setItems(shipmentsInProcess);
		this.shipmentsInProcessView.setItems(shipmentsInProcess);

	}

	public void addToListTabel(int number, String courierInfo) {// wika se ot bd prawi String ot nomer i ime v
																// courierInfo;
		shipmentsInProcess.add(new ShipmentView(number, courierInfo));
	}
	
	public void setCompanyID(int id)
	{
		this.company_id=id;
	}

}
