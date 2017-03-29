package com.test.spring.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.spring.common.Message;
import com.test.spring.common.Page;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T>  implements IBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public T load(Class<?> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}
	
	public T get(Class<?> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	public Object getObject(Class<?> clazz, Serializable id) {
		return getSession().get(clazz, id);
	}

	public void insert(Object entity) {
		getSession().save(entity);
	}

	public void insert(Collection<?> entities) {
		for(Object entity:entities){
			getSession().save(entity);
		}
	}

	public void save(Object entity) {
		getSession().save(entity);
	}

	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public void update(Object entity) {
		getSession().update(entity);
	}

	public void update(Collection<?> entities) {
		for(Object entity:entities){
			getSession().update(entity);
		}
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	public void delete(Collection<?> entities) {
		for(Object entity:entities){
			getSession().delete(entity);
		}
	}
	
	public int count(String className) {
		Query query = getSession().createQuery("SELECT COUNT(*) FROM "+className);
		return (Integer)query.uniqueResult();
	}

	public List<T> findEntityListByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for(int i=0; params!=null && i<params.length; i++){
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.test.spring.base.IBaseDao#findEntityListByHql(java.lang.String, com.test.spring.common.Page, java.lang.Object[])
	 */
	@Override
	public List<T> findEntityListByHql(String hql,Page page,Object... params){
		Query query = getSession().createQuery(hql);
		for(int i=0; params!=null && i<params.length; i++){
			query.setParameter(i, params[i]);
		}
		if(page!=null){
			query.setFirstResult(page.getBeginIndex());
			query.setMaxResults(page.getEndIndex());
			page.setData(query.list());
			page.setTotalCount(100);
			return (List<T>) page.getData();
		}else{
			return query.list();
		}
	}
	
	@Override
	public List<T> findEntityListByEntityName(String className) {
		Query query = getSession().createQuery("FROM "+ className);
		return query.list();
	}
	
	public String getGenericClassName(){
		Type type = this.getClass().getGenericSuperclass();
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
		if(types != null && types.length > 0){
			return ((Class<?>)types[0]).getSimpleName();
		}
		throw new RuntimeException("The BaseDaoImpl :"+ Message.GENERIC_CLASS_NOT_GET);
	}
	
}
