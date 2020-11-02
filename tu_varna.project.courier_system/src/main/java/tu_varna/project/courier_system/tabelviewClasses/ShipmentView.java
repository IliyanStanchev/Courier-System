package tu_varna.project.courier_system.tabelviewClasses;

import java.util.Date;

public class ShipmentView {

	private int number;
	private String phoneNmb;
	private Date date;
	private String status;

	public ShipmentView(int number, Date date, String phoneNmb, String status) {
		this.number = number;
		this.phoneNmb = phoneNmb;
		setDate(date);
		this.status = status;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPhoneNmb() {
		return phoneNmb;
	}

	public void setPhoneNmb(String phoneNmb) {
		this.phoneNmb = phoneNmb;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
