package tu_varna.project.courier_system.dao;

import java.util.List;

import tu_varna.project.courier_system.entity.Company;

public interface CompanyDao {

	Company get(int id);

	boolean save(Company t);

	void update(Company t);

	void delete(Company t);

	List<Object[]> getAllCompanies();

}