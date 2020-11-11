package tu_varna.project.courier_system.tabelviewClasses;

public class CompanyView {
	private String name;
	private int bulstat;

	public CompanyView(String name, int bulstat) {
		this.name = name;
		this.bulstat = bulstat;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", bulstat=" + bulstat + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBulstat() {
		return bulstat;
	}

	public void setBulstat(int bulstat) {
		this.bulstat = bulstat;
	}
}
