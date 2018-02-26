package com.sports.meetup.user.sapi.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Comment {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Long userId;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdTime;
	
	private String title;
	
	@Column(columnDefinition = "text")
	private String content;
	
	private Boolean replied;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp repliedTime;
	
	

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



	public Timestamp getRepliedTime() {
		return repliedTime;
	}



	public void setRepliedTime(Timestamp repliedTime) {
		this.repliedTime = repliedTime;
	}



	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", createdTime=" + createdTime + ", title=" + title
				+ ", content=" + content + ", replied=" + replied + ", repliedTime=" + repliedTime + "]";
	}

}
