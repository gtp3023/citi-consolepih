package com.citi.cpih.ws;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SOAPCaptiveHandler implements SOAPHandler<SOAPMessageContext> {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private String username;
	private String password;
	
	public SOAPCaptiveHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void close(MessageContext context) {}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {
            try {
                SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
                SOAPHeader header = (envelope.getHeader()!=null)?envelope.getHeader():envelope.addHeader();
 
                SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
 
                SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
                usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
 
                SOAPElement usernameElement = usernameToken.addChildElement("Username", "wsse");
                usernameElement.addTextNode(username);
 
                SOAPElement passwordElement = usernameToken.addChildElement("Password", "wsse");
                passwordElement.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                passwordElement.addTextNode(password);
            } catch (SOAPException e) {
            	logger.error("Error on handleMessage method [SOAPException] => Message: {}", e.getMessage().toString());
            }
        }
        return outboundProperty;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
