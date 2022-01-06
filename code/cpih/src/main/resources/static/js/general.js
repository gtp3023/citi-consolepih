/**
 * @JSdoc Function
 * @name genericAjax
 *
 * @description Metodo generico para llamadas ajax al servidor.
 */
function genericAjax(e, url, params, execute, type) {
	if(e != null) {
		e.preventDefault();
	}
	
	url = Constants.Context.APP_PATH + url;
	
    $.post(url, params, execute, type);
};

/**
 * @JSdoc Function
 * @name genericAjaxJson
 *
 * @description Metodo generico para llamadas ajax al servidor enviando objectos json.
 */
function genericAjaxJson(e, url, params, execute) {
	if(e != null) {
		e.preventDefault();
	}
	
	url = Constants.Context.APP_PATH + url;
    
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        data: JSON.stringify(params),
        success: execute });
};