package com.citi.cpih.util;

import java.util.ArrayList;
import java.util.List;

import com.citi.cpih.dto.KeyValue;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class ComboUtil {
			
	public static List<KeyValue> getListRoleCombo(){
		List<KeyValue> retVal = new ArrayList<>();
		retVal.add(new KeyValue(String.valueOf(Constants.ADMINISTATOR), "Administrador"));
		retVal.add(new KeyValue(String.valueOf(Constants.NO_ADMINISTATOR), "Lectura"));
		return retVal;
	}
	
}