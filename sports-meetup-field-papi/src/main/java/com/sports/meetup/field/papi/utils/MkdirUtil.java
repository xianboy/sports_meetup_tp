package com.sports.meetup.field.papi.utils;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.sports.meetup.field.papi.config.WebConfig;

/**
 * 
 * @author 按照日期生成文件夹名 /2017/08/08/
 */
@PropertySource(value = { "classpath:/application.properties" })
@Component
public class MkdirUtil {
	private static final Logger LOG = LoggerFactory.getLogger(MkdirUtil.class);
	
	@Autowired
	private WebConfig config;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public String getDirName(long currentTimeMillis) {
		String dateTime = dateFormat.format(currentTimeMillis);
		// String[] subDir = dateTime.split("-");
		/*
		 * String nian = dateTime.substring(0, 4); String yue = dateTime.substring(5,
		 * 7); String ri = dateTime.substring(8, 10);
		 */
		// dir.append(dateTime);
		// dir.append(subDir[0]).append("/").append(subDir[1]).append("/").append(subDir[2]).append("/");
		return dateTime + "/";
	}

	public String getPicsStorePath(String dirName) {
		StringBuilder dir = new StringBuilder(config.getFieldPicsStorePath());
		dir.append(dirName);
		return dir.toString();
	}

	public String rebuildeFileUrl(String fileName, HttpServletRequest request) {
		StringBuilder rebuildedUrl = new StringBuilder(config.getAccessType());
		
		/*String localIp = ServiceInfoUtil.getIp();
		if (null==localIp || localIp.equals("") || localIp.equals("null")) {
			System.out.println("服务器ip为:"+ localIp);
			localIp = config.getLocalIp();
		}*/
		String localIp = config.getLocalIp();
		localIp = "112.74.45.247";
		int appPort = ServiceInfoUtil.getPort();
		if (-1 == appPort) {
			appPort = config.getappPort();
		}
		LOG.info("服务器IP为：{}", localIp);
		String url = rebuildedUrl.append(localIp).append(":").append(appPort).append(request.getContextPath())
				.append("/").append(fileName).toString();
		LOG.info("生成的URL: {}", url);
		return url;
	}

}
