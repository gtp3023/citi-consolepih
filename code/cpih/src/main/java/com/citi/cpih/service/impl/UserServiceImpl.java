package com.citi.cpih.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citi.cpih.dto.Request;
import com.citi.cpih.model.User;
import com.citi.cpih.repository.UserRepository;
import com.citi.cpih.service.UserService;

/**
 * @author jruizg citi.com.mx
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired 
    private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
    	if (username == null || username.trim().isEmpty()) {
    		throw new UsernameNotFoundException(username);
        }
    	
        User user = this.userRepository.findByUsername(username);
        
        if (user == null) {
        	throw new UsernameNotFoundException(username);
        }

        String role = user.getRole().intValue() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        
        String sessionId = UUID.randomUUID().toString();
        return new Request(user, sessionId);
    }
    
    @Override
	public User findById(Long id) {
		return this.userRepository.findById(id).orElse(null);
	}
	
	@Override
    public List<User> findAll() {
		try {
			return this.userRepository.findAll();
		} catch (Exception e) {
			this.logger.error("Error on load user list => Message: {}", e.getMessage().toString());
			return new ArrayList<>();
		}
	}
	
	@Override
    public boolean save(User user) {
		this.logger.info("Starting saveOrUpdate method => Username: {}", user.getUsername());
		
		try {
			if(user.getEncodedPass() != null && user.getEncodedPass()) {
				user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			}
			
			this.userRepository.save(user);
			return true;
		} catch (Exception e) {
			this.logger.error("Error on saveOrUpdate method => Message: {}", e.getMessage().toString());
			return false;
		}
	}
	
	@Override
    public boolean delete(User user) {
		this.logger.info("Starting delete method => Id: {}", user.getUserId());
		
		try {
			this.userRepository.delete(user);
			return true;
		} catch (Exception e) {
			this.logger.error("Error on delete method => Message: {}", e.getMessage().toString());
			return false;
		}
    }
    
	@Override
    public boolean existUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        
        if(user != null && user.getUserId() != null) {
        	return true;
        }
        
        return false;
    }
	
}