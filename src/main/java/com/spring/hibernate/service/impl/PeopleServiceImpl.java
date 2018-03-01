package com.spring.hibernate.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.spring.hibernate.dao.PeopleDao;
import com.spring.hibernate.entity.People;
import com.spring.hibernate.service.PeopleService;
import com.spring.hibernate.utils.Page;

@Service(value="peopleService")
@Transactional//表示类中的所有方法都进行事务处理
public class PeopleServiceImpl implements PeopleService {

	@Resource(name="peopleDao")
	public PeopleDao peopleDao;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)//java包的事务注解，此处表示该方法不支持事务处理
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)//spring包的事务注解
	public People findById(Integer id) {
		People p = (People)peopleDao.findById(id);
		return p;
	}

	@Override
	public Page<People> pageList(Page<People> page, String searchName) {
		page = peopleDao.queryList(page, searchName);
		return page;
	}

	@Override
	public void save(People bean) {
		peopleDao.save(bean);
	}

	@Override
	public void update(People bean) {
		peopleDao.update(bean);
	}

	@Override
	public void opera(String ids, String type) {
		if(null != ids && ids.length() != 0){
			String[] _ids = ids.split(",");
			for(String id : _ids){
				if(null == id || "".equals(id)) continue;
				People p = findById(Integer.parseInt(id));
				p.setOpera(type);
				update(p);
			}
		}else{
			throw new NullPointerException(String.format("参数id为空！"));
		}
	}
	
}
