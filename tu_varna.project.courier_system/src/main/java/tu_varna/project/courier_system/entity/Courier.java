package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Courier extends User {
	
	@ManyToOne
	private CourierFirm firm;
	
	 @OneToMany(cascade = CascadeType.MERGE,mappedBy="courier")
     private List<Shipment> shipmentsForDelivery= new ArrayList<Shipment>();

	public List<Shipment> getShipmentsForDelivery() {
		return shipmentsForDelivery;
	}

	public void setShipmentsForDelivery(List<Shipment> shipmentsForDelivery) {
		this.shipmentsForDelivery = shipmentsForDelivery;
	}

	public CourierFirm getFirm() {
		return firm;
	}

	public void setFirm(CourierFirm firm) {
		this.firm = firm;
	}

	@Override
	public String loadView() {
		return "CourierWorkspaceForm.fxml";
		
	}
	
	
	}
	


