package com.sports.meetup.upload.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String imgName;
	
	private String imgPath;
	
	
	
	public Image() {
		super();
	}

	public Image(String imgName, String imgPath) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}
