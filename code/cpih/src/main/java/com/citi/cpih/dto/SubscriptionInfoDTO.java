package com.citi.cpih.dto;

import java.io.Serializable;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class SubscriptionInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 3675895601728090362L;
	private String startDate;
    private String expiryDate;
    private String offerID;
    private String productID;
    private String offerType;
    private String offerState;
    private String expiryDateTime;
    private String startDateTime;
    private String offerProviderID;
    
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getOfferID() {
		return offerID;
	}
	
	public void setOfferID(String offerID) {
		this.offerID = offerID;
	}
	
	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getOfferType() {
		return offerType;
	}
	
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	
	public String getOfferState() {
		return offerState;
	}
	
	public void setOfferState(String offerState) {
		this.offerState = offerState;
	}
	
	public String getExpiryDateTime() {
		return expiryDateTime;
	}
	
	public void setExpiryDateTime(String expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}
	
	public String getStartDateTime() {
		return startDateTime;
	}
	
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getOfferProviderID() {
		return offerProviderID;
	}

	public void setOfferProviderID(String offerProviderID) {
		this.offerProviderID = offerProviderID;
	}
    
}