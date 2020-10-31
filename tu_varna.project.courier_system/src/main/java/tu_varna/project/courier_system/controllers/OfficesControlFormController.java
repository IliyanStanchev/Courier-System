package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import tu_varna.project.courier_system.helper.OpenNewForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class OfficesControlFormController implements Initializable {

	@FXML

	public void viewOfficesList(ActionEvent event) {
		OpenNewForm.openNewForm("ViewOfficesForm.fxml","Offices list");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
