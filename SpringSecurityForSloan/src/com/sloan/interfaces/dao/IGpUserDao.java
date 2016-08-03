package com.sloan.interfaces.dao;

import java.util.Set;

import com.sloan.model.GpAuthority;
import com.sloan.model.GpUser;
import com.sloan.model.SloanUser;



public interface IGpUserDao {

	public abstract SloanUser findUser(String userName);

	/*public abstract Set<GpAuthority> getRolesForGpUser(SloanUser user);

	public abstract SloanUser insert(SloanUser gpUser) throws Exception;
		
	public abstract void Update_docker_json(SloanUser gpUser) throws Exception;
*/
}
