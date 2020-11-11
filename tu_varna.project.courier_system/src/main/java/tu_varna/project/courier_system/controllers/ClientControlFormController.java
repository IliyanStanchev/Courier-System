package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

public class ClientControlFormController implements Initializable {

	private static final Logger logger = LogManager.getLogger(ClientControlFormController.class);

	private UserService service = new UserServiceImpl();

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
			logger.info("Client [" + selectedClient.getName() + " , " + selectedClient.getPhoneNmb()
					+ " ]  successfully deleted by administrator! ");

		} else
			resultLabel.setText("First select");
	}

	public void addToListTabel(String name, String phoneNmb) {
		clients.add(new ClientView(name, phoneNmb));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));

		for (Object[] column : service.getAllClients()) {
			addToListTabel((String) column[0], (String) column[1]);
		}

		clientView.setItems(clients);

	}

}
