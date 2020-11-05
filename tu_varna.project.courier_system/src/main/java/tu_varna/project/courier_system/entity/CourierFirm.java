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

import org.hibernate.annotations.Cascade;

import tu_varna.project.courier_system.entity.Status.status;


@Entity(name="Firm")
@Table(name="Firm")
public class CourierFirm {
	
	@Id
	@Column(name="bulstat", unique=true)
	private int id;
	
	@Column(unique=true)
	private String companyName;
	

	private Manager manager;
	
	private Address address;
	
	private LocalDate dateOfCreation;
	

	@OneToMany(mappedBy="firm",cascade=CascadeType.REMOVE)
	private List<Courier> employees= new ArrayList<Courier>();
	
	@OneToMany(mappedBy="firm",cascade=CascadeType.REMOVE)
	private List<Office> offices = new ArrayList<Office>();
	
	@OneToMany(mappedBy="firm",cascade=CascadeType.REMOVE)
	private List<Shipment> shipments = new ArrayList<Shipment>();
	
	
	
	public CourierFirm() {
		this.dateOfCreation= LocalDate.now();
		
	}
	
	public CourierFirm(int id, String companyName, String manager, String phone_number, String country, String city, String street) {
		
		this.id = id;
		this.companyName = companyName;
		this.manager = new Manager(manager,phone_number);
		this.setAddress(new Address(country,city,street));
		this.dateOfCreation= LocalDate.now();
	}

	public Manager getManager() {
		return manager;
	}
	
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public List<Office> getOffices() {
		return offices;
	}
	
	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Courier> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Courier> employees) {
		this.employees = employees;
	}
	
	public String toString() {
		return "CourierFirm [id=" + id + ", companyName=" + companyName + ", manager=" + manager + "]";
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getSuccesfulOrders()
	{
		int number=0;
		for(Shipment s : this.shipments)
		{
			if(s.getStatus()==status.delivered)
				number++;
		}
		return number;
	}
	public int getUnsuccesfulOrders()
	{
		int number=0;
		for(Shipment s : this.shipments)
		{
			if(s.getStatus()==status.declined)
				number++;
		}
		return number;
	}
	public int getActiveOrders()
	{
		int number=0;
		for(Shipment s : this.shipments)
		{
			if(s.getStatus()!=status.delivered && s.getStatus()!=status.declined) 
				number++;
		}
		return number;
	}
	
	public List<Shipment> getPendingOrders()
	{
		List<Shipment> toReturn= new ArrayList<Shipment>();
		for(Shipment s : this.shipments)
		{
			if(s.getStatus()==status.pending)
			{
				toReturn.add(s);
			}
		}
		return toReturn;
	}

	public LocalDate getDateOfCreation() {
		
		return dateOfCreation;
	}

	
	
	
}
