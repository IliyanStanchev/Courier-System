package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class AboutCourierFormController {

	UserService service = new UserServiceImpl();

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

		try {

			Courier courier = (Courier) service.SearchUser(this.name.getText(), this.phoneNmb.getText());
			if (courier != null) {
				loadInfo(courier);
			} else {
				resultLabel.setText("Courier not found!");
			}
		} catch (ClassCastException e) {

			resultLabel.setText("User with this username and phone is not Courier!");

		}
	}

	public void loadInfo(Courier courier) {

		this.nameL.setText(courier.getName());
		this.companyName.setText(courier.getFirm().getCompanyName());
		this.username.setText(courier.getLoginUsername());
		this.password.setText(courier.getLoginPassword());
		this.email.setText(courier.getEmail());
		this.phoneN.setText(courier.getPhoneNumber());
		this.address.setText(courier.getAddress().toString());
		this.deliveredShipments.setText(Integer.toString(courier.getDeliveredOrders().size()));
		this.date.setText(LocalDate.now().toString());

	}

}
