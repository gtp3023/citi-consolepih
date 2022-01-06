package com.citi.cpih.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PIH_USER")
public class UserPih implements Serializable{

	private static final long serialVersionUID = -3695822831594312907L;
	
	public static final Integer STATUS_REGISTER = 1;
	public static final Integer STATUS_NO_LOC = 2;
	public static final Integer STATUS_CHANGE_LOC = 3;
	public static final Integer STATUS_CAC_BLOQUED = 4;
	public static final Integer STATUS_PERMANENT_BLOQUED = 5;
	public static final Integer STATUS_PRE_REGISTER = 6;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PIH_USER")
    @SequenceGenerator(name = "SEQ_PIH_USER", sequenceName = "SEQ_PIH_USER", allocationSize = 1, initialValue = 1)
    private Integer id;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATED_DATE")
    private Date created;

    @Column(name = "UPDATED_DATE")
    private Date updated;
    
    @Column(name = "SUBSCRIPTION_TYPE")
    private int subscriptionType;
    
    @Column(name = "MSISDN_TWO")
    private String msisdnTwo;
    
    @Transient
    private String fullName;

    @Transient
    private Integer vpnNodeId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public int getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(int subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getMsisdnTwo() {
		return msisdnTwo;
	}

	public void setMsisdnTwo(String msisdnTwo) {
		this.msisdnTwo = msisdnTwo;
	}

	public String getFullName() {		
		return ((this.name != null) ? this.name : "") + ((this.lastName != null) ? " " + this.lastName : "");
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getVpnNodeId() {
		return vpnNodeId;
	}

	public void setVpnNodeId(Integer vpnNodeId) {
		this.vpnNodeId = vpnNodeId;
	}
	
}