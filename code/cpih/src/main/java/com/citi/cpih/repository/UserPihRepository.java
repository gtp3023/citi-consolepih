package com.citi.cpih.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.cpih.model.UserPih;

/**
 * @author jorge.ruiz citi.com.mx
 */
public interface UserPihRepository extends JpaRepository<UserPih, Long> {
	
	UserPih findByMsisdn(String msisdn);
	
}