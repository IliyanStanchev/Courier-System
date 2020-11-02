package tu_varna.project.courier_system.tabelviewClasses;

public class OfficeView {
	private int code;
	private String agent;
	private String company;
	private String city;

	public OfficeView(int code, String agent, String company, String city) {
		super();
		this.code = code;
		this.agent = agent;
		this.company = company;
		this.city = city;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
