package tu_varna.project.courier_system.services.impl;

import java.util.ArrayList;
import java.util.List;

import tu_varna.project.courier_system.dao.CompanyDao;
import tu_varna.project.courier_system.dao.OfficeDao;
import tu_varna.project.courier_system.dao.impl.CompanyDaoImpl;
import tu_varna.project.courier_system.dao.impl.OfficeDaoImpl;
import tu_varna.project.courier_system.entity.Address;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Manager;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.services.CompanyService;
import tu_varna.project.courier_system.tabelviewClasses.CompanyView;
import tu_varna.project.courier_system.tabelviewClasses.OfficeView;

public class CompanyServiceImpl implements CompanyService
{

	private CompanyDao companyDao = new CompanyDaoImpl();
	private OfficeDao officeDao = new OfficeDaoImpl();

	@Override
	public List<CompanyView> getAllCompanies()
	{

		List<CompanyView> toReturn = new ArrayList<CompanyView>();
		List<Object[]> list = companyDao.getAllCompanies();
		for (Object[] column : list)
		{
			toReturn.add(new CompanyView((Integer) column[0], (String) column[1]));
		}
		return toReturn;

	}

	@Override
	public Company getCompanyByID(int id)
	{
		return companyDao.get(id);
	}

	@Override
	public void deleteCompany(Company company)
	{
		companyDao.delete(company);

	}

	@Override
	public boolean createCompany(Company company)
	{
		return companyDao.save(company);

	}

	@Override
	public boolean createCompany(int bulstat, String companyName, String manager, String phone, String country,
			String city, String street)
	{
		return companyDao.save(new Company(bulstat, companyName, manager, phone, country, city, street));
	}

	@Override
	public boolean createOffice(String companyName, String country, String city, String streetN, String agent,
			String phoneNmb, int bulstat)
	{

		return officeDao.save(new Office(companyName + " "+ city + " " + streetN, country, city, streetN, agent, phoneNmb,
				companyDao.get(bulstat)));
	}

	@Override
	public void deleteOffice(Office office)
	{

		officeDao.delete(office);

	}

	@Override
	public List<OfficeView> getAllOffices()
	{

		List<OfficeView> toReturn = new ArrayList<OfficeView>();
		List<Object[]> list = officeDao.getAllOffices();
		for (Object[] column : list)
		{
			Manager manager = (Manager) column[1];
			Address address = (Address) column[3];
			Company firm = (Company) column[2];
			toReturn.add(new OfficeView((Integer) column[0], manager.getManagerName(), firm.getCompanyName(),
					address.getCity()));
		}
		return toReturn;

	}

	@Override
	public Office getOfficeById(int id)
	{
		return officeDao.get(id);
	}

	@Override
	public List<OfficeView> getOfficesList(int bulstat)
	{

		List<OfficeView> toReturn = new ArrayList<OfficeView>();
		List<Object[]> list = officeDao.getOfficesByFirm(bulstat);
		for (Object[] column : list)
		{
			toReturn.add(new OfficeView((Integer) column[0], (String) column[1]));
		}
		return toReturn;
	}

	@Override
	public void setDaos(CompanyDao companyDao, OfficeDao officeDao)
	{
		this.companyDao = companyDao;
		this.officeDao = officeDao;

	}

	@Override
	public boolean createOffice(Office office)
	{
		return officeDao.save(office);
	}

}
