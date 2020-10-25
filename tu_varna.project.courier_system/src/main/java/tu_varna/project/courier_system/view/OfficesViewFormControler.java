package tu_varna.project.courier_system.view;

import java.net.URL;
import java.util.ResourceBundle;
import tu_varna.project.courier_system.helper.OpenNewForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;


public class OfficesViewFormControler implements Initializable {

	@FXML

	public void addOffice(ActionEvent event) {
		OpenNewForm.openNewForm("CreateOfficeForm.fxml","Create Office");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
