package com.sloan.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sloan.dao.SignUpDao;
import com.sloan.model.SloanUser;
import com.sloan.service.SignUpService;

@Service
@Transactional
public class SignUpServiceimpl implements SignUpService {
	
	@Autowired
	private SignUpDao signUpDao;

	@Override
	public SloanUser update(SloanUser SignUp) throws Exception {
		return signUpDao.update(SignUp);
		
	}

	/*@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		signUpDao.delete(id);
		
	}*/

	@Override
	public SloanUser search(long id) throws Exception {
		// TODO Auto-generated method stub
		return signUpDao.search(id);
	
	}

	@Override
	public SloanUser create(SloanUser SignUp) throws Exception {
		// TODO Auto-generated method stub
		return 	signUpDao.create(SignUp);
	}

}
