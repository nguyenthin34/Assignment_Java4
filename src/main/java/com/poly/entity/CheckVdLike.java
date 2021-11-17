package com.poly.entity;

import java.io.Serializable;

public class CheckVdLike implements Serializable{
	private Video video;
	private boolean check;
	
	public CheckVdLike() {
	}
	public CheckVdLike(Video video, boolean check) {
		super();
		this.video = video;
		this.check = check;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	
}
