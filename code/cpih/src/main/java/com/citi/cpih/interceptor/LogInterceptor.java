package com.citi.cpih.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.citi.cpih.util.Constants;

public class LogInterceptor extends HandlerInterceptorAdapter {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse httpResponse, Object handler) throws Exception {
		
		HttpSession session = request.getSession();

		if(session != null && request.getSession().getAttribute(Constants.SESSION_ID) != null) {
        	String sessionId = (String) request.getSession().getAttribute(Constants.SESSION_ID);
			
			if(sessionId != null) {
				ThreadContext.clearAll();
				ThreadContext.put("sessionId", sessionId);
			}
		}
		
		return true;
	}

}
