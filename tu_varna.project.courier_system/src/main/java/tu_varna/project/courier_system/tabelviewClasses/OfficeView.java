package tu_varna.project.courier_system.tabelviewClasses;

public class OfficeView
{
	private int code;
	private String agent;
	private String company;
	private String city;
	private String name;

	public OfficeView(int id, String agent, String company, String city)
	{

		this.code = id;
		this.agent = agent;
		this.company = company;
		this.city = city;
	}

	public OfficeView(int id, String name)
	{
		this.code = id;
		this.name = name;
	}

	public String getAgent()
	{
		return agent;
	}

	public String getCity()
	{
		return city;
	}

	public String getCompany()
	{
		return company;
	}

	public void setAgent(String agent)
	{
		this.agent = agent;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int id)
	{
		this.code = id;
	}

	@Override
	public String toString()
	{
		return name;
	}

}
