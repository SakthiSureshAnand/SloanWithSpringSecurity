package com.sloan.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SloanUser implements Serializable,UserDetails{

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String passWord;
	private String oldPassword;
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	private boolean isLoggedIn;
	
	
	private String postThatRole ="";
	
	
	@Column(name = "roles")
	@Enumerated(EnumType.STRING)
	private Roles roles;
	
	
	/*@OneToOne(mappedBy="user", cascade={CascadeType.ALL})
	private Role role;*/
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	private Organisation organisation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", passWord=" + passWord + ", oldPassword=" + oldPassword
				+ ", userType=" + userType + ", isLoggedIn=" + isLoggedIn + "]";
	}*/

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//return null;
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		//for (Roles role :this.roles) {
			//System.out.println("The roles is: " + role.getAuthority());
		  SloanUser getRole = new SloanUser();
		  
		  System.out.println("Hewre comses SLOAN UISERSor not ");
		  if(Roles.ROLE_ADMIN == getRole.getRoles()){
			  postThatRole = "ROLE_ADMIN";
		  }
		  if(Roles.ROLE_USER == getRole.getRoles()){
			  postThatRole = "ROLE_USER";
		  }
		  System.out.println("postThatRoles data =>"+postThatRole);
			list.add(new SimpleGrantedAuthority(postThatRole));
		//}

		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passWord;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + ", passWord=" + passWord + ", oldPassword=" + oldPassword
				+ ", userType=" + userType + ", isLoggedIn=" + isLoggedIn + ", roles=" + roles + ", organisation="
				+ organisation + "]";
	}
	
	

}
