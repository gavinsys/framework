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
@Table(name="role")
public class Role extends BaseEntity {

	@Column(name="name", length=200)
	private String name;
	
	@Column(name="descn", length=200)
	private String descn;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "USER2ROLE", 
		joinColumns ={
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, 
			inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")     
	})
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "RESOURCE2ROLE", 
		joinColumns ={
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, 
			inverseJoinColumns = {@JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID")     
	})
	private Set<Resource> resources = new HashSet<Resource>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	
}
