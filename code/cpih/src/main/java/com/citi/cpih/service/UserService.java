package com.citi.cpih.service;

import java.util.List;

import com.citi.cpih.model.User;

/**
 * @author jruizg citi.com.mx
 */
public interface UserService {
		
    User findById(Long id);
	
	List<User> findAll();
	
	boolean save(User user);
	
	boolean delete(User user);
	
	boolean existUsername(String username);
	
}