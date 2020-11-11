package tu_varna.project.courier_system.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Manager {

	String managerName;
	String managerPhone;

	public Manager(String managerName, String managerPhone) {

		this.managerName = managerName;
		this.managerPhone = managerPhone;
	}

	public Manager() {

	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

}
