/**
 * @JSdoc Object
 * @name constants.js
 * 
 * @description Declara las variables que se consideran constantes dentro de la aplicacion.
 */
var Constants = (function() {
    var self = {};
    
    /**
     * @JSdoc Object
     * @name Context
     *
     * @description Constantes de contexto de aplicacion.
     */
    self.Context = {
    	APP_PATH : ''
    };
    
    /**
	 * @JSdoc Object
	 * @name Validacion
	 *
	 * @description Constantes para validaciones.
	 */
	self.Validation = {
		REGEX_RFC : /^[a-zA-Z]{4}(\d{6})((\D|\d){3})?$/,
		REGEX_CORREO : /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/,
		REGEX_CARACTERES_ALFABETICO : '^[a-zA-Z\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC]+$',
		REGEX_CARACTERES_ALFANUMERICO : '^[a-zA-Z0-9\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC]+$',
		REGEX_CARACTERES_ALFANUMERICO_PLUS : '^[a-zA-Z0-9-_.\ \'\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC]+$',
		REGEX_CARACTERES_CORREO : '^[a-zA-Z0-9\u00e1\u00e9\u00ed\u00f3\u00fa\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1\u00FC\u00DC\u0040\u005F\u002E\u002D]+$',
		REGEX_CARACTERES_ALFANUMERICO_CON_ESPACIO: '^[ñÑa-zA-Z0-9_- ]*$',
		REGEX_CARACTERES_ALFANUMERICO_SIN_ESPACIO: '^[ñÑa-zA-Z0-9_-]*$',
		REGEX_CARACTERES_URL: '^[ñÑa-zA-Z0-9/._-]*$'
	};
	
	/**
     * @JSdoc Object
     * @name Code
     *
     * @description Constantes con los codigos regresados por el servidor.
     */
    self.Code = {
    	SUCCESS : 0,
    	ERROR : -1
    };
    
    /**
     * @JSdoc Object
     * @name Message
     *
     * @description Constantes con mensajes.
     */
    self.Message = {
    	SUCCESS : 'Confirmaci\u00F3n',
    	SUCCESS_SAVE : 'El registro se ha guardado correctamente.',
    	SUCCESS_UPDATE : 'El registro se ha actualizado correctamente.',
    	SUCCESS_DELETE : 'El registro se ha eliminado correctamente.',
    	SUCCESS_SAVE_ALL : 'Los cambios se han guardado correctamente.',
    	SUCCESS_UPDATE_ALL : 'Los cambios se han actualizado correctamente.',
    	SELECT_FILE_TEXT : 'Seleccione un archivo para cargar',
    	EXT_FILE_TXT : 'El archivo debe ser extensi\u00F3n .txt.',
    	ERROR_GENERAL : 'Error general en el sistema.',
    	REQUIRED_ALL : 'Es requerido capturar todos los filtros.',
    	REQUIRED_MSISDN : 'Es requerido capturar el msisdn.',
    	REQUIRED_MSISDN_LENGTH : 'El msisdn debe ser de 10 o 12 d\u00EDgitos.',
    	REQUIRED_DATE : 'Es requerido capturar la fecha.',
    	RESULTADOS_TITLE : 'Sin resultados.',
    	SIN_RESULTADOS : 'No se encontraron resultados.',
    	ERROR : 'Error'
    };
    
    /**
     * @JSdoc Object
     * @name Message
     *
     * @description Constantes para filtros de consola.
     */
    self.Filter = {
		FOLIO : 1,
		EMAIL : 2,
		PERSONALIZADO : 3,
		HOY : 4,
		ULTIMAS_24_HORAS : 5,
		AYER : 6,
		ESTA_SEMANA : 7,
		SEMANA_ANTERIOR : 8,
		ESTE_MES : 9,
		MESES_PREVIOS : 10
    };
    
    return self;
}());