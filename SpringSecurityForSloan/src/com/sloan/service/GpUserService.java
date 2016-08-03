package com.sloan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sloan.dao.GpUserDao;
import com.sloan.interfaces.services.IGpUserService;
import com.sloan.model.GpUser;
import com.sloan.model.Roles;
import com.sloan.model.SloanUser;




@Service
//@Transactional
public class GpUserService implements UserDetailsService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private GpUserDao gpuser_Dao;
	
	//private String postThatRole = null;
	
	@SuppressWarnings("unused")
	@Override
	//@Transactional
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserDetails springUser = null;
		SloanUser user = gpuser_Dao.findUser(userName);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		if (user == null) {
			System.out.println("IT COIIMES FGHJKLDFGHJKDFGHJKFGHJK");
			throw new UsernameNotFoundException("No User found");
		}
		//System.out.println("User data from GPUSER after data =>"+user.toString());
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    return springUser = new User(user.getUsername(),user.getPassword().toLowerCase(),accountNonExpired,user.isEnabled(),
	            credentialsNonExpired,
	            accountNonLocked,gpuser_Dao.getAuthorities());
	    //return user;
	}
	
	
	
	 
}
