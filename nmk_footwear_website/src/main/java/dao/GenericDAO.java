package dao;

import java.util.List;

public interface GenericDAO <T>{
	public void save(T object);

	public void update(T object);

	public void delete(T object);

	public T findById(Object id);

	public List<T> findAll();
}
