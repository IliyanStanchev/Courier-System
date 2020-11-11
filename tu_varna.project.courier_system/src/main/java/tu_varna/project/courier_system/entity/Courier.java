package tu_varna.project.courier_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javafx.fxml.FXMLLoader;
import tu_varna.project.courier_system.controllers.CourierWorkspaceFormController;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.helper.OpenNewForm;

@Entity
public class Courier extends User {

	public Courier(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street, Company firm) {
		super(loginUsername, loginPassword, name, email, phoneNumber, country, city, street);
		this.firm = firm;

	}

	public Courier() {

	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Company firm;

	@OneToMany(mappedBy = "courier", cascade = CascadeType.REMOVE)
	private List<Shipment> shipmentsForDelivery = new ArrayList<Shipment>();

	public List<Shipment> getShipmentsForDelivery() {
		return shipmentsForDelivery;
	}

	public void setShipmentsForDelivery(List<Shipment> shipmentsForDelivery) {
		this.shipmentsForDelivery = shipmentsForDelivery;
	}

	public Company getFirm() {
		return firm;
	}

	public void setFirm(Company firm) {
		this.firm = firm;
	}

	@Override
	public String toString() {

		return super.toString() + "Courier [firm=" + firm + ", shipmentsForDelivery=" + shipmentsForDelivery + "]";
	}

	public List<Shipment> getDeliveredOrders() {
		List<Shipment> toReturn = new ArrayList<Shipment>();
		for (Shipment s : this.shipmentsForDelivery) {
			if (s.getStatus() == status.delivered) {
				toReturn.add(s);
			}
		}
		return toReturn;
	}

	public int getCancelledShipments() {
		int number = 0;
		for (Shipment s : this.shipmentsForDelivery) {
			if (s.getStatus() == status.declined)
				number++;
		}
		return number;

	}

	public List<Shipment> getShipmentsInProgress() {

		List<Shipment> toReturn = new ArrayList<Shipment>();
		for (Shipment s : shipmentsForDelivery) {
			if (s.getStatus() != status.declined && s.getStatus() != status.delivered
					&& s.getStatus() != status.accepted) {
				toReturn.add(s);
			}
		}
		return toReturn;
	}

	@Override
	public void loadController() {
		FXMLLoader loader = OpenNewForm.openNewForm("CourierWorkspaceForm.fxml", "Courier workspace");
		CourierWorkspaceFormController next = loader.getController();
		next.setUserID(this.getId());

	}

}
