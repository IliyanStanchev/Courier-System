package tu_varna.project.courier_system.tabelviewClasses;

public class CourierView {
	private String name;
	private String phoneNmb;
	private String company;

	public CourierView(String name, String phoneNmb, String company) {

		this.name = name;
		this.phoneNmb = phoneNmb;
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNmb() {
		return phoneNmb;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNmb(String phoneNmb) {
		this.phoneNmb = phoneNmb;
	}

}
