package com.sports.meetup.user.papi.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

@Component
public class Comment {
	
	private Integer id;
	
	private Long userId;

	// 创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;
//	private Date createdTime;
	
	private String title;
	
	private String content;
	
	private Boolean replied;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp repliedTime;
//	private Date repliedTime;
	
	

	public Comment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getRepliedTime() {
		return repliedTime;
	}

	public void setRepliedTime(Timestamp repliedTime) {
		this.repliedTime = repliedTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getReplied() {
		return replied;
	}

	public void setReplied(Boolean replied) {
		this.replied = replied;
	}

	
	
}
