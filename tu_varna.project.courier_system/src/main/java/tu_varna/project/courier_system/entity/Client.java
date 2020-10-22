package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends User{
	
    @OneToMany(mappedBy="receiver")
	private List<Shipment> receivedShipments= new ArrayList<Shipment>();
    
    @OneToMany(mappedBy="sender")
  	private List<Shipment> sendShipments= new ArrayList<Shipment>();

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
    
     
    
}
