package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="mysequence")
	@SequenceGenerator(name="mysequence",sequenceName="USER_SEQ",allocationSize=1)
	@Column(name="USER_ID")
	int userId;
	@Column(name="EMAIL_ID")
	public String emailId;
	public String password;
	@Column(name="NAME")
	public String name;
	@Column(name="CONTACT_NO")
	String contactNo;
	String role;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}		
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
