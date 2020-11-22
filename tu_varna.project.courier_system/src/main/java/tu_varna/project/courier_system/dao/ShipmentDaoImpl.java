package tu_varna.project.courier_system.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import tu_varna.project.courier_system.dao.manager.entityManager;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;

public class ShipmentDaoImpl implements BaseDao<Shipment> {

	@Override
	public Shipment get(int id) {
		return entityManager.getEntityManager().find(Shipment.class, id);
	}

	@Override
	public boolean save(Shipment t) {
		try {
			entityManager.executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public void update(Shipment t) {
		entityManager.executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Shipment t) {
		entityManager.executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getShipmentsByCourier(int courier_id, Status.status status) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.dateCreated,u.phoneNumber,e.status FROM Shipment e, User u, Courier c WHERE e.courier = c.id AND c.id=: courier_id AND e.receiver = u.id AND e.status=: status")
				.setParameter("courier_id", courier_id).setParameter("status", status).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getExpectedShipments(int client_id) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, u.name, e.shipmentPrice,e.firm ,e.status FROM Shipment e, User u WHERE e.receiver = u.id AND u.id=: id AND e.status!='delivered' AND e.status!='declined' AND e.status!='accepted'")
				.setParameter("id", client_id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getRequestedShipments(int client_id) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.receiver, e.firm ,e.status FROM Shipment e, User u WHERE e.sender = u.id AND u.id=: id")
				.setParameter("id", client_id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCourierDeliveredShipments(int courier_id) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.sender, e.receiver ,e.dateShipped FROM Shipment e, User u WHERE e.courier = u.id AND u.id=: id AND (e.status='delivered' OR e.status='declined' OR e.status= 'accepted')")
				.setParameter("id", courier_id).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCourierActiveShipments(int courier_id) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.sender, e.receiver ,e.dateShipped FROM Shipment e, User u WHERE e.courier = u.id AND u.id=: id AND e.status!= 'accepted' AND e.status!='declined' AND e.status!='delivered'")
				.setParameter("id", courier_id).getResultList();

	}

	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getShipmentsByCompany(int bulstat) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.dateCreated,u.phoneNumber,e.status FROM Shipment e, Firm f, User u WHERE f.id=: bulst AND f.id = e.firm AND e.receiver=u.id")
				.setParameter("bulst", bulstat).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPendingOrdersByCompany(int bulstat) {
		return entityManager.getEntityManager().createQuery(
				"SELECT e.id, e.sender,e.receiver FROM Shipment e, Firm f WHERE f.id=: bulstat AND f.id = e.firm AND (e.status='pending' OR e.status='in_proccess_of_return')")
				.setParameter("bulstat", bulstat).getResultList();
	}

	public long getSuccesfulOrders(int bulstat) {
		return (long) entityManager.getEntityManager().createQuery(
				"SELECT count(e) FROM Shipment e, Firm f WHERE f.id=: bulstat AND f.id = e.firm AND (e.status='accepted' OR e.status='delivered')")
				.setParameter("bulstat", bulstat).getSingleResult();

	}

	public long getUnsuccesfulOrders(int bulstat) {
		return (long) entityManager.getEntityManager().createQuery(
				"SELECT count(e) FROM Shipment e, Firm f WHERE f.id=: bulstat AND f.id = e.firm AND e.status='declined'")
				.setParameter("bulstat", bulstat).getSingleResult();

	}

}
