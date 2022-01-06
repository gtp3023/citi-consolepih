package com.citi.cpih.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class HeaderHandlerResolver implements HandlerResolver {
	
	private String username;
	private String password;
	
	public HeaderHandlerResolver(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		SOAPCaptiveHandler handler = new SOAPCaptiveHandler(username, password);
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(handler);
		return handlerChain;
	}

}
