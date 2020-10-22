package tu_varna.project.courier_system.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


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
	


