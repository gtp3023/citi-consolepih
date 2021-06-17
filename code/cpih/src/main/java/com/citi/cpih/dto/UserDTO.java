package com.citi.cpih.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jorge.ruiz citi.com.mx
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 7990006619400160574L;
	
	private String msisdn;
    private String fullName;
    private String email;
    private String msisdnTwo;
    private String hasMh3;
    private String hasGeolk;
    private String hasOfferId;
    private String subscription;
    private String hasVpn;
    private String lastChangeDate;
    private String hasRegister;
    private String creationDate;
    
    public UserDTO(String msisdn) {
    	this.msisdn = msisdn;
    }
    
}