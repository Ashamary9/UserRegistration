package com.register.dto;

import java.io.Serializable;

public class CustomerDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8984211913739936200L;
	
	private String userId;
	private String userName;
	private String email;
	private String password;
	private long phoneNo;
	private String Address;
	
	
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomerDto(String userId, String userName, String email, String password, long phoneNo, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		Address = address;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public long getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
