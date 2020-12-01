package tu_varna.project.courier_system.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import tu_varna.project.courier_system.dao.em.entityManager;
import tu_varna.project.courier_system.dao.impl.CompanyDaoImpl;
import tu_varna.project.courier_system.dao.impl.UserDaoImpl;
import tu_varna.project.courier_system.entity.Admin;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.User;

@RunWith(JUnitPlatform.class)
class UserDaoTest {
	
	UserDao userDao;
	CompanyDao companyDao;
	
	
	@BeforeEach
	void init() {
		entityManager.initEntityManager("persistence_test");
		userDao=new UserDaoImpl();	
		companyDao=new CompanyDaoImpl();
	}
	
	

	@Test
	void testSaveAndGetAfter() {
		User user= new Client();
		user.setName("Iliyan");
		userDao.save(user);
		User dbUser= userDao.get(1);
		assertNotNull(dbUser);
		assertEquals(user.getName(),dbUser.getName());
		
	}

	@Test
	void testSaveAndThenUpdate() {
		User user= new Client();
		user.setName("Iliyan");
		userDao.save(user);
		User dbUser= userDao.get(1);
		dbUser.setName("Desislava");
		userDao.update(dbUser);
		User updatedUser=userDao.get(1);
		assertEquals("Desislava",updatedUser.getName());
		
	}

	@Test
	void testDelete() {
		User user= new Client();
		userDao.save(user);
		User dbUser= userDao.get(1);
		userDao.delete(dbUser);
		User deletedUser = userDao.get(1);
		assertEquals(null,deletedUser);
	}

	
	void testGetUserByPhone() {
		User user= new Client();
		user.setPhoneNumber("1");
		userDao.save(user);
		User dbUser= userDao.getUserByPhone("1");
		assertNotNull(dbUser);
		assertEquals(user.getId(),dbUser.getId());
	}

	@Test
	void testGetUserByUsername() {
		User user= new Client();
		user.setLoginUsername("user");
		userDao.save(user);
		User dbUser= userDao.getUserByUsername("user");
		assertNotNull(dbUser);
		assertEquals(user.getId(),dbUser.getId());
	}

	@Test
	void testGetAllClients() {
		userDao.save(new Courier());
		userDao.save(new Client());
		userDao.save(new Admin());
		userDao.save(new Client());
		assertEquals(2,userDao.getAllClients().size());
	}

	@Test
	void testGetAllCouriers() {
		Company company = new Company();
		company.setCompanyName("company");
		company.setId(1);
		Courier courier= new Courier();
		courier.setName("Iliyan");
		courier.setPhoneNumber("0897875640");
		courier.setFirm(company);
		companyDao.save(company);
		userDao.save(courier);
		userDao.save(new Client());
		userDao.save(new Admin());
		userDao.save(new Client());
		userDao.save(courier);
		assertNotNull(userDao.getAllCouriers());
	}
 
}
