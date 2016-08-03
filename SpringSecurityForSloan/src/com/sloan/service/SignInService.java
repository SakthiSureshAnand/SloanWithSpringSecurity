package com.sloan.service;


import java.util.List;

import com.sloan.model.KeyValueData;
import com.sloan.model.SloanUser;

public interface SignInService {
	
	
	public Object isValidLogin(SloanUser user) throws Exception;
	public List<KeyValueData> isEnvironment(KeyValueData user);
	
	
}
