package tu_varna.project.courier_system.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String country;
	private String city;
	private String street;

	public Address(String country, String city, String street) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
	}

	public Address() {

	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		return country + " " + city + " " + street;
	}

}
