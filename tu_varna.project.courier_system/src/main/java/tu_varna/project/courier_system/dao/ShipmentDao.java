package tu_varna.project.courier_system.dao;

import java.util.List;

import tu_varna.project.courier_system.entity.Shipment;

public interface ShipmentDao {

	boolean save(Shipment t);
	
	Shipment get(int id);

	void update(Shipment t);

	void delete(Shipment t);

	List<Object[]> getExpectedShipments(int client_id);

	List<Object[]> getRequestedShipments(int client_id);

	List<Object[]> getCourierDeliveredShipments(int courier_id);

	List<Object[]> getCourierActiveShipments(int courier_id);

	List<Object[]> getShipmentsByCompany(int bulstat);

	List<Object[]> getPendingOrdersByCompany(int bulstat);

	long getSuccesfulOrders(int bulstat);

	long getUnsuccesfulOrders(int bulstat);

}