package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.helper.OpenNewForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CourierControlFormController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	private void addCourier(ActionEvent event) {
	OpenNewForm.openNewForm("CreateCourierForm.fxml","Create Courier");
		
	}

}


