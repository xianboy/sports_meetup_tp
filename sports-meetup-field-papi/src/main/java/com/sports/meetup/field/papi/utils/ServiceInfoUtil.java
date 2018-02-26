package com.sports.meetup.field.papi.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author jiangzeyin
 * @create 2017 02 14 12:44
 */
@Configuration
public class ServiceInfoUtil implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
	private static EmbeddedServletContainerInitializedEvent event;

	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
		ServiceInfoUtil.event = event;
	}

	public static int getPort() {
		Assert.notNull(event);
		int port = event.getEmbeddedServletContainer().getPort();
		Assert.state(port != -1, "端口号获取失败");
		return port;
	}

	public static String getIp() {
		String host = null;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		return host;
	}

}
