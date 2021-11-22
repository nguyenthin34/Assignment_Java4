package com.poly.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report{
	@Id
	private Serializable group;
	private long likes;
	private Date latestDate;
	private Date oldestDate;
	public Serializable getGroup() {
		return group;
	}

	public void setGroup(Serializable group) {
		this.group = group;
	}

	public long getLikes() {
		return likes;
	}

	public void setLikes(long likes) {
		this.likes = likes;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	public Date getOldestDate() {
		return oldestDate;
	}

	public void setOldestDate(Date oldestDate) {
		this.oldestDate = oldestDate;
	}
	
	public Report() {
		
	}

	public Report(Serializable group, long likes, Date latestDate, Date oldestDate) {
		super();
		this.group = group;
		this.likes = likes;
		this.latestDate = latestDate;
		this.oldestDate = oldestDate;
	}
	
}
