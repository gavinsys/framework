package com.test.spring.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.test.spring.base.BaseDaoImpl;
import com.test.spring.dao.ResourceDao;
import com.test.spring.entity.Resource;
import com.test.spring.model.Menus;
import com.test.spring.model.Trees;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource> implements ResourceDao {

	@Override
	public List<Menus> findMenusByUser(String username, String grade, String parentId) {
		StringBuffer hql = new StringBuffer("");
		hql.append("select new ").append(Menus.class.getName());
		hql.append("(res.id,res.icon,res.name,res.url)");
		hql.append(" from User u left join u.roles r left join r.resources res");
		hql.append(" where u.username=? and res.grade=? order by res._order");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter(0, username);
		query.setParameter(1, grade);
		return (List<Menus>) query.list();
	}
	
	@Override
	public List<Trees> findTreesByUser(String username, String grade, String parentId) {
		StringBuffer hql = new StringBuffer("");
		hql.append("select new ").append(Trees.class.getName());
		hql.append("(res.id,res.icon,res.name,res.url)");
		hql.append(" from User u left join u.roles r left join r.resources res");
		hql.append(" where u.username=? and res.grade=? and res.parentId=? order by res._order");
		
		Query query = getSession().createQuery(hql.toString());
		query.setParameter(0, username);
		query.setParameter(1, grade);
		query.setParameter(2, parentId);
		return (List<Trees>) query.list();
	}
}
