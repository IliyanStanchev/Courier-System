package tu_varna.project.courier_system.services;

import java.util.List;

import tu_varna.project.courier_system.dao.ShipmentDao;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public interface ShipmentService {

	List<ShipmentView> getExpectedShipments(Client client);

	List<ShipmentView> getRequestedShipments(Client client);

	Shipment getShipmentByID(int id);

	List<ShipmentView> getPendingOrders(Company company);

	List<ShipmentView> getShipmentsByCompany(Company company);

	List<ShipmentView> getCourierActiveShipments(Courier courier);

	void setCourierOfShipment(Shipment shipment, Courier courier);

	void deleteShipment(Shipment shipment);

	List<ShipmentView> getCourierDeliveredShipments(Courier courier);

	boolean createShipment(Shipment shipment);

	long getOrders(Company company, boolean isSuccesful);

	void changeShipmentStatus(Shipment shipment, status status);

	void setShipmentDao(ShipmentDao shipmentDao);

}