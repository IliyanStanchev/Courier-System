package tu_varna.project.courier_system.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class AboutCourierFormController {
	
	UserService service= new UserServiceImpl();

	@FXML
	private AnchorPane workPane;

	@FXML
	private Label companyName;

	@FXML
	private Label nameL;

	@FXML
	private Label username;

	@FXML
	private Label password;

	@FXML
	private Label email;

	@FXML
	private Label phoneN;

	@FXML
	private Label address;

	@FXML
	private Label date;
	@FXML
	private TextField name;
	@FXML
	private TextField phoneNmb;

	@FXML
	private Label resultLabel;

	@FXML
	private Label deliveredShipments;

	@FXML
	private Label phoneNValidationLabel;

	@FXML
	private Label nameValidationLabel;

	@FXML
	public void searchCourier(ActionEvent event) {
		String name = this.name.getText();
		String phoneNmb = this.phoneNmb.getText();
		User usr = service.SearchUser(name, phoneNmb);
		Courier courier;
		if(usr instanceof Courier)
		{
			
			courier = (Courier)usr;
			loadInfo(courier.getName(),courier.getFirm().getCompanyName(),courier.getLoginUsername(),courier.getLoginPassword(),courier.getEmail(),courier.getPhoneNumber(),courier.getAddress().getCountry(),courier.getAddress().getCity(),courier.getAddress().getStreet(),Integer.toString(courier.getDeliveredOrders().size()));
		}
		else
		{
			resultLabel.setText("Courier not found!");
		}
	}

	public void loadInfo(String name, String company, String username, String password, String email, String phoneNmb,
			String country, String city, String streetN, String deliveredShipments) {
		this.nameL.setText(name);
		this.companyName.setText(company);
		this.username.setText(username);
		this.password.setText(password);
		this.email.setText(email);
		this.address.setText(country + " " + city + " " + streetN);
		this.deliveredShipments.setText(deliveredShipments);
		this.date.setText(LocalDate.now().toString());
	}

	

}
