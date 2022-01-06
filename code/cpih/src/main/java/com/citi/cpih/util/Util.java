package com.citi.cpih.util;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class Util {

	private Util() {
		
	}
	
	public static final String getSubscriberType(String subscriberType) {
    	if(subscriberType != null) {
    		if(subscriberType.equalsIgnoreCase(Constants.POSTPAID)) {
	    		return Constants.POSPAGO;
	    	} else if(subscriberType.equalsIgnoreCase(Constants.MIX)) {
	    		return Constants.MIXTO;
	    	} else if(subscriberType.equalsIgnoreCase(Constants.PREPAID)) {
	    		return Constants.PREPAGO;
	    	} else if(subscriberType.equalsIgnoreCase(Constants.POSTPAID_BES)) {
	    		return Constants.POSPAGO_BES;
	    	} 
    	}
    	
    	return "NA";
    }
	
}