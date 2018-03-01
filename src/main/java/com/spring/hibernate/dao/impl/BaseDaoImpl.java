package com.spring.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.hibernate.dao.BaseDao;
import com.spring.hibernate.utils.Page;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    //资料连接：http://blog.csdn.net/lipei1220/article/details/47153181
    
    public abstract Class<T> getEntityClass();

    //采用getCurrentSession()创建的session会绑定到当前线程中，而采用openSession()创建的session则不会。
    //采用getCurrentSession()创建的session在commit或rollback时会自动关闭，而采用openSession()创建的session必须手动关闭。
    private Session getSession() {
//        return this.sessionFactory.openSession();
        return this.sessionFactory.getCurrentSession();
    }
    
    protected String getClassName(){
    	return getEntityClass().getName();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		Criteria cr = getSession().createCriteria(getEntityClass());//标准查询
		list = cr.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String hql, String... str){
		List<T> list = new ArrayList<T>();
		Query q = getSession().createQuery(hql);//hql查询
		for(int i=0; i<str.length; i++){
			q.setParameter(i, str[i]);
		}
		list = q.list();
		return list;
	}

	@Override
	public Object save(T t) {
//		Transaction tx = getSession().beginTransaction();  
		Object o = getSession().merge(t);
//		Object o1 = getSession().save(t);
//		tx.commit();
		return o;
	}

	@Override
	public Object update(T t) {
//		Transaction tx = getSession().beginTransaction();  
		Object o = getSession().merge(t);
//		getSession().update(t);
//		tx.commit();
		return o;
	}

	@Override
	public Object findById(Integer id) {
		Object o = getSession().get(getEntityClass(), id);
//		Object o = getSession().load(getEntityClass(), id);
		flush();
		return o;
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Integer id) {
		T t = (T)findById(id);
		delete(t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> pageList(String hql, Page<T> page, String... str){
		List<T> list = new ArrayList<T>();
		Query q = getSession().createQuery(hql);//hql查询
		if(null != str && str.length != 0){
			for(int i=0; i<str.length; i++){
				q.setParameter(i, str[i]);
			}
		}
		q.setFirstResult(page.getPageNum());
		q.setMaxResults(page.getPageSize());
		list = q.list();
		int totalRow = rows(hql, str);
		
		page.setList(list);
		page.setTotalRow(totalRow);
		return page;
	}
	
	protected int rows(String hql, String... str){
		Query q = getSession().createQuery(hql);//hql查询
		if(null != str && str.length != 0){
			for(int i=0; i<str.length; i++){
				q.setParameter(i, str[i]);
			}
		}
		return q.list().size();
	}
	
	protected void flush(){
		getSession().flush();
		getSession().clear();
	}

}
