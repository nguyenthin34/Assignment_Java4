package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TopLike {
	@Id
	private Video video;
	private Long countlike;
	
	
	public TopLike() {
	}


	public TopLike(Video video, Long countlike) {
		this.video = video;
		this.countlike = countlike;
	}
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	public Long getCountlike() {
		return countlike;
	}
	public void setCountlike(Long countlike) {
		this.countlike = countlike;
	}
	
	
}
