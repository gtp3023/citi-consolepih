package com.citi.cpih.util;

/**
 * @author jorge.ruiz citi.com.mx
 */
public class Constants {
	
	public static final String USER_SESSION = "userSession";
	public static final String SESSION_ID = "sessionId";
	
	public static final String VIEW_WELCOME = "/welcome";
	public static final String VIEW_LOGIN = "/login";
	public static final String VIEW_LOGIN_PAGE = "login";
	public static final String VIEW_LOGOUT_PAGE = "/logout";
	public static final String ANONYMOUS = "anonymousUser";
	public static final String RESPONSE = "response";
		
	public static final String LADA_MX = "52";
	
	public static final int ADMINISTATOR = 1;
	public static final int NO_ADMINISTATOR = 2;
	
	public static final String INCORRECT_CREDENTIALS = "Usuario y/o contrase\u00F1a incorrectos.";
    public static final String ERROR_GENERAL = "Error general.";
	
	public static final String RESPONSE_MSG = "responseMsg";
	public static final String RESPONSE_CODE = "responseCode";
	
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_ERROR = -1;
	
	public static final String START_DATE_COMPLEMENT = " 00:00:00";
	public static final String END_DATE_COMPLEMENT = " 23:59:59";
	
	public static final String UTF_8 = "utf-8";
	
	public static final String POSTPAID = "POSTPAID";
	public static final String POSTPAID_BES = "POSTPAIDGF";
	public static final String MIX = "MIX";
	public static final String PREPAID = "PREPAID";
	
	public static final String POSPAGO = "PospagoLegacy";
	public static final String POSPAGO_BES = "Pospago BES";
	public static final String MIXTO = "Mixto";
	public static final String PREPAGO = "Prepago";
	
	public static final int CODE_POSTPAID = 0;
	public static final int CODE_MIX = 1;
	public static final int CODE_PREPAID = 3;
	
	public static final int CODE_WS_EXCEPTION = -9;
	public static final String DESCRIPTION_WS_EXCEPTION = "Error general en Web Service";
    
    public static final String LADA = "52";
	
	public static final String MSG_SUCCESS = "Success";
	public static final String MSG_ERROR = "Error";
	
	public static final String GWT_METHOD_GET_INTERNET_BALANCE_V4 = "GWT|executeOperation|getInternetBalanceV4";
	public static final String GWT_METHOD_GET_CLOUD_INFO = "GWT|invoke|getCloudInfo";
	
	public static final String ERROR_WS_PAC = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><Mensaje><NumEmp>null</NumEmp><UsrUniversal>null</UsrUniversal><Nombre>null</Nombre><Appat>null</Appat><Apmat>null</Apmat><Region>0</Region><IdPerfil>null</IdPerfil><NombrePerfil>null</NombrePerfil><FvPrepagoPadre>null</FvPrepagoPadre><FvPospagoPadre>null</FvPospagoPadre><FvPrepagoPersonal>null</FvPrepagoPersonal><FvpospagoPersonal>null</FvpospagoPersonal><FvPrepagoReporte>null</FvPrepagoReporte><FvPospagoReporte>null</FvPospagoReporte><Escenario>null</Escenario><Direccion>null</Direccion><Subdireccion>null</Subdireccion><Gerencia>null</Gerencia><Departamento>null</Departamento><DescDepartamento>null</DescDepartamento><Puesto>0</Puesto><Correo>null</Correo><ProblemaId>-1</ProblemaId><ProblemaDesc>TOKEN INVALIDO, PASSWORD INCORRECTO</ProblemaDesc></Mensaje>";
	public static final String SUCCES_WS_PAC = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><Mensaje><NumEmp>54956</NumEmp><UsrUniversal>VVAD6AC</UsrUniversal><Nombre>IVAN EDUARDO</Nombre><Appat>ORTIZ</Appat><Apmat>SANCHEZ</Apmat><Region>0</Region><IdPerfil>ANCGIAIC</IdPerfil><NombrePerfil>ADMINISTRADOR DE SVA INGT IOT MASIVO(ANCGIAIC)</NombrePerfil><FvPrepagoPadre>1000</FvPrepagoPadre><FvPospagoPadre>DUMMY</FvPospagoPadre><FvPrepagoPersonal>null</FvPrepagoPersonal><FvpospagoPersonal>null</FvpospagoPersonal><FvPrepagoReporte>null</FvPrepagoReporte><FvPospagoReporte>null</FvPospagoReporte><Escenario>null</Escenario><Direccion>00500000</Direccion><Subdireccion>00550000</Subdireccion><Gerencia>00550100</Gerencia><Departamento>00550108</Departamento><DescDepartamento>null</DescDepartamento><Puesto>128279</Puesto><Correo>null</Correo><ProblemaId>0</ProblemaId><ProblemaDesc>OPERACION EXITOSA</ProblemaDesc></Mensaje>";
	public static final String ACCESSO_SEGURO = "accesoSeguro";
	public static final String USERNAME_PAC = "pac";
	public static final String PASS_PAC = "pac";
	public static final int CORRECT_PAC = 0;
	public static final int INCORRECT_PAC = -1;
	
	public static final String SI = "Sí";
	public static final String NO = "No";
	public static final String NA = "-";
	
	public static final String VPN_CORRECT = "Correcto";
	public static final String VPN_INCORRECT = "Incorrecto";
	
}