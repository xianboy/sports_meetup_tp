package com.sports.meetup.field.papi.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sports.meetup.field.papi.domain.UploadFile;
import com.sports.meetup.field.papi.domain.UploadFileRequest;
import com.sports.meetup.field.papi.service.IFileTransferService;
import com.sports.meetup.field.papi.utils.MkdirUtil;

@Service
public class FileTransferService implements IFileTransferService {
	private static final Logger LOG = LoggerFactory.getLogger(FileTransferService.class);
	
	@Autowired
	private MkdirUtil mkdirUtil;
	
	public List<String> base64UpLoad(HttpServletRequest request, UploadFileRequest uploadFileRequest) throws Exception {
		List<UploadFile> files = uploadFileRequest.getUploadFiles();
		List<String> fileUploadedUrls = new ArrayList<String>();
		UploadFile uploadFile = null;
		/*if(0==files.size()) {
			LOG.error("上传失败，上传图片数据为空");
			throw new Exception("上传失败，上传图片数据为空");
		}*/
		//遍历处理上传的Base64 数据
		
		for(int i=0; i<files.size();i++) {
			try{  
				uploadFile = files.get(i);
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
	            fileName = DigestUtils.md5Hex(""+currentTimeMillis) + suffixName; 
	            //构建图片存储目录
	            String dirName = mkdirUtil.getDirName(currentTimeMillis);
	            LOG.info("图片存储目录： {}", dirName);
	            String fileUploadedPath = mkdirUtil.getPicsStorePath(dirName)+fileName;
	            LOG.info("上传后的图片路径为： {}", fileUploadedPath);
	            String fileUploadedUrl = "";
	            try{
	            	//因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
	            	byte[] bs = Base64.decodeBase64(data);
	                //使用apache提供的工具类操作流
	            	FileUtils.writeByteArrayToFile(new File(fileUploadedPath), bs);
	            	fileUploadedUrl =mkdirUtil.rebuildeFileUrl(dirName+fileName, request);
	            	LOG.info("======测试日志=====");
	            	LOG.info("上传后的图片URL为: {}", fileUploadedUrl);
	            }catch(Exception ee){
	                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
	            }
	            fileUploadedUrls.add(fileUploadedUrl);
	            LOG.debug("上传成功");
			}catch (Exception e) {  
	            LOG.debug("上传失败,"+e.getMessage());
	            throw new IOException("上传失败,"+e.getMessage());
	        }  
		}
		return fileUploadedUrls;
	}
}
