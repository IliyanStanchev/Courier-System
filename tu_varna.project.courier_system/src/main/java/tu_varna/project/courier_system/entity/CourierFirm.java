package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tu_varna.project.courier_system.roles.Courier;



@Entity
@Table(name="courier_firm")
public class CourierFirm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companyName;
	private String bulstat;
	@OneToMany//(mappedBy="firm")
	private List<Courier> employees= new ArrayList<Courier>();
	
	@OneToMany//(mappedBy="firm")
	private List<Office> offices = new ArrayList<Office>();
	
	public List<Office> getOffices() {
		return offices;
	}
	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBulstat() {
		return bulstat;
	}
	public void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}
	public List<Courier> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Courier> employees) {
		this.employees = employees;
	}
    
}
