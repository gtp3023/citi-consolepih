package com.citi.cpih.dto;

import java.io.Serializable;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 5961612398041258740L;
	
	private Integer code;
    private String description;
    
    public ResponseDTO() {}
    
    public ResponseDTO(Integer code, String description) {
    	this.code = code;
    	this.description = description;
    }
    
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
    
}