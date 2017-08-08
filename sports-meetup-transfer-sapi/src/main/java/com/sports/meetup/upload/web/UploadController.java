package com.sports.meetup.upload.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sports.meetup.upload.service.UploadService;

@RestController
@RequestMapping(value= {"/upload", "/v1.0/upload"})
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/saveImages")
	public String saveImages(Map<String, String> images) {
		
		return this.uploadService.saveImages(images);
	}
	
	
}
