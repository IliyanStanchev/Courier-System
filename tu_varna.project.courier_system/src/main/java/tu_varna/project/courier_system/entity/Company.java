package tu_varna.project.courier_system.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tu_varna.project.courier_system.entity.Status.status;

@Entity(name = "Firm")
@Table(name = "Firm")
public class Company {

	@Id
	@Column(name = "bulstat", unique = true)
	private int id;

	@Column(unique = true)
	private String companyName;

	private Manager manager;

	private Address address;

	private LocalDate dateOfCreation;

	@OneToMany(mappedBy = "firm", cascade = CascadeType.REMOVE)
	private List<Courier> employees = new ArrayList<Courier>();

	@OneToMany(mappedBy = "firm", cascade = CascadeType.REMOVE)
	private List<Office> offices = new ArrayList<Office>();

	@OneToMany(mappedBy = "firm", cascade = CascadeType.REMOVE)
	private List<Shipment> shipments = new ArrayList<Shipment>();

	public Company(int id, String companyName, String manager, String phone_number, String country, String city,
			String street) {

		this.id = id;
		this.companyName = companyName;
		this.manager = new Manager(manager, phone_number);
		this.setAddress(new Address(country, city, street));
		this.dateOfCreation = LocalDate.now();
	}

	public Company() {
		this.dateOfCreation = LocalDate.now();

	}

	public Address getAddress() {
		return address;
	}

	public String getCompanyName() {
		return companyName;
	}

	public LocalDate getDateOfCreation() {

		return dateOfCreation;
	}

	public List<Courier> getEmployees() {
		return employees;
	}

	public int getId() {
		return id;
	}

	public Manager getManager() {
		return manager;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmployees(List<Courier> employees) {
		this.employees = employees;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public int getSuccesfulOrders() {
		int number = 0;
		for (Shipment s : this.shipments) {
			if (s.getStatus() == status.delivered || s.getStatus() == status.accepted)
				number++;
		}
		return number;
	}

	public int getUnsuccesfulOrders() {
		int number = 0;
		for (Shipment s : this.shipments) {
			if (s.getStatus() == status.declined)
				number++;
		}
		return number;
	}

	public int getActiveOrders() {
		int number = 0;
		for (Shipment s : this.shipments) {
			if (s.getStatus() != status.delivered && s.getStatus() != status.declined
					&& s.getStatus() != status.accepted)
				number++;
		}
		return number;
	}

}
