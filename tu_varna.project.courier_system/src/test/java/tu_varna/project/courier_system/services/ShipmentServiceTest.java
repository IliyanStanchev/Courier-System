package tu_varna.project.courier_system.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tu_varna.project.courier_system.dao.impl.ShipmentDaoImpl;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status;
import tu_varna.project.courier_system.services.impl.ShipmentServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ShipmentServiceTest {

	ShipmentService shipmentService;

	@Mock
	ShipmentDaoImpl shipmentDao;

	@BeforeEach
	void init() {
		shipmentService = new ShipmentServiceImpl();
		shipmentService.setShipmentDao(shipmentDao);
	}

	@Test
	void testGetShipmentByID() {
		final Shipment shipment = new Shipment();
		shipment.setId(1);
		when(shipmentDao.get(1)).thenReturn(shipment);
		Shipment returnedShipment = shipmentService.getShipmentByID(1);
		assertNotNull(returnedShipment);
		verify(shipmentDao).get(1);

	}

	@Test
	void testSetCourierOfShipment() {
		final Courier courier = new Courier();
		courier.setId(1);
		final Shipment shipment = new Shipment();
		shipmentService.setCourierOfShipment(shipment, courier);
		verify(shipmentDao).update(shipment);
	}

	@Test
	void testDeleteShipment() {
		Shipment shipment = shipmentDao.get(1);
		shipmentService.deleteShipment(shipment);
		verify(shipmentDao).delete(shipment);
	}

	@Test
	void testCreateShipment() {
		final Shipment shipment = new Shipment();
		when(shipmentDao.save(shipment)).thenReturn(true);
		assertEquals(true, shipmentService.createShipment(shipment));
		verify(shipmentDao).save(shipment);
	}

	@Test
	void testChangeShipmentStatus() {
		final Shipment shipment = new Shipment();
		shipmentService.changeShipmentStatus(shipment, Status.status.declined);
		verify(shipmentDao).update(shipment);
	}

}
