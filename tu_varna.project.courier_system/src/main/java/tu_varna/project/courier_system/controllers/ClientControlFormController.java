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
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.impl.UserServiceImpl;
import tu_varna.project.courier_system.tabelviewClasses.ClientView;

public class ClientControlFormController implements Initializable
{

	private static final Logger logger = LogManager.getLogger(ClientControlFormController.class);

	private UserService userService = new UserServiceImpl();

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
	private FilteredList<ClientView> filteredData = new FilteredList<>(clients, b -> true);

	@FXML
	private void createClient(ActionEvent event)
	{
		OpenNewForm.openNewForm("CreateClientForm.fxml", "Create client");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.phoneNmbColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNmb"));
		for (ClientView client : userService.getAllClients())
		{
			addToListTabel(client);
		}
		clientView.setItems(clients);
		wrapListAndAddFiltering();
		clientView.setItems(filteredData);
	}

	@FXML
	private void removeClient(ActionEvent event)
	{
		ClientView selectedClient = clientView.getSelectionModel().getSelectedItem();
		if (selectedClient != null)
		{
			userService.deleteUser(userService.getUserByPhone(selectedClient.getPhoneNmb()));
			remove(selectedClient);
			logger.info("Client [" + selectedClient.getName() + " , " + selectedClient.getPhoneNmb()
					+ " ]  successfully deleted by administrator! ");
		} else
			resultLabel.setText("First select");
	}

	private void wrapListAndAddFiltering()
	{

		phoneNmb.textProperty().addListener((observable, oldValue, newValue) ->
		{
			filteredData.setPredicate(client ->
			{
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (client.getPhoneNmb().toLowerCase().indexOf(lowerCaseFilter) != -1)
				{
					return true;
				} else
					return false;
			});
		});
	}

	private void remove(ClientView item)
	{
		clients.remove(item);
		wrapListAndAddFiltering();
		clientView.setItems(filteredData);
	}

	public void addToListTabel(ClientView client)
	{
		clients.add(client);
	}

}
