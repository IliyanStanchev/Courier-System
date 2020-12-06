package tu_varna.project.courier_system.tabelviewClasses;

import java.time.LocalDate;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Type;

public class ShipmentView
{

	private int number;
	private String phoneNmb;
	private LocalDate date;
	private Status.status status;
	private Double price;
	private String company;
	private String sender;
	private String receiver;
	private Address from;
	private Address to;
	private LocalDate deliveryDate;
	private String courierInfo;
	private Type.type type;

	public ShipmentView(int number)
	{
		this.number = number;

	}

	public ShipmentView(int number, Address from, Address to)
	{
		this.number = number;
		this.from = from;
		this.to = to;
	}

	public ShipmentView(int number, Address from, Address to, LocalDate deliveryDate)
	{
		this.number = number;
		this.from = from;
		this.to = to;
		this.deliveryDate = deliveryDate;
	}

	public ShipmentView(int number, LocalDate date, String phoneNmb, Status.status status)
	{
		this.number = number;
		this.phoneNmb = phoneNmb;
		this.date = date;
		this.status = status;
	}

	public ShipmentView(int id, String courierInfo)
	{
		this.number = id;
		this.courierInfo = courierInfo;

	}

	public ShipmentView(int number, String sender, Double price, String company, Status.status status)
	{
		this.number = number;
		this.sender = sender;
		this.price = price;
		this.company = company;
		this.status = status;
	}

	public ShipmentView(int number, String receiver, String company, Status.status status)
	{
		this.number = number;
		this.receiver = receiver;
		this.company = company;
		this.status = status;
	}

	public ShipmentView(Integer number, String sender, Type.type type, Double price)
	{
		this.number = number;
		this.sender = sender;
		this.setType(type);
		this.price = price;
	}

	public String getCompany()
	{
		return company;
	}

	public String getCourier()
	{
		return courierInfo;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public LocalDate getDeliveryDate()
	{
		return deliveryDate;
	}

	public Address getFrom()
	{
		return from;
	}

	public int getNumber()
	{
		return number;
	}

	public String getPhoneNmb()
	{
		return phoneNmb;
	}

	public Double getPrice()
	{
		return price;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public String getSender()
	{
		return sender;
	}

	public Status.status getStatus()
	{
		return status;
	}

	public Address getTo()
	{
		return to;
	}

	public Type.type getType()
	{
		return type;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public void setDate(LocalDate date)
	{
		this.date = date;
	}

	public void setDeliveryDate(LocalDate deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public void setFrom(Address from)
	{
		this.from = from;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public void setPhoneNmb(String phoneNmb)
	{
		this.phoneNmb = phoneNmb;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public void setStatus(Status.status status)
	{
		this.status = status;
	}

	public void setTo(Address to)
	{
		this.to = to;
	}

	public void setType(Type.type type)
	{
		this.type = type;
	}

}
