package com.instacart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	int userId;

	
	@Column(name="firstName")
	String firstName;
	
	@Column(name="lastName")
	String lastName;
	
	@Column(name = "userName", unique= true, nullable=false)
	String userName;

	@Column(name = "userPassword")
	String userPassword;

	@Column(name = "userRole")
	String userRole;
	
	@Column(name = "userMobile")
	String userMobile;
	
	@Column(name = "userAddress")
	String userAddress;
	
	
	
	

	public User() {

	}

	public User(String firstName, String lastName, String userName, String userPassword, String userRole, String userMobile, String userAddress) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userAddress=userAddress;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUser_id(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}



	
}
