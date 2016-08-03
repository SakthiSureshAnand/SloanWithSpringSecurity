package com.sloan.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sloan.model.SloanUser;

@Repository
@Transactional
public class SignUpDao {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public SloanUser create(SloanUser SignUp) throws Exception {
		entityManager.persist(SignUp);
		entityManager.flush();
		return SignUp;
	}

	@Transactional
	public SloanUser update(SloanUser SignUp) throws Exception {
		entityManager.merge(SignUp);
		entityManager.flush();
		return SignUp;
	}

	/*
	 * @SuppressWarnings("null") public void delete(long id) throws Exception {
	 * // TODO Auto-generated method stub User signUp = null; signUp.setId(8);
	 * entityManager.remove(signUp.getId()); }
	 */

	public SloanUser search(long id) throws Exception {
		ArrayList<SloanUser> list = new ArrayList<SloanUser>();
		SloanUser signUp;
		signUp = (SloanUser) entityManager.find(SloanUser.class, id);
		return signUp;
	}
}
