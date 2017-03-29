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
@Table(name="user")
public class User extends BaseEntity {

	@Column(name="username", length=200)
	private String username;
	
	@Column(name="password", length=200)
	private String password;
	
	@Column(name="descn", length=200)
	private String descn;
	
	@Column(name="accountNonExpired")
	private boolean accountNonExpired=true;
	@Column(name="accountNonLocked")
    private boolean accountNonLocked=true;
	@Column(name="credentialsNonExpired")
    private boolean credentialsNonExpired=true;
    @Column(name="enabled")
    private boolean enabled=true;
    
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "USER2ROLE", 
		joinColumns ={
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, 
			inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")     
	})
	private Set<Role> roles = new HashSet<Role>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getDescn() {
		return descn;
	}
	public void setDescn(String descn) {
		this.descn = descn;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
