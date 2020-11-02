package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AboutShipmentFormController {

	@FXML
	private TextField number;

	@FXML
	private Label company;

	@FXML
	private Label from;

	@FXML
	private Label oORa;

	@FXML
	private Label senderName;

	@FXML
	private Label receiverName;

	@FXML
	private Label price;

	@FXML
	private Label expenseOf;

	@FXML
	private Label dueAmount;

	@FXML
	private Label to;

	@FXML
	private Label dateOfOrdering;

	@FXML
	private Label state;

	@FXML
	private Label type;

	@FXML
	private Label result;

	@FXML
	private boolean searchShipment(ActionEvent event) {

		int phoneNmb = Integer.parseInt(this.number.getText());
		boolean isFound = false;// dbsearch(phoneNmb));

		return isFound;
	}

	public void loadInfo(String state, String company, String dateOfOrdering, String from, String to, String oORa,
			String sender, String receiver, String type, String price, String expenseOf, String dueAmount) {
		this.state.setText(state);
		this.company.setText(company);
		this.dateOfOrdering.setText(dateOfOrdering);
		this.from.setText(from);
		this.to.setText(to);
		this.oORa.setText(oORa);
		this.senderName.setText(sender);
		this.receiverName.setText(receiver);
		this.type.setText(type);
		this.price.setText(price);
		this.expenseOf.setText(expenseOf);
		this.dueAmount.setText(dueAmount);
	}

}
