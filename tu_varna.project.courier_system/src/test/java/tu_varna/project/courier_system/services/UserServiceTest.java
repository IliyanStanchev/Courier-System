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

import tu_varna.project.courier_system.dao.impl.UserDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;
import tu_varna.project.courier_system.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)

class UserServiceTest {

	UserService userService;

	@Mock
	UserDaoImpl userDao;

	@BeforeEach
	void init() {
		userService = new UserServiceImpl();
		userService.setUserDao(userDao);
	}

	@Test
	void testCreateClient() {
		final Client client = new Client("client", "client", "client", "client", "client", "client", "client",
				"client");
		when(userDao.save(client)).thenReturn(true);
		assertEquals(true, userService.createClient(client));
		verify(userDao).save(client);

	}

	@Test
	void testCreateCourier() {
		final Courier courier = new Courier("client", "client", "client", "client", "client", "client", "client",
				"client", new Company());
		when(userDao.save(courier)).thenReturn(true);
		assertEquals(true, userService.createCourier(courier));
		verify(userDao).save(courier);
	}

	@Test
	void testDeleteUser() {

		User user = userDao.get(1);
		userService.deleteUser(user);
		verify(userDao).delete(user);

	}

	@Test
	void testGetUserByID() {
		final User client = new Client("client", "client", "client", "client", "client", "client", "client", "client");
		client.setId(1);
		when(userDao.get(1)).thenReturn(client);
		User user = userService.getUserByID(1);
		assertNotNull(user);
		verify(userDao).get(1);
	}

	@Test
	void testChangeUserPhone() {
		final User client = new Client("client", "client", "client", "client", "client", "client", "client", "client");
		userService.changeUserPhone(client, "8");
		verify(userDao).update(client);

	}

}
