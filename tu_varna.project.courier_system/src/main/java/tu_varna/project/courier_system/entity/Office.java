package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private Address address;

	private Manager manager;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Company firm;

	@OneToMany(mappedBy = "toOffice", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Shipment> shipment = new ArrayList<Shipment>();

	public Office() {

	}

	public Office(String name, String country, String city, String streetN, String managerName, String managerPhone,
			Company firm) {
		super();
		this.name = name;
		this.address = new Address(country, city, streetN);
		this.manager = new Manager(managerName, managerPhone);
		this.firm = firm;
	}

	public Address getAddress() {
		return address;
	}

	public Company getFirm() {
		return firm;
	}

	public int getId() {
		return id;
	}

	public Manager getManager() {
		return manager;
	}

	public String getName() {
		return name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setFirm(Company firm) {
		this.firm = firm;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void setName(String name) {
		this.name = name;
	}

}
