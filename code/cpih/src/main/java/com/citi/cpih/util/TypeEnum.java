package com.citi.cpih.util;

/**
 * @author jorge.ruiz citi.com.mx
 */
public enum TypeEnum {
	
	CONSULTA_PLAN(1),
	COBRO(2),
	RESERVA(3),
	DESCARGA_ICCID(4),
	ACTIVACION(5),
	APROVISIONAMIENTO(6);
    
	private final int id;
	
    private TypeEnum(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
	
}