package tu_varna.project.courier_system.tabelviewClasses;

public class ClientView
{
	private String name;
	private String phoneNmb;

	public ClientView(String name, String phoneNmb)
	{
		super();
		this.name = name;
		this.phoneNmb = phoneNmb;
	}

	public String getName()
	{
		return name;
	}

	public String getPhoneNmb()
	{
		return phoneNmb;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPhoneNmb(String phoneNmb)
	{
		this.phoneNmb = phoneNmb;
	}

}
