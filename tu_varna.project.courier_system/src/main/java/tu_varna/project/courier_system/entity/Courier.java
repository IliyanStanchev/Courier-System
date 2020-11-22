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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Company firm;

	@OneToMany(mappedBy = "courier", cascade = CascadeType.REMOVE)
	private List<Shipment> shipmentsForDelivery = new ArrayList<Shipment>();

	public Courier() {

	}

	public Courier(String loginUsername, String loginPassword, String name, String email, String phoneNumber,
			String country, String city, String street, Company firm) {
		super(loginUsername, loginPassword, name, email, phoneNumber, country, city, street);
		this.firm = firm;

	}

	public Company getFirm() {
		return firm;
	}

	public List<Shipment> getShipmentsForDelivery() {
		return shipmentsForDelivery;
	}

	public void setFirm(Company firm) {
		this.firm = firm;
	}

	public void setShipmentsForDelivery(List<Shipment> shipmentsForDelivery) {
		this.shipmentsForDelivery = shipmentsForDelivery;
	}

	public int getCancelledShipments() {
		int number = 0;
		for (Shipment s : this.shipmentsForDelivery) {
			if (s.getStatus() == status.declined)
				number++;
		}
		return number;

	}

	@Override
	public void loadController() {
		FXMLLoader loader = OpenNewForm.openNewForm("CourierWorkspaceForm.fxml", "Courier workspace");
		CourierWorkspaceFormController next = loader.getController();
		next.setUser(this);

	}

}
