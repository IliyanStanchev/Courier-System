package tu_varna.project.courier_system.dao;

import java.util.List;

public interface BaseDao<T> {

	T get(int id);

	List<T> getAll();

	boolean save(T t);

	void update(T t);

	void delete(T t);
}
