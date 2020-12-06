package tu_varna.project.courier_system.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address
{

	private String country;
	private String city;
	private String street;

	public Address()
	{

	}

	public Address(String country, String city, String street)
	{
		super();
		this.country = country;
		this.city = city;
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public String getCountry()
	{
		return country;
	}

	public String getStreet()
	{
		return street;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	@Override
	public String toString()
	{
		return country + " " + city + " " + street;
	}

}
