package tu_varna.project.courier_system.roles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.User;

@Entity
public class Client extends User{
    @OneToMany(mappedBy="client")
	private List<Shipment> shipments= new ArrayList<Shipment>();

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
    
}
