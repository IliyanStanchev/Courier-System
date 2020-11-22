package tu_varna.project.courier_system.dao;

public interface BaseDao<T> {

	void delete(T t);

	T get(int id);

	boolean save(T t);

	void update(T t);
}
