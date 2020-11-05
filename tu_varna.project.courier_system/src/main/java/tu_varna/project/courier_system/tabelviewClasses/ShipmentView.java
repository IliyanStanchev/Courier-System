package tu_varna.project.courier_system.tabelviewClasses;

import java.time.LocalDate;
import java.util.Date;

import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.Status;

public class ShipmentView {

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

	public ShipmentView(int number, LocalDate date, String phoneNmb, Status.status status) {
		this.number = number;
		this.phoneNmb = phoneNmb;
		this.date=date;
		this.status = status;
	}
	
	public ShipmentView(int number, String sender, Double price, String company) {
		this.number = number;
		this.sender = sender;
		this.price= price;
		this.company=company;
	}


	public ShipmentView(int number, String receiver, String company) {
		this.number = number;
		this.receiver = receiver;
		this.company=company;
	}
	
	public ShipmentView(int number, Address from, Address to) {
		this.number = number;
		this.from = from;
		this.to = to;
	}

	
	
	public ShipmentView(int number, Address from, Address to, LocalDate deliveryDate) {
		this.number = number;
		this.from = from;
		this.to = to;
		this.deliveryDate= deliveryDate;
	}
	


	public ShipmentView(int id, String courierInfo) {
		this.number=id;
		this.courierInfo=courierInfo;
		
	}

	public ShipmentView(int number) {
		this.number=number; 
		
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Address getFrom() {
		return from;
	}

	public void setFrom(Address from) {
		this.from = from;
	}

	public Address getTo() {
		return to;
	}

	public void setTo(Address to) {
		this.to = to;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPhoneNmb() {
		return phoneNmb;
	}

	public void setPhoneNmb(String phoneNmb) {
		this.phoneNmb = phoneNmb;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Status.status getStatus() {
		return status;
	}

	public void setStatus(Status.status status) {
		this.status = status;
	}

	public String getCourier() {
		return courierInfo;
	}

}
