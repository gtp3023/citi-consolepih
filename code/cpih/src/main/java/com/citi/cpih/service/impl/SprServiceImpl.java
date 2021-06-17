package com.citi.cpih.service.impl;

import javax.naming.Name;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.cpih.dto.UserDTO;
import com.citi.cpih.service.SprService;
import com.citi.cpih.util.Util;

/**
 * @author jruizg citi.com.mx
 */
@Transactional
@Service("sprService")
public class SprServiceImpl implements SprService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired 
    private LdapTemplate ldapTemplate;
	
    @SuppressWarnings({ "unchecked" })
    @Override
	public UserDTO getProfile(String msisdn) {
    	UserDTO userDTO = null;
    	try {
    		this.logger.info("{} - Validating user in SPR", msisdn);
            Name dn = LdapNameBuilder.newInstance().add("msisdn", msisdn).add("cn", "BasicProfile").build();
            userDTO = (UserDTO) this.ldapTemplate.lookup(dn, new SubscriberContextMapper());
            userDTO.setMsisdn(msisdn);
		} catch (Exception e) {
			this.logger.info("{} - User does not exist in SPR, msisdn is not Telcel", msisdn);
		}
    	
    	return userDTO;
    }
	
	@SuppressWarnings("rawtypes")
	private static final class SubscriberContextMapper extends AbstractContextMapper  {
        protected Object doMapFromContext(DirContextOperations ctx) {
        	UserDTO userDTO = new UserDTO();
        	userDTO.setSubscription(Util.getSubscriberType(ctx.getStringAttribute("subscriberType")));
            return userDTO;
        }
    }
	
}