package com.instacart.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(LogInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		

		long timeStarted = Instant.now().toEpochMilli();
		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + Instant.now());
		request.setAttribute("startTime", timeStarted);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		

		long timeStarted = (Long) request.getAttribute("startTime");

		logger.info("Request URL::" + request.getRequestURL().toString() + ":: Time Taken="
				+ (Instant.now().toEpochMilli() - timeStarted));
	}
}
