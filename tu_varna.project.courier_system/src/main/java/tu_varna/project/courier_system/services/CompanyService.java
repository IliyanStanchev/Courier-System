package tu_varna.project.courier_system.services;

import java.util.List;

import tu_varna.project.courier_system.dao.CompanyDaoImpl;
import tu_varna.project.courier_system.dao.OfficeDaoImpl;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.OfficeView;

public interface CompanyService {

	List<CompanyView> getAllCompanies();

	Company getCompanyByID(int id);

	void deleteCompany(Company company);

	boolean createCompany(Company company);

	boolean createCompany(int bulstat, String companyName, String manager, String phone, String country, String city,
			String street);

	boolean createOffice(String companyName, String country, String city, String streetN, String agent, String phoneNmb,
			int bulstat);

	boolean createOffice(Office office);

	void deleteOffice(Office office);

	List<OfficeView> getAllOffices();

	Office getOfficeById(int id);

	List<OfficeView> getOfficesList(int bulstat);

	void setDaos(CompanyDaoImpl companyDao, OfficeDaoImpl officeDao);

}