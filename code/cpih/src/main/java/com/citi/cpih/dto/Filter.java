package com.citi.cpih.dto;

import java.io.Serializable;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class Filter implements Serializable {
	
	private static final long serialVersionUID = 5502963715370884L;
	
	private int key;
    private String value1;
    private String value2;
    
    public Filter() {}
    
    public Filter(int key) {
    	this.key = key;
    }
    
    public Filter(int key, String value1) {
    	this.key = key;
    	this.value1 = value1;
    }
    
    public Filter(int key, String value1, String value2) {
    	this.key = key;
    	this.value1 = value1;
    	this.value2 = value2;
    }
    
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
}