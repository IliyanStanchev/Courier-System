package tu_varna.project.courier_system.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class Shipment {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private Status.status status;
	@Enumerated(EnumType.STRING)
	private Type.type type;
	private Date dateCreated;
	private Date dateShipped;
	private double shipmentPrice;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Client sender;
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Client receiver;
	@ManyToOne
	private Office office;
	@ManyToOne(cascade = {CascadeType.MERGE})
	private Courier courier;
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
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
	
	
	
	public Client getSender() {
		return sender;
	}
	public void setSender(Client sender) {
		this.sender =  sender;
	}
	public Client getReceiver() {
		return receiver;
	}
	public void setReceiver(Client receiver) {
		this.receiver = receiver;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
	
	

}
