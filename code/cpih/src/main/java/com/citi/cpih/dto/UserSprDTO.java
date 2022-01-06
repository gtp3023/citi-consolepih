package com.citi.cpih.dto;

import java.io.Serializable;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class UserSprDTO implements Serializable {

	private static final long serialVersionUID = -7810831264604006792L;
	
	private String subscription;
    private Integer vpnNodeId;

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public Integer getVpnNodeId() {
		return vpnNodeId;
	}

	public void setVpnNodeId(Integer vpnNodeId) {
		this.vpnNodeId = vpnNodeId;
	}

}