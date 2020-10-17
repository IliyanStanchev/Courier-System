package tu_varna.project.courier_system.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import tu_varna.project.courier_system.roles.Client;


@Entity
public class Shipment {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private Status.status status;
	private Type.type type;
	private Date dateCreated;
	private Date dateShipped;
	private double shipmentPrice;
	
	@ManyToOne
	private Client client;
	@ManyToOne
	private Office office;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public Status.status getStatus() {
		return status;
	}
	public Type.type getType() {
		return type;
	}
	public void setStatus(Status.status status) {
		this.status = status;
	}
	
	
	public void setType(Type.type type) {
		this.type = type;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateShipped() {
		return dateShipped;
	}
	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}
	public double getShipmentPrice() {
		return shipmentPrice;
	}
	public void setShipmentPrice(double shipmentPrice) {
		this.shipmentPrice = shipmentPrice;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
	
	

}
