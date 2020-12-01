package tu_varna.project.courier_system.dao;

import java.util.List;

import tu_varna.project.courier_system.entity.Office;

public interface OfficeDao {

	boolean save(Office t);

	Office get(int id);

	void update(Office t);

	void delete(Office t);

	List<Object[]> getAllOffices();

	List<Object[]> getOfficesByFirm(int bulstat);

}