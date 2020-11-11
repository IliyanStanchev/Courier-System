package tu_varna.project.courier_system.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private Status.status status;
	@Enumerated(EnumType.STRING)
	private Type.type type;
	private LocalDate dateCreated;
	private LocalDate dateShipped;
	private double shipmentPrice;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Client sender;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Client receiver;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Company firm;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Courier courier;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Office toOffice;

	public Shipment() {
		// TODO Auto-generated constructor stub
	}

	public Shipment(Type.type type, LocalDate localDate, double shipmentPrice, User sender, User receiver,
			Company firm, Office toOffice) {
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.setToOffice(toOffice);
		this.status = Status.status.pending;
	}

	public Shipment(Type.type type, LocalDate localDate, double shipmentPrice, User sender, User receiver,
			Company firm) {
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.status = Status.status.pending;
	}

	public Shipment(Status.status status,
			Type.type type, LocalDate localDate, double shipmentPrice, User sender,
			User receiver,User courier, Company firm) {
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.courier= (Courier) courier;
		this.status = status;		
	}

	@Override
	public String toString() {
		return "Shipment id=" + id + ", status=" + status + ", type=" + type + "date created: " + dateCreated + "\n";

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

	public void setStatus(Status.status status) {
		this.status = status;
	}

	public Type.type getType() {
		return type;
	}

	public void setType(Type.type type) {
		this.type = type;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(LocalDate dateShipped) {
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
		this.sender = sender;
	}

	public Client getReceiver() {
		return receiver;
	}

	public void setReceiver(Client receiver) {
		this.receiver = receiver;
	}

	public Company getFirm() {
		return firm;
	}

	public void setFirm(Company firm) {
		this.firm = firm;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Office getToOffice() {
		return toOffice;
	}

	public void setToOffice(Office toOffice) {
		this.toOffice = toOffice;
	}

}
