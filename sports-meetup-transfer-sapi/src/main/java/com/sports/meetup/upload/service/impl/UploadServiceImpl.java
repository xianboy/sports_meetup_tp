package com.sports.meetup.upload.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.sports.meetup.upload.domain.Image;
import com.sports.meetup.upload.repository.ImageRepository;
import com.sports.meetup.upload.service.UploadService;

public class UploadServiceImpl implements UploadService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public String saveImages(Map<String, String> images) {
		List<Image> imgs = new ArrayList<>();
		for (Entry<String, String> entry : images.entrySet()) {
			//Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
			//entry.getKey() ;entry.getValue(); entry.setValue();
			//map.entrySet()  返回此映射中包含的映射关系的 Set视图。
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			Image img = new Image();
			img.setImgName(entry.getKey());
			img.setImgPath(entry.getValue());
			imgs.add(img);
		}
		
		List<Image> imgList = this.imageRepository.save(imgs);
		return null;
	}
	
}
