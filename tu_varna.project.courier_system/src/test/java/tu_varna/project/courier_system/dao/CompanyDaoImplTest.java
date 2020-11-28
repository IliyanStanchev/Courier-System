package tu_varna.project.courier_system.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import tu_varna.project.courier_system.entity.Company;

@RunWith(JUnitPlatform.class)
class CompanyDaoImplTest {

	private CompanyDaoImpl companyDao= new CompanyDaoImpl();
	
	@BeforeEach
	void init() {
		entityManager.initEntityManager("persistence_test");
		companyDao=new CompanyDaoImpl();
	}
	
	@Test
	void testSaveAndThenGet() {
		Company company = new Company();
		company.setId(123);
		companyDao.save(company);
		Company dbCompany = companyDao.get(123);
		assertNotNull(dbCompany);
		assertEquals(company.getId(),dbCompany.getId());
		
	}


	@Test
	void testSaveAndThenUpdate() {
		Company company = new Company();
		company.setId(1);
		company.setCompanyName("company");
		companyDao.save(company);
		Company dbCompany = companyDao.get(1);
		dbCompany.setCompanyName("firm");
		companyDao.update(dbCompany);
		Company updatedCompany = companyDao.get(1);
		assertNotNull(updatedCompany);
		assertEquals("firm",updatedCompany.getCompanyName());
	}

	@Test
	void testDelete() {
		Company company= new Company();
		company.setId(1);
		companyDao.save(company);
		Company dbcompany= companyDao.get(1);
		companyDao.delete(dbcompany);
		Company deletedcompany = companyDao.get(1);
		assertEquals(null,deletedcompany);
	}

	@Test
	void testGetAllCompanies() {
		Company company = new Company();
		company.setId(1);
		companyDao.save(company);
		company = new Company();
		company.setId(2);
		companyDao.save(company);
		company = new Company();
		company.setId(3);
		companyDao.save(company);
		assertEquals(3,companyDao.getAllCompanies().size());
		
		
	}

}
