package tu_varna.project.courier_system.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Status.status;

public class ShipmentDaoImpl extends entityManager implements BaseDao<Shipment> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Shipment> getAll() {
		Query query = getEntityManager().createQuery("SELECT e FROM Shipment e");
		return query.getResultList();
	}

	@Override
	public boolean save(Shipment t) {
		try {
			executeInsideTransaction(entityManager -> entityManager.persist(t));
		} catch (PersistenceException e) {
			System.out.println("Error saving object!");
			return false;
		}
		return true;

	}

	@Override
	public void update(Shipment t) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));

	}

	@Override
	public void delete(Shipment t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));

	}

	@Override
	public Shipment get(int id) {
		return getEntityManager().find(Shipment.class, id);
	}

	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getListShipmentsByFirm(int bulstat) {
		return getEntityManager().createQuery(
				"SELECT e.id, e.dateCreated,u.phoneNumber,e.status FROM Shipment e, Firm f, User u WHERE f.id=: bulst AND f.id = e.firm AND e.receiver=u.id")
				.setParameter("bulst", bulstat).getResultList();

	}

	public Shipment getShipmentByNumAndPhone(int id, String phone) {

		Shipment ship;
		try {
			ship = (Shipment) getEntityManager().createQuery(
					"Select s FROM Shipment s, User u WHERE s.id=: id AND s.receiver = u.id AND u.phoneNumber=: phone")
					.setParameter("id", id).setParameter("phone", phone).getSingleResult();
		} catch (NoResultException e) {
			ship = null;
		}
		return ship;
	}

	@SuppressWarnings({ "unchecked" })
	public List<Object[]> getListShipmentsByCourier(int courier_id, Status.status status) {
		return getEntityManager().createQuery(
				"SELECT e.id, e.dateCreated,u.phoneNumber,e.status FROM Shipment e, User u, Courier c WHERE e.courier = c.id AND c.id=: courier_id AND e.receiver = u.id AND e.status=: status")
				.setParameter("courier_id", courier_id).setParameter("status", status).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllShipments() {
		return getEntityManager().createQuery(
				"SELECT e.id, e.dateCreated,u.phoneNumber,e.status FROM Shipment e, User u WHERE e.receiver = u.id")
				.getResultList();
	}

	public void ChangeShipmentStatus(Shipment shipment, status status) {

		shipment.setStatus(status);
		update(shipment);

	}

	public void setShipmentCourier(Shipment shipment, Courier courier) {
		shipment.setCourier(courier);
		shipment.setStatus(status.taken_by_courier);
		update(shipment);

	}

	public void setShipmentDateShipped(Shipment shipment, LocalDate now) {
		shipment.setDateShipped(now);
		update(shipment);

	}

}
