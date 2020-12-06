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
import javax.persistence.OneToOne;

@Entity
public class Shipment
{

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
	@OneToOne(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Notification notification;

	public Shipment()
	{

	}

	public Shipment(Status.status status, Type.type type, LocalDate localDate, double shipmentPrice, User sender,
			User receiver, Company firm)
	{
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.status = status;
	}

	public Shipment(Type.type type, LocalDate localDate, double shipmentPrice, User sender, User receiver, Company firm)
	{
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.status = Status.status.pending;
	}

	public Shipment(Type.type type, LocalDate localDate, double shipmentPrice, User sender, User receiver, Company firm,
			Office toOffice)
	{
		this.type = type;
		this.dateCreated = localDate;
		this.shipmentPrice = shipmentPrice;
		this.sender = (Client) sender;
		this.receiver = (Client) receiver;
		this.firm = firm;
		this.setToOffice(toOffice);
		this.status = Status.status.pending;
	}

	public Courier getCourier()
	{
		return courier;
	}

	public LocalDate getDateCreated()
	{
		return dateCreated;
	}

	public LocalDate getDateShipped()
	{
		return dateShipped;
	}

	public Company getFirm()
	{
		return firm;
	}

	public int getId()
	{
		return id;
	}

	public Client getReceiver()
	{
		return receiver;
	}

	public Client getSender()
	{
		return sender;
	}

	public double getShipmentPrice()
	{
		return shipmentPrice;
	}

	public Status.status getStatus()
	{
		return status;
	}

	public Office getToOffice()
	{
		return toOffice;
	}

	public Type.type getType()
	{
		return type;
	}

	public void setCourier(Courier courier)
	{
		this.courier = courier;
	}

	public void setDateCreated(LocalDate dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public void setDateShipped(LocalDate dateShipped)
	{
		this.dateShipped = dateShipped;
	}

	public void setFirm(Company firm)
	{
		this.firm = firm;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setReceiver(Client receiver)
	{
		this.receiver = receiver;
	}

	public void setSender(Client sender)
	{
		this.sender = sender;
	}

	public void setShipmentPrice(double shipmentPrice)
	{
		this.shipmentPrice = shipmentPrice;
	}

	public void setStatus(Status.status status)
	{
		this.status = status;
	}

	public void setToOffice(Office toOffice)
	{
		this.toOffice = toOffice;
	}

	public void setType(Type.type type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Shipment id=" + id + ", status=" + status + ", type=" + type + "date created: " + dateCreated + "\n";

	}

}
