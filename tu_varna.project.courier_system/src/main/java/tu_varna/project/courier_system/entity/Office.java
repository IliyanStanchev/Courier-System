package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tu_varna.project.courier_system.embeddable.Address;

@Entity
public class Office {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private Address address;
	
	@ManyToOne
	private CourierFirm firm;
	
	@OneToMany//(mappedBy="office")
	private List<Shipment> shipments= new ArrayList<Shipment>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CourierFirm getFirm() {
		return firm;
	}

	public void setFirm(CourierFirm firm) {
		this.firm = firm;
	}
	
	


}
