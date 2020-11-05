package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.List;
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
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ClientView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public class ClientControlFormController implements Initializable {
	
	private UserService service= new UserServiceImpl();

	@FXML
	private TextField phoneNmb;

	@FXML
	private TableView<ClientView> clientView;

	@FXML
	private TableColumn<ClientView, String> nameColumn;

	@FXML
	private TableColumn<ClientView, String> phoneNmbColumn;

	@FXML
	private Label resultLabel;

	private ObservableList<ClientView> clients = FXCollections.observableArrayList();

	public void createClient(ActionEvent event) {
		OpenNewForm.openNewForm("CreateClientForm.fxml", "Create client");
	}

	@FXML
	void removeClient(ActionEvent event) {
		ClientView selectedClient = clientView.getSelectionModel().getSelectedItem();
		if (selectedClient != null) {
			service.DeleteUser(service.SearchUserByPhone(selectedClient.getPhoneNmb()));
			clientView.getItems().remove(selectedClient);
		} else
			resultLabel.setText("First select");
	}

	public void addToListTabel(String name, String phoneNmb) {
		clients.add(new ClientView(name, phoneNmb));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		List<Object[]> list = service.getAllClients();
	        for(Object[] column : list)
	        {
	        	addToListTabel((String)column[0],(String)column[1]);
	        }
		clientView.setItems(clients);
		

	}

}
