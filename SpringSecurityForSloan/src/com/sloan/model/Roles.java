package com.sloan.model;

public enum Roles {
	ROLE_ADMIN , ROLE_USER;
	
	//private String roleName;
	
	/*private  Roles(String brand){
		this.roleName  = brand;
	}*/
	
	public static Roles getUseType(String value) {
		return Roles.valueOf(value);
	}
	
	/*@Override
	public String toString() {
		// TODO Auto-generated method stub
		return roleName;
	}*/
	
}
