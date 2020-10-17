package tu_varna.project.courier_system.roles;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tu_varna.project.courier_system.entity.CourierFirm;
import tu_varna.project.courier_system.entity.User;

@Entity
public class Courier extends User {
	
	@ManyToOne
	private CourierFirm firm;

	public CourierFirm getFirm() {
		return firm;
	}

	public void setFirm(CourierFirm firm) {
		this.firm = firm;
	}

	
	}
	


