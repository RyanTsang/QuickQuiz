package pers.ryan.quickquiz.base;

import java.util.List;

public interface BaseDao<T> {

	void save(T t);
	void update(T t);
	void delete(T t);
	void saveOrUpdate(T t);
	
	T findById(java.io.Serializable id);
	List<T> findAll();
}
