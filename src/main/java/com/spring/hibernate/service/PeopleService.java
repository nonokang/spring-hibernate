package com.spring.hibernate.service;

import com.spring.hibernate.entity.People;
import com.spring.hibernate.utils.Page;

public interface PeopleService {

	public People findById(Integer id);
	
	public Page<People> pageList(Page<People> page, String searchName);
	
	public void save(People bean);
	
	public void update(People bean);
	
	public void opera(String ids, String type);
}
