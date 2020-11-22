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

import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.User;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class LoginServiceImplTest {

	LoginServiceImpl loginService;

	@Mock
	UserDaoImpl userDao;

	@BeforeEach
	void init() {
		loginService = new LoginServiceImpl();
		loginService.setUserDao(userDao);
	}

	@Test
	void testAuthenticateUserLogin() {
		final User user = new Client();
		user.setLoginUsername("user");
		user.setLoginPassword("user");
		when(userDao.getUserByUsername("user")).thenReturn(user);
		final User loginUser = loginService.authenticateUserLogin("user", "user");
		assertNotNull(loginUser);
		assertEquals(user.getLoginPassword(), loginUser.getLoginPassword());
		verify(userDao).getUserByUsername("user");
	}

}
