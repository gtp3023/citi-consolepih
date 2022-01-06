package com.citi.cpih.dto;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class KeyValue {

	private String key;
	private String value;
	
	/**
	 * Constructor default
	 */
	public KeyValue() {
		super();
	}
	
	/**
	 * Constructor to values
	 * @param key
	 * @param value
	 */
	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComboItem [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
