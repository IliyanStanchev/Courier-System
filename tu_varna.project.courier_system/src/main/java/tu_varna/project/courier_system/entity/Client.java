package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import javafx.fxml.FXMLLoader;
import tu_varna.project.courier_system.controllers.ClientWorkspaceFormController;
import tu_varna.project.courier_system.controllers.CourierWorkspaceFormController;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;


@Entity
public class Client extends User{
	
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy="receiver")
	private List<Shipment> receivedShipments= new ArrayList<Shipment>();
    
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy="sender")
  	private List<Shipment> sendShipments= new ArrayList<Shipment>();
    
    

	
    public Client() {
    
    }

	public Client(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street) {
		super(loginUsername, loginPassword, name, email, phoneNumber, country, city, street);
		// TODO Auto-generated constructor stub
	}

	public List<Shipment> getReceivedShipments() {
		return receivedShipments;
	}

	public void setReceivedShipments(List<Shipment> receivedShipments) {
		this.receivedShipments = receivedShipments;
	}

	public List<Shipment> getSendShipments() {
		return sendShipments;
	}

	public void setSendShipments(List<Shipment> sendShipments) {
		this.sendShipments = sendShipments;
	}

	@Override
	public String loadView() {
		return "ClientWorkspaceForm.fxml";
		
	}

	public List<ShipmentView> getExpectedShipments() {
		List<ShipmentView> toReturn= new ArrayList<ShipmentView>();
		for(Shipment shipment : receivedShipments)
		{
			if(shipment.getStatus()!=status.delivered && shipment.getStatus()!= status.declined)
			{
				toReturn.add(new ShipmentView(shipment.getId(),shipment.getSender().getName(),shipment.getShipmentPrice(),shipment.getFirm().getCompanyName()));
			}
		}
		return toReturn;
	}
    
	public List<ShipmentView> getRequestedShipments() {
		List<ShipmentView> toReturn= new ArrayList<ShipmentView>();
		for(Shipment shipment : sendShipments)
		{
			
				toReturn.add(new ShipmentView(shipment.getId(),shipment.getReceiver().getName(),shipment.getFirm().getCompanyName()));
			
		}
		return toReturn;
	}

	@Override
	public void loadController(FXMLLoader loader) {
		ClientWorkspaceFormController next= loader.getController();
		next.setUserID(this.getId());
		
	}
	
	public int getReceivedShipments(int company_id)
	{
		int number=0;
		for(Shipment s : receivedShipments)
		{
			if(s.getFirm().getId()== company_id && s.getStatus()== status.delivered)
			{
				number++;
			}
		}
		return number;
	}
	
	public int getDeclinedShipments(int company_id)
	{
		int number=0;
		for(Shipment s : receivedShipments)
		{
			if(s.getFirm().getId()== company_id && s.getStatus()== status.declined)
			{
				number++;
			}
		}
		return number;
	}
    
	public List<ShipmentView> getShipmentInProcess() {
		List<ShipmentView> toReturn= new ArrayList<ShipmentView>();
		for(Shipment shipment : receivedShipments)
		{
			if(shipment.getStatus()!=status.delivered && shipment.getStatus()!= status.declined)
			{
				if(shipment.getCourier()!=null)
				{
				    toReturn.add(new ShipmentView(shipment.getId(),shipment.getCourier().getName()+ " " + shipment.getCourier().getPhoneNumber()));
				}
				else
				{
					toReturn.add(new ShipmentView(shipment.getId(),"Courier not set yet!"));
				}
					
			}
		}
		return toReturn;
		}
	}
     
    

