package com.citi.cpih.util;

/**
 * @author jorge.ruiz citi.com.mx
 */
public enum FilterEnum {
	
	FOLIO(1),
	EMAIL(2),
	PERSONALIZADO(3),
	HOY(4),
	ULTIMAS_24_HORAS(5),
	AYER(6),
	ESTA_SEMANA(7),
	SEMANA_ANTERIOR(8),
	ESTE_MES(9),
	MESES_PREVIOS(10);
    
	private final int id;

    private FilterEnum(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
	
}