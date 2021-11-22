package com.poly.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopShare {
	@Id
	private Video video;
	private Long countshare;
	public TopShare() {
	}
	public TopShare(Video video, Long countshare) {
		this.video = video;
		this.countshare = countshare;
	}
	
	
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Long getCountshare() {
		return countshare;
	}
	public void setCountshare(Long countshare) {
		this.countshare = countshare;
	}
	
	
}
