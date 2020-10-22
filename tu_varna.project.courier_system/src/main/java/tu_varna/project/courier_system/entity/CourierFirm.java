package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Firm")
public class CourierFirm {
	
	@Id
	@Column(name="bulstat", unique=true)
	private int id;
	
	private String companyName;
	
	@OneToMany(mappedBy="firm")
	private List<Courier> employees= new ArrayList<Courier>();
	
	@OneToMany(mappedBy="firm")
	private List<Office> offices = new ArrayList<Office>();
	
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
    
}
