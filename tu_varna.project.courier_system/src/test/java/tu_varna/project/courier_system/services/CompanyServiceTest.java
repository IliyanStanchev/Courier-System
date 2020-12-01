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

import tu_varna.project.courier_system.dao.impl.CompanyDaoImpl;
import tu_varna.project.courier_system.dao.impl.OfficeDaoImpl;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.services.impl.CompanyServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CompanyServiceTest {

	CompanyService companyService;

	@Mock
	CompanyDaoImpl companyDao;

	@Mock
	OfficeDaoImpl officeDao;

	@BeforeEach
	void init() {
		companyService = new CompanyServiceImpl();
		companyService.setDaos(companyDao, officeDao);
	}

	@Test
	void testGetCompanyByID() {

		final Company company = new Company();
		company.setId(1);
		when(companyDao.get(1)).thenReturn(company);
		Company returnedCompany = companyService.getCompanyByID(1);
		assertNotNull(returnedCompany);
		verify(companyDao).get(1);

	}

	@Test
	void testDeleteCompany() {
		Company company = new Company();
		company.setId(1);
		companyService.deleteCompany(company);
		verify(companyDao).delete(company);
	}

	@Test
	void testCreateCompany() {
		final Company company = new Company();
		when(companyDao.save(company)).thenReturn(true);
		assertEquals(true, companyService.createCompany(company));
		verify(companyDao).save(company);
	}

	@Test
	void testCreateOffice() {
		final Office office = new Office();
		when(officeDao.save(office)).thenReturn(true);
		assertEquals(true, companyService.createOffice(office));
		verify(officeDao).save(office);
	}

	@Test
	void testDeleteOffice() {
		Office office = new Office();
		office.setId(1);
		companyService.deleteOffice(office);
		verify(officeDao).delete(office);
	}

	@Test
	void testGetOfficeById() {
		final Office office = new Office();
		office.setId(1);
		when(officeDao.get(1)).thenReturn(office);
		Office returnedOffice = companyService.getOfficeById(1);
		assertNotNull(returnedOffice);
		verify(officeDao).get(1);
	}

}
