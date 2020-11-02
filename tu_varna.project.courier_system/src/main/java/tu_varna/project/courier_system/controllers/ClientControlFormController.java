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
import tu_varna.project.courier_system.tabelviewClasses.ClientView;
import tu_varna.project.courier_system.tabelviewClasses.CourierView;

public class ClientControlFormController implements Initializable {

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
			System.out.println(selectedClient.getPhoneNmb());
			// tabwat exsepsani tuk tam mai
			// if DBremoveCorier(String selectedClient.getPhoneNmb()) == true{ .. else
			// neuspeshno iztriwane i ne se trie ot tablicata
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
		clientView.setItems(clients);
		addToListTabel("aa", "ffdd");
		addToListTabel("bbb", "ddd");

	}

}
