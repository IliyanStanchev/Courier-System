package tu_varna.project.courier_system.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Office;
@RunWith(JUnitPlatform.class)
class OfficeDaoImplTest {
	
	OfficeDaoImpl officeDao;
	CompanyDaoImpl companyDao;
	
	@BeforeEach
	void init()
	{
		entityManager.initEntityManager("persistence_test");
		officeDao=new OfficeDaoImpl();
		companyDao= new CompanyDaoImpl();
		
	}

	@Test
	void testSaveAndThenGet() {
		Office office = new Office();
		officeDao.save(office);
		Office dbOffice = officeDao.get(1);
		assertNotNull(dbOffice);
		assertEquals(office.getId(),dbOffice.getId());
		
	}

	@Test
	void testSaveAndThenUpdate() {
		Office office= new Office();
		officeDao.save(office);
	    Office dbOffice = officeDao.get(1);
	    dbOffice.setName("office");
	    officeDao.update(dbOffice);
	    Office updatedOffice=officeDao.get(1);
	    assertNotNull(updatedOffice);
	    assertEquals(dbOffice.getName(),updatedOffice.getName());
	}

	@Test
	void testDelete() {
		Office office= new Office();
		officeDao.save(office);
		Office dbOffice = officeDao.get(1);
		officeDao.delete(dbOffice);
		assertEquals(null,officeDao.get(1));
	}

	@Test
	void testGetAllOffices() {
		Company company = new Company();
		company.setId(123);
		companyDao.save(company);
		Office office= new Office("office","office", "office", "office", "office", "office", company);
		officeDao.save(office);
		assertEquals(1,officeDao.getAllOffices().size());
	}

	@Test
	void testGetOfficesByFirm() {
	
		Company company = new Company();
		company.setId(123);
		companyDao.save(company);
		Office office = new Office();
		officeDao.save(office);
		office = new Office();
		office.setFirm(company);
		officeDao.save(office);
		assertEquals(1,officeDao.getOfficesByFirm(123).size());
	}

}
