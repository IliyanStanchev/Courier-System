package tu_varna.project.courier_system.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tu_varna.project.courier_system.dao.ShipmentDao;
import tu_varna.project.courier_system.dao.impl.ShipmentDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.services.ShipmentService;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.tabelviewClasses.ShipmentView;

public class ShipmentServiceImpl implements ShipmentService
{

	private ShipmentDao shipmentDao = new ShipmentDaoImpl();

	@Override
	public List<ShipmentView> getExpectedShipments(Client client)
	{

		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getExpectedShipments(client.getId());
		for (Object[] column : list)
		{
			int id = (Integer) column[0];
			String name = (String) column[1];
			Double price = (Double) column[2];
			Company company = (Company) column[3];
			Status.status status = (Status.status) column[4];
			toReturn.add(new ShipmentView(id, name, price, company.getCompanyName(), status));
		}
		return toReturn;

	}

	@Override
	public List<ShipmentView> getRequestedShipments(Client client)
	{

		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getRequestedShipments(client.getId());
		for (Object[] column : list)
		{
			int id = (Integer) column[0];
			User receiver = (User) column[1];
			Company company = (Company) column[2];
			Status.status status = (Status.status) column[3];

			toReturn.add(new ShipmentView(id, receiver.getName(), company.getCompanyName(), status));
		}
		return toReturn;

	}

	@Override
	public Shipment getShipmentByID(int id)
	{

		return shipmentDao.get(id);

	}

	@Override
	public List<ShipmentView> getPendingOrders(Company company)
	{

		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getPendingOrdersByCompany(company.getId());
		for (Object[] column : list)
		{
			int id = (Integer) column[0];
			User receiver = (User) column[1];
			User sender = (User) column[2];
			toReturn.add(new ShipmentView(id, receiver.getAddress(), sender.getAddress()));
		}
		return toReturn;

	}

	@Override
	public List<ShipmentView> getShipmentsByCompany(Company company)
	{
		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getShipmentsByCompany(company.getId());
		for (Object[] column : list)
		{
			int id = (Integer) column[0];
			LocalDate date = (LocalDate) column[1];
			String senderPhone = (String) column[2];
			Status.status status = (Status.status) column[3];
			toReturn.add(new ShipmentView(id, date, senderPhone, status));
		}
		return toReturn;
	}

	@Override
	public List<ShipmentView> getCourierActiveShipments(Courier courier)
	{
		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getCourierActiveShipments(courier.getId());
		for (Object[] column : list)
		{
			Client sender = (Client) column[1];
			Client receiver = (Client) column[2];
			Address from = sender.getAddress();
			Address to = receiver.getAddress();
			toReturn.add(new ShipmentView((Integer) column[0], from, to, (LocalDate) column[3]));
		}
		return toReturn;

	}

	@Override
	public void setCourierOfShipment(Shipment shipment, Courier courier)
	{
		shipment.setCourier(courier);
		shipmentDao.update(shipment);

	}

	@Override
	public void deleteShipment(Shipment shipment)
	{

		shipmentDao.delete(shipment);

	}

	@Override
	public List<ShipmentView> getCourierDeliveredShipments(Courier courier)
	{

		List<ShipmentView> toReturn = new ArrayList<ShipmentView>();
		List<Object[]> list = shipmentDao.getCourierDeliveredShipments(courier.getId());
		for (Object[] column : list)
		{
			Client sender = (Client) column[1];
			Client receiver = (Client) column[2];
			Address from = sender.getAddress();
			Address to = receiver.getAddress();
			toReturn.add(new ShipmentView((Integer) column[0], from, to, (LocalDate) column[3]));
		}
		return toReturn;
	}

	@Override
	public boolean createShipment(Shipment shipment)
	{
		return shipmentDao.save(shipment);

	}

	@Override
	public long getOrders(Company company, boolean isSuccesful)
	{
		if (isSuccesful)
		{
			return shipmentDao.getSuccesfulOrders(company.getId());
		} else
			return shipmentDao.getUnsuccesfulOrders(company.getId());
	}

	@Override
	public void changeShipmentStatus(Shipment shipment, status status)
	{
		shipment.setStatus(status);
		shipmentDao.update(shipment);

	}

	@Override
	public void setShipmentDao(ShipmentDao shipmentDao)
	{
		this.shipmentDao = shipmentDao;

	}

}
