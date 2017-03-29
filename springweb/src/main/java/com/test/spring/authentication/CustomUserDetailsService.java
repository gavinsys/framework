package com.test.spring.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.test.spring.dao.UserDao;
import com.test.spring.entity.Resource;
import com.test.spring.entity.Role;
import com.test.spring.entity.User;

public class CustomUserDetailsService implements UserDetailsService {
	
    private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = this.userDao.findUserByUserName(username);
        if(user == null){
        	return null;
        }
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);  
        boolean enables = user.isEnabled();  
        boolean accountNonExpired = user.isAccountNonExpired();  
        boolean credentialsNonExpired = user.isCredentialsNonExpired();  
        boolean accountNonLocked = user.isAccountNonLocked(); 
        org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
        return userdetail;
    }

    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {  
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();  
        Set<Role> roles = user.getRoles();  
        for(Role role : roles) {  
            Set<Resource> tempRes = role.getResources();  
            for(Resource res : tempRes) {  
                authSet.add(new SimpleGrantedAuthority(res.getName()));  
            }  
        }  
        return authSet;  
    }  
}
