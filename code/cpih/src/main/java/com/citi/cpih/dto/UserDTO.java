package com.citi.cpih.dto;

import java.io.Serializable;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 7990006619400160574L;
	
	private String msisdn;
	private int subscriptionType;
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
    
    public UserDTO() {
    }
    
    public UserDTO(String msisdn) {
    	this.msisdn = msisdn;
    }

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	public int getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(int subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdnTwo() {
		return msisdnTwo;
	}

	public void setMsisdnTwo(String msisdnTwo) {
		this.msisdnTwo = msisdnTwo;
	}

	public String getHasMh3() {
		return hasMh3;
	}

	public void setHasMh3(String hasMh3) {
		this.hasMh3 = hasMh3;
	}

	public String getHasGeolk() {
		return hasGeolk;
	}

	public void setHasGeolk(String hasGeolk) {
		this.hasGeolk = hasGeolk;
	}

	public String getHasOfferId() {
		return hasOfferId;
	}

	public void setHasOfferId(String hasOfferId) {
		this.hasOfferId = hasOfferId;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getHasVpn() {
		return hasVpn;
	}

	public void setHasVpn(String hasVpn) {
		this.hasVpn = hasVpn;
	}

	public String getLastChangeDate() {
		return lastChangeDate;
	}

	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public String getHasRegister() {
		return hasRegister;
	}

	public void setHasRegister(String hasRegister) {
		this.hasRegister = hasRegister;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}