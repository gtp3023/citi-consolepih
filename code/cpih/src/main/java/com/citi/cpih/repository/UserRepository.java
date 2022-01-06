package com.citi.cpih.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.cpih.model.User;

/**
 * @author jorge.ruiz citi.com.mx
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}