package com.test.spring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.test.spring.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="resource")
public class Resource extends BaseEntity {
	
	@Column(name="icon", length=10)
	private String icon;
	
	@Column(name="name", length=50)
	private String name;
	
	@Column(name="url", length=200)
	private String url;
	
	@Column(name="grade", length=1)
	private String grade;
	
	@Column(name="_order")
	private int _order;
	
	@Column(name="descn", length=200)
	private String descn;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RESOURCE2ROLE", 
		joinColumns ={
			@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "id") }, 
			inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")     
	})
	private Set<Role> roles = new HashSet<Role>();
	
	@Column(name = "parent_id")
	private String parentId;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int get_order() {
		return _order;
	}

	public void set_order(int _order) {
		this._order = _order;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
