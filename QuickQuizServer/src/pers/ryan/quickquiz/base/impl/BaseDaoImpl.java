package pers.ryan.quickquiz.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pers.ryan.quickquiz.base.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<?> beanClass;
	public BaseDaoImpl(){
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		beanClass = (Class<?>) paramType.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(beanClass, id);
	}

	@Override
	public List<T> findAll() {
		String hql = "from " + beanClass.getName();
		return this.getHibernateTemplate().find(hql);
	}

}
