package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Report2 {
	@Id
	private Serializable userid;
	private String fullname;
	private String email;
	private Date fvDate;
	
	public Report2() {
	}
	public Report2(Serializable userid, String fullname, String email, Date fvDate) {
		this.userid = userid;
		this.fullname = fullname;
		this.email = email;
		this.fvDate = fvDate;
	}
	public Serializable getUserid() {
		return userid;
	}
	public void setUserid(Serializable userid) {
		this.userid = userid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFvDate() {
		return fvDate;
	}
	public void setFvDate(Date fvDate) {
		this.fvDate = fvDate;
	}
	
	
}
