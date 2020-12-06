package tu_varna.project.courier_system.tabelviewClasses;

public class CompanyView
{
	private String name;
	private int bulstat;

	public CompanyView(int bulstat, String name)
	{
		this.bulstat = bulstat;
		this.name = name;

	}

	public int getBulstat()
	{
		return bulstat;
	}

	public String getName()
	{
		return name;
	}

	public void setBulstat(int bulstat)
	{
		this.bulstat = bulstat;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
