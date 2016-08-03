package com.sloan.utils;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sloan.model.GpUser;
import com.sloan.model.SloanUser;



public class GpUserUtils {

	public static UserDetails getLoggedUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails user = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			//System.out.println("fdsfd >> " + authentication.getCredentials().toString());
			
			//user.setRoles(authentication.getCredentials().toString());
			
			if (principal instanceof UserDetails) {
				user = (UserDetails) principal;
				
				System.out.println("Loggedin user: " + user.getUsername());
				System.out.println("Loggedin role : " + user.getAuthorities());
			}
		}
		return user;

	}

}
