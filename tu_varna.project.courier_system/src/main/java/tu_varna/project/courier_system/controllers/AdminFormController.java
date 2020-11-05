package tu_varna.project.courier_system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.helper.BuiltInForm;
import tu_varna.project.courier_system.helper.LogOut;
import tu_varna.project.courier_system.helper.OpenNewForm;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AdminFormController implements Initializable {
	
	private UserService service = new UserServiceImpl();
	
    public void setUserID(int id)
	    {
	    	this.id=id;
	    	welcomeUser.setText("Welcome "+ service.getUserName(id));
	    }

    @FXML
	private Label welcomeUser;
    
    private int id;
	
	@FXML
	private AnchorPane workPane;

	@Override
	public void initialize(URL location, ResourceBundle bb) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void controlClients(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ControlClientForm.fxml", workPane);
	}

	@FXML
	private void controlCouriers(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("CourierControlForm.fxml", workPane);
	}

	@FXML
	private void controlCourierSystems(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ControlCompanyForm.fxml", workPane);
	}

	@FXML
	private void controlOffices(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("OfficesControlForm.fxml", workPane);
	}

	@FXML
	private void controlShipments(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("ShipmentControlForm.fxml", workPane);
	}
	
	

	@FXML
	private void aboutCourier(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutCourierForm.fxml", workPane);
	}

	@FXML
	private void aboutCourierCompany(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutCourierCompanyForm.fxml", workPane);
	}

	@FXML
	private void aboutShipment(ActionEvent event) throws IOException {
		BuiltInForm.built_inForm("AboutShipmentForm.fxml", workPane);
	}

	@FXML
	private void logOut(ActionEvent event) throws IOException {
		LogOut.logOut(event);
		OpenNewForm.openNewForm("WelcomeForm.fxml", "Welcome");
	}


}
