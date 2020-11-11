package tu_varna.project.courier_system.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	public Office(String name, String country, String city, String streetN, String managerName, String managerPhone,
			Company firm) {
		super();
		this.name = name;
		this.address = new Address(country, city, streetN);
		this.manager = new Manager(managerName, managerPhone);
		this.firm = firm;
	}

	public Office() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getFirm() {
		return firm;
	}

	public void setFirm(Company firm) {
		this.firm = firm;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

}
