package tu_varna.project.courier_system.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import tu_varna.project.courier_system.dao.em.entityManager;
import tu_varna.project.courier_system.dao.impl.CompanyDaoImpl;
import tu_varna.project.courier_system.dao.impl.ShipmentDaoImpl;
import tu_varna.project.courier_system.dao.impl.UserDaoImpl;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;

@RunWith(JUnitPlatform.class)
class ShipmentDaoTest {
	
	ShipmentDao shipmentDao;
	CompanyDao companyDao;
	UserDao userDao;
	
	@BeforeEach
	public void init()
	{
		entityManager.initEntityManager("persistence_test");
		shipmentDao=new ShipmentDaoImpl();
		companyDao= new CompanyDaoImpl();
		userDao=new UserDaoImpl();
	}

	@Test
	void testSaveAndThenGet() {
		Shipment shipment = new Shipment();
		shipmentDao.save(shipment);
		Shipment dbShipment= new Shipment();
		dbShipment=shipmentDao.get(1);
		assertNotNull(dbShipment);
		assertEquals(shipment.getId(),dbShipment.getId());
		
	}


	@Test
	void testUpdateAndThenGet() {
		Shipment shipment = new Shipment();
		shipmentDao.save(shipment);
		Shipment dbShipment= new Shipment();
		dbShipment=shipmentDao.get(1);
		dbShipment.setShipmentPrice(12.50);
		shipmentDao.update(dbShipment);
		Shipment updatedShipment= shipmentDao.get(1);
		assertNotNull(updatedShipment);
		assertEquals(12.50,updatedShipment.getShipmentPrice());
	}

	@Test
	void testDelete() {
		Shipment shipment = new Shipment();
		shipmentDao.save(shipment);
		Shipment dbShipment = shipmentDao.get(1);
		shipmentDao.delete(dbShipment);
		assertEquals(null, shipmentDao.get(1));
	}



	@Test
	void testGetSuccesfulOrders() {
		Company company = new Company();
		company.setId(1);
		companyDao.save(company);
		
		Shipment shipment = new Shipment();
		shipment.setStatus(status.declined);
		shipment.setFirm(company);
		shipmentDao.save(shipment);
		Shipment newShipment= new Shipment();
		newShipment.setFirm(company);
		newShipment.setStatus(status.accepted);
		shipmentDao.save(newShipment);
		assertEquals(1,shipmentDao.getSuccesfulOrders(1));
		
	}

	@Test
	void testGetUnsuccesfulOrders() {
		Company company = new Company();
		company.setId(1);
		companyDao.save(company);
		
		Shipment shipment0 = new Shipment();
		shipment0.setStatus(status.declined);
		shipment0.setFirm(company);
		shipmentDao.save(shipment0);
		Shipment shipment1= new Shipment();
		shipment1.setFirm(company);
		shipment1.setStatus(status.accepted);
		shipmentDao.save(shipment1);
		Shipment shipment2= new Shipment();
		shipment2.setFirm(company);
		shipment2.setStatus(status.declined);
		shipmentDao.save(shipment2);
		assertEquals(2,shipmentDao.getUnsuccesfulOrders(1));
	}

}
