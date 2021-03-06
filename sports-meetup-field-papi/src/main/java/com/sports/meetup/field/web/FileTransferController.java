package com.sports.meetup.field.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import com.sports.common.constant.ConstantFields;
import com.sports.common.domain.ApiDefaultResponse;
import com.sports.meetup.field.domain.UploadFile;
import com.sports.meetup.field.domain.UploadFileRequest;
import com.sports.meetup.field.utils.MkdirUtil;

@RestController
@RequestMapping(value = {"/sportfields", "/v1.0/sportfields"})
public class FileTransferController {
	private static final Logger LOG = LoggerFactory.getLogger(FileTransferController.class); 
	
	@RequestMapping("/")
	 public ModelAndView index() {
	        return new ModelAndView("index");
	    }
	 
	//文件上传相关代码
	@RequestMapping(value="/uploadBase64",method=RequestMethod.POST)
    public ApiDefaultResponse base64UpLoad( @RequestBody UploadFileRequest uploadFileRequest) throws Exception{
		List<UploadFile> files = uploadFileRequest.getUploadFiles();
		List<String> fileUploadedNames = new ArrayList<String>();
		UploadFile uploadFile = null;
		if(0==files.size()) {
			LOG.error("上传失败，上传图片数据为空");
			throw new Exception("上传失败，上传图片数据为空");
		}
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
	            fileName = currentTimeMillis + suffixName; 
	    	    // 文件上传后的路径
	            String uploadedFilePath = "images"+MkdirUtil.getDirname(currentTimeMillis) + fileName;
	            LOG.info("上传后的图片路径为： {}", uploadedFilePath);
	            try{
	            	//因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
	            	byte[] bs = Base64Utils.decodeFromString(data);
	                //使用apache提供的工具类操作流
	            	FileUtils.writeByteArrayToFile(new File(uploadedFilePath), bs);
	            }catch(Exception ee){
	                throw new Exception("上传失败，写入文件失败，"+ee.getMessage());
	            }
	            fileUploadedNames.add(uploadedFilePath);
	            LOG.debug("上传成功");
			}catch (Exception e) {  
	            LOG.debug("上传失败,"+e.getMessage());
	            throw new IOException("上传失败,"+e.getMessage());
	        }  
		}
        return new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), fileUploadedNames);
    }
	
//	  @RequestMapping(value = "/upload")
//	  @ResponseBody
	  public String upload(MultipartFile file) {
	    if (file.isEmpty()) {
	      return "文件为空";
	    }
	    // 获取文件名
	    String fileName = file.getOriginalFilename();
	    LOG.info("上传的图片名为：" + fileName);
	    // 获取文件的后缀名
	    String suffixName = fileName.substring(fileName.lastIndexOf("."));
	    LOG.info("上传图片的后缀名为：" + suffixName);
	    Long currentTimeMillis = System.currentTimeMillis();
	    // 解决中文问题，liunx下中文路径，图片显示问题
	    fileName = currentTimeMillis + suffixName;
	    // 文件上传后的路径
		String filePath = "images"+MkdirUtil.getDirname(currentTimeMillis);
	    File dest = new File(filePath + fileName);
	    // 检测是否存在目录
	    if (!dest.getParentFile().exists()) {
	      dest.getParentFile().mkdirs();
	    }
	    try {
	      file.transferTo(dest);
	      return fileName;
	    } catch (IllegalStateException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return "上传失败";
	  }

	  
	  //多文件上传
	  @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	  @ResponseBody
	  public ApiDefaultResponse handleFileUpload(HttpServletRequest request) {
	    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
	    MultipartFile file = null;
	    BufferedOutputStream stream = null;
	    List<String> fileNameList = new ArrayList<String>();
	    for (int i = 0; i < files.size(); ++i) {
	      file = files.get(i);
	      if (!file.isEmpty()) {
	        /*try {
	          byte[] bytes = file.getBytes();
	          stream = new BufferedOutputStream(new FileOutputStream(
	              new File(file.getOriginalFilename())));
	          stream.write(bytes);
	          stream.close();
	 
	        } catch (Exception e) {
	          stream = null;
	          return "You failed to upload " + i + " => "
	              + e.getMessage();
	        }*/
	    	  fileNameList.add(upload(file));
	      } 
	    }
	    return new ApiDefaultResponse(ConstantFields.getSuccessCode(), ConstantFields.getSuccessMsg(), fileNameList);
	  }
	  //文件下载相关代码
	  @RequestMapping("/download")
	  public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response){
	    String fileName = "FileUploadTests.java";
	    if (fileName != null) {
	      //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
	      String realPath = request.getServletContext().getRealPath(
	          "//WEB-INF//");
	      File file = new File(realPath, fileName);
	      if (file.exists()) {
	        response.setContentType("application/force-download");// 设置强制下载不打开
	        response.addHeader("Content-Disposition",
	            "attachment;fileName=" + fileName);// 设置文件名
	        byte[] buffer = new byte[1024];
	        FileInputStream fis = null;
	        BufferedInputStream bis = null;
	        try {
	          fis = new FileInputStream(file);
	          bis = new BufferedInputStream(fis);
	          OutputStream os = response.getOutputStream();
	          int i = bis.read(buffer);
	          while (i != -1) {
	            os.write(buffer, 0, i);
	            i = bis.read(buffer);
	          }
	          System.out.println("success");
	        } catch (Exception e) {
	          e.printStackTrace();
	        } finally {
	          if (bis != null) {
	            try {
	              bis.close();
	            } catch (IOException e) {
	              e.printStackTrace();
	            }
	          }
	          if (fis != null) {
	            try {
	              fis.close();
	            } catch (IOException e) {
	              e.printStackTrace();
	            }
	          }
	        }
	      }
	    }
	    return "文件不存在!";
	  }
	
}
