package com.sloan.service;

import com.sloan.model.SloanUser;

public interface SignUpService {
	
	public SloanUser create(SloanUser SignUp) throws Exception;
	public SloanUser update(SloanUser SignUp) throws Exception;
	//public void delete(long id) throws Exception;
	public SloanUser search (long id) throws Exception;
	

}
