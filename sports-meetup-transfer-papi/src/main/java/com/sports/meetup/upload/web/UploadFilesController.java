package com.sports.meetup.upload.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.upload.config.ImgConfig;
import com.sports.meetup.upload.domain.UploadFile;
import com.sports.meetup.upload.domain.UploadFileRequest;
import com.sports.meetup.upload.utils.MkdirUtil;

@RestController
@RequestMapping({"/uploadfiles", "/v1.0/uploadfiles"})
public class UploadFilesController {
	private static final Logger LOG = LoggerFactory.getLogger(UploadFilesController.class);
	
	@Autowired
	private ImgConfig imgConfig;

	//文件上传相关代码
	@PostMapping(value="/imgs")
    public ApiDefaultResponse base64UpLoad( @RequestBody UploadFileRequest uploadFileRequest) throws Exception{
		List<UploadFile> files = uploadFileRequest.getUploadFiles();
		Map<String, String> fileUploadedNames = new HashMap<String, String>();
		if(0==files.size()) {
			LOG.error("上传失败，上传图片数据为空");
			throw new Exception("上传失败，上传图片数据为空");
		}
		//遍历处理上传的Base64 数据
		
		for(int i=0; i<files.size();i++) {
			try{  
				UploadFile uploadFile =  files.get(i);
				String data = uploadFile.getData();
	           if(StringUtils.isEmpty(data)){
	                throw new IOException("上传失败，上传图片数据为空");
	            }
	            // 获取文件名
	    	    String fileName = uploadFile.getName();
	    	    LOG.info("上传的文件名为：" + fileName);
	    	    // 获取文件的后缀名
	    	    String suffixName = fileName.substring(fileName.lastIndexOf("."));
	    	    LOG.info("上传的后缀名为：" + suffixName);
	    	    Long currentTimeMillis = System.currentTimeMillis();
	    	    //上传后的图片名
	            fileName = currentTimeMillis + suffixName; 
	    	    // 文件上传后的路径
	            String uploadedFilePath = imgConfig.getImgPath() + MkdirUtil.getDirname(currentTimeMillis) + fileName;
	            LOG.info("上传后的图片路径为： {}", uploadedFilePath);
	            try{
	            	//因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
	            	byte[] bs = Base64Utils.decodeFromString(data);
	                //使用apache提供的工具类操作流
	            	FileUtils.writeByteArrayToFile(new File(uploadedFilePath), bs);
	            }catch(Exception ee){
	                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
	            }
	            fileUploadedNames.put(fileName, uploadedFilePath);
	            LOG.debug("上传成功");
			}catch (Exception e) {  
	            LOG.debug("上传失败,"+e.getMessage());
	            throw new IOException("上传失败,"+e.getMessage());
	        }  
		}
        return new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), fileUploadedNames);
    }
		
	public ResponseEntity<String> saveImgs(Map<String, String> uploadedFilePaths){
		RestTemplate restTemplate = new RestTemplate();
		String url = "";
		MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<String, String>();
		bodyMap.setAll(uploadedFilePaths);
		ResponseEntity< String> response = restTemplate.postForEntity(url, null, String.class, bodyMap);
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
