package com.citi.cpih.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Name;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.cpih.dto.UserSprDTO;
import com.citi.cpih.service.SprService;
import com.citi.cpih.util.Util;

/**
 * @author jruizg citi.com.mx
 */
@Transactional
@Service("sprService")
public class SprServiceImpl implements SprService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private static final String VPN_NODE_ID = "VPNnodeID";
	
	@Autowired 
    private LdapTemplate ldapTemplate;
	
    @SuppressWarnings({ "unchecked" })
    @Override
	public UserSprDTO getProfile(String msisdn) {
    	UserSprDTO userSprDTO = null;
    	try {
    		this.logger.info("{} - Validating user in SPR", msisdn);
            Name dn = LdapNameBuilder.newInstance().add("msisdn", msisdn).add("cn", "BasicProfile").build();
            userSprDTO = (UserSprDTO) this.ldapTemplate.lookup(dn, new SubscriberContextMapper());
            
            if(userSprDTO != null) {
            	UserSprDTO locationInfoUser = this.getLocationInfo(msisdn);
    			
    			if(locationInfoUser != null) {
    				userSprDTO.setVpnNodeId(locationInfoUser.getVpnNodeId());
    			}
    			
    			return userSprDTO;
            }
		} catch (Exception e) {
			this.logger.info("{} - User does not exist in SPR, msisdn is not Telcel", msisdn);
		}
    	
    	return userSprDTO;
    }
	
	@SuppressWarnings("rawtypes")
	private static final class SubscriberContextMapper extends AbstractContextMapper  {
        protected Object doMapFromContext(DirContextOperations ctx) {
        	UserSprDTO userSprDTO = new UserSprDTO();
        	userSprDTO.setSubscription(Util.getSubscriberType(ctx.getStringAttribute("subscriberType")));
            return userSprDTO;
        }
    }
	
	@SuppressWarnings("unchecked")
	private UserSprDTO getLocationInfo(String msisdn) {
    	try {
    		this.logger.info("{} - Validating user in SPR", msisdn);
    		Name dn = LdapNameBuilder.newInstance().add("msisdn", msisdn).add("cn", "LocationInfo").build();

    		Map<String,String> locationValues = (Map<String, String>) this.ldapTemplate.lookup(dn, new LocationInfoContextMapper());
            UserSprDTO userSprDTO = new UserSprDTO();
            userSprDTO.setVpnNodeId(Integer.parseInt(locationValues.get(VPN_NODE_ID)));
        	
        	return userSprDTO;
		} catch (Exception e) {
			this.logger.info("{} - User does not contain location info :: {}", msisdn, e);
		}
    	
    	return null;
    }
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private static final class LocationInfoContextMapper extends AbstractContextMapper  {
		protected Object doMapFromContext(DirContextOperations ctx) {
			Map<String,String> values = new HashMap<>();
			
			try {
				values.put(VPN_NODE_ID, ctx.getStringAttribute(VPN_NODE_ID));
			} catch (NumberFormatException e) {
				e.getMessage();
			} finally {
				LdapUtils.closeContext(ctx);
			}
			return values;
		}
	}
	
}