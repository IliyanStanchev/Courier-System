package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.services.ShipmentServiceImpl;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class AboutCourierFormController {

	UserService userService = new UserServiceImpl();
	ShipmentService shipmentService = new ShipmentServiceImpl();

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
	private TextField phoneNmb;
	@FXML
	private Label resultLabel;
	@FXML
	private Label deliveredShipments;

	private void loadInfo(Courier courier) {
		this.nameL.setText(courier.getName());
		this.companyName.setText(courier.getFirm().getCompanyName());
		this.username.setText(courier.getLoginUsername());
		this.password.setText(courier.getLoginPassword());
		this.email.setText(courier.getEmail());
		this.phoneN.setText(courier.getPhoneNumber());
		this.address.setText(courier.getAddress().toString());
		this.deliveredShipments.setText(Integer.toString(shipmentService.getCourierDeliveredShipments(courier).size()));
		this.date.setText(LocalDate.now().toString());
	}

	@FXML
	private void searchCourier(ActionEvent event) {
		this.cleanFields();
		resultLabel.setText("");
		try {
			Courier courier = (Courier) userService.getUserByPhone(this.phoneNmb.getText());
			if (courier != null) {
				loadInfo(courier);
			} else {
				resultLabel.setText("Courier not found!");
			}
		} catch (ClassCastException e) {
			resultLabel.setText("User with this username and phone is not Courier!");
		}
	}

	private void cleanFields() {
		this.nameL.setText("");
		this.companyName.setText("");
		this.username.setText("");
		this.password.setText("");
		this.email.setText("");
		this.phoneN.setText("");
		this.address.setText("");
		this.deliveredShipments.setText("");
		this.date.setText("");

	}

}
