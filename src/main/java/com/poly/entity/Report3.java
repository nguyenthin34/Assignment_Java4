package com.poly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report3 {
	@Id
	private String senderName;
	private String senderMail;
	private String receiverMail;
	private Date sentDate;

	public Report3() {
	}

	public Report3(String senderName, String senderMail, String receiverMail, Date sentDate) {
		super();
		this.senderName = senderName;
		this.senderMail = senderMail;
		this.receiverMail = receiverMail;
		this.sentDate = sentDate;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	
}
