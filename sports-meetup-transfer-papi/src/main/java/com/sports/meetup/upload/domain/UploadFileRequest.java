package com.sports.meetup.upload.domain;

import java.util.List;

public class UploadFileRequest {
	
	private List<UploadFile> uploadFiles;

	public List<UploadFile> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<UploadFile> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

}
