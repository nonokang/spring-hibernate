package com.spring.hibernate.dao;

import com.spring.hibernate.entity.People;
import com.spring.hibernate.utils.Page;

public interface PeopleDao extends BaseDao<People> {
	
	public Page<People> queryList(Page<People> page, String searchName);
}
