package com.sloan.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sloan.interfaces.dao.IGpUserDao;
import com.sloan.model.GpAuthority;
import com.sloan.model.GpUser;
import com.sloan.model.Roles;
import com.sloan.model.SloanUser;

@Repository
@Transactional
public class GpUserDao implements IGpUserDao/*, UserDetails */{

	private Log log = LogFactory.getLog(getClass());

	@Value("${findUser.sql}")
	private String findUser;

	@Value("${findRole.sql}")
	private String findRole;

	@PersistenceContext
	private EntityManager entityManager;

	public String setRole = "";

	String postThatRole = null;
	
	SloanUser user = new SloanUser();
	
	@SuppressWarnings("unchecked")
	@Override

	
	
	public SloanUser findUser(String userName) {

		System.out.println("UserName is:" + userName);
		Query result = entityManager.createNativeQuery(findUser).setParameter("userName", userName);
		List<Object[]> user_list = (ArrayList<Object[]>) result.getResultList();
		System.out.println("user list got data form the DB =>" + user_list.toString());
		int i = 0;
		
		List<SloanUser> newarray = new ArrayList<SloanUser>();

		for (Object[] myuser : user_list) {
			System.out.println("userList Data->" + user_list.toString());
			// long id = Long.parseLong(myuser[0].toString());
			// user.setId(id);
			// user.setUsername(myuser[1].toString());
			// user.setPassword(myuser[2].toString());
			user.setEmail(myuser[0].toString());
			user.setPassWord(myuser[1].toString());
			//user.setRoles(myuser[2].toString());
			System.out.println("username:" + myuser[0].toString());
			System.out.println("user id:" + myuser[1].toString());
			System.out.println("ROLES " + myuser[2].toString());
			System.out.println("Check" + myuser[3].toString());
			System.out.println("Roles.ROLE_ADMIN data =>"+Roles.ROLE_ADMIN);
			System.out.println("Roles.ROLE_USER data =>"+Roles.ROLE_USER);
			
			
			if (Roles.ROLE_ADMIN.toString().equals(myuser[2])) {
				System.out.println("-----inn if------admin");
				String admin = "ROLE_ADMIN";
				//auth_man.setAuthority(admin);
				setRole = admin;
				user.setRoles(Roles.ROLE_ADMIN);
			}
			if (Roles.ROLE_USER.toString().equals(myuser[2])) {
				System.out.println("-----in else----");
				String userRole = "ROLE_USER";
				//auth_man.setAuthority(userRole);
				setRole = userRole;
				user.setRoles(Roles.ROLE_USER);
			}
		 System.out.println("dunmny find User"+user.toString());

			newarray.add(user);
		}

		/*System.out.println("Size of user:" + newarray.size());
		if (newarray.size() != 0) {
			System.out.println("User found:" + newarray.get(0).getEmail());

			// Roles authorties = newarray.get(0).getRoles();
			// Set<GpAuthority> roleAuthorities =
			// getRolesForGpUser(newarray.get(0));
			GpAuthority auth_man = new GpAuthority();
			if (Roles.ROLE_ADMIN == user.getRoles()) {
				String admin = "ROLE_ADMIN";
				auth_man.setAuthority(admin);
				setRole = admin;
			}
			if (Roles.ROLE_USER == user.getRoles()) {
				String userRole = "ROLE_USER";
				auth_man.setAuthority(userRole);
				setRole = userRole;
			}

			auth_man.setUsername(user.getEmail());

			// newarray.get(0).setRoles(authorties);
			newarray.get(0).setRoles(Roles.getUseType(setRole));
		} else {
			log.debug("No roles found for this user:" + newarray.get(0).getId());
			// returning set as null
			newarray.get(0).setRoles(null);
		}
		System.out.println("data form dao data  newarray.get(0).toString()" + newarray.get(0).toString());*/
		return newarray.get(0);

	}
	
	
	
		//@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			//return null;
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			//for (Roles role :this.roles) {
				//System.out.println("The roles is: " + role.getAuthority());
			  //SloanUser getRole = new SloanUser();
			  System.out.println("USER IN GET UIONBGUIJJ DATA"+user.toString());
			  System.out.println("Hewre comses or not ");
			  if(Roles.ROLE_ADMIN == user.getRoles()){
				   postThatRole = "ROLE_ADMIN";
			  }
			  if(Roles.ROLE_USER == user.getRoles()){
				   postThatRole = "ROLE_USER";
			  }
			  System.out.println("postThatRoles data =>"+postThatRole);
			  System.out.println("setRoles data =>"+setRole);
				list.add(new SimpleGrantedAuthority(postThatRole));
			//}

			return list;
		}



	/*@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getPassWord();
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	*/
	

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override
	 * 
	 * public Set<GpAuthority> getRolesForGpUser(SloanUser user) {
	 * System.out.println("user Id:" + user.getId()); String userId =
	 * user.getId().toString(); Query result =
	 * entityManager.createNativeQuery(findRole).setParameter( "userId",
	 * userId); List<Object[]> authorities = (ArrayList<Object[]>) result
	 * .getResultList(); List<GpAuthority> hashSetArray = new
	 * ArrayList<GpAuthority>(); GpAuthority auth_man = new GpAuthority(); int i
	 * = 0;
	 * 
	 * System.out.println("object size:" + authorities.size()); for (Object[]
	 * myuser : authorities) { Long userid =
	 * Long.parseLong(myuser[0].toString()); auth_man.setId(userid);
	 * auth_man.setUsername(myuser[1].toString());
	 * auth_man.setAuthority(myuser[2].toString()); hashSetArray.add(auth_man);
	 * }
	 * 
	 * if(Roles.ADMIN == user.getRoles()){ String admin = "ADMIN";
	 * auth_man.setAuthority(admin); setRole = admin; } if(Roles.USER ==
	 * user.getRoles()){ String userRole = "USER";
	 * auth_man.setAuthority(userRole); setRole = userRole; }
	 * 
	 * auth_man.setUsername(user.getEmail());
	 * 
	 * hashSetArray.add(auth_man);
	 * 
	 * 
	 * Set<GpAuthority> auth = new HashSet<GpAuthority>(hashSetArray);
	 * System.out.println("Role:" + auth.toString()); return auth;
	 * 
	 * }
	 * 
	 * @Override public SloanUser insert(SloanUser gpUser) throws Exception { //
	 * TODO Auto-generated method stub return null; }
	 * 
	 * @Override public void Update_docker_json(SloanUser gpUser) throws
	 * Exception { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
