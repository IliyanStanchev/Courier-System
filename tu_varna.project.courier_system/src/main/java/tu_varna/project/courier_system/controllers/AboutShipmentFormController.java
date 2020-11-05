package tu_varna.project.courier_system.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.services.UserService;
import tu_varna.project.courier_system.services.UserServiceImpl;

public class AboutShipmentFormController {
    
	
	private UserService service= new UserServiceImpl();
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
	private void searchShipment(ActionEvent event) {

		int shipment_id = Integer.parseInt(this.number.getText());
		Shipment shipment = service.SearchShipmentByID(shipment_id);
		if(shipment!=null)
		{
			String oORa="address";
			if(shipment.getToOffice()!=null)
			{
				oORa="office";
			}
			loadInfo(shipment.getStatus(),shipment.getFirm().getCompanyName(),shipment.getDateCreated(),shipment.getSender().getAddress().toString(),shipment.getReceiver().getAddress().toString(),oORa,
				shipment.getSender().getName(),shipment.getReceiver().getName(),shipment.getType(),shipment.getShipmentPrice());
		}
		else
		{
			result.setText("Shipment not found!");
		}
		

		
	}

	public void loadInfo(Status.status state, String company, LocalDate dateOfOrdering, String from, String to, String oORa,
			String sender, String receiver, Type.type type, double price) {
		this.state.setText(state.toString());
		this.company.setText(company);
		this.dateOfOrdering.setText(dateOfOrdering.toString());
		this.from.setText(from);
		this.to.setText(to);
		this.oORa.setText(oORa);
		this.senderName.setText(sender);
		this.receiverName.setText(receiver);
		this.type.setText(type.toString());
		this.price.setText(Double.toString(price));
	}

}
