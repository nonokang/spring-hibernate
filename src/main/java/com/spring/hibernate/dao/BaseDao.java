package com.spring.hibernate.dao;

import java.util.List;

import com.spring.hibernate.utils.Page;

public interface BaseDao<T> {

	public List<T> findAll();
	
	public List<T> findAll(String hql, String... str);
	
	public Object save(T t);
	
	public Object update(T t);
	
	public Object findById(Integer id);
	
	public void delete(T t);
	
	public void deleteById(Integer id);
	
	public Page<T> pageList(String hql, Page<T> page, String... str);
	
}
