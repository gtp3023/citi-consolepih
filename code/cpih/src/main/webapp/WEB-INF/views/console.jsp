<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div id="divSearch" class="content">
    <div class="index_header text-center">
        <h2>Hola <sec:authentication property="principal.fullName"/></h2>
        <div class="search">
            <p>Para realizar una búsqueda<br /> ingresa el número de línea deseado.</p>
            <input id="msisdn" type="text" class="form_input" placeholder="Número 10 dígitos" />
            <h2 class="msg-error" id="errorSearch"></h2>
            <button id="btnSearch" type="button" class="button btn_primary">Buscar</button>
        </div>
    </div>
    
    <div id="modalMessage" class="modal fade" aria-labelledby="modalMessageTitle">
		<div class="modal-dialog" role="document">
		    <div class="modal-content info">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                   	<span aria-hidden="true">&times;</span>
	                </button>
	                
	                <div class="modal_title">
	                    <h3><span id="modalMessageTitle"></span></h3>
	                </div>
					
	                <div class="eliminarTitle">
	                    <label id="modalMessageText"></label>
	                </div>
	            </div>
	            <div class="modal-footer">
		            <button data-dismiss="modal" type="button" class="btn btn_primary"><spring:message code="cpih.general.button.accept"/></button>
	            </div>
			</div>
		</div>
	</div>
	
</div>

<div class="overlay">
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
</div>

<div id="divDetail" class="content" style="display: none; padding: 70px 15px 0";/>

<script>	
	$(document).ready(function() {
		$('#msisdn').inputmask('9', {'greedy': false, 'repeat': 10});
		
		$('#btnSearch').click(function(e) {
			self.search();
        });
		
		$('#btnBack').click(function(e) {
			self.clean();
        });
	});
  	
	$('#msisdn').bind("enterKey",function(e){
		search();
	});
	
	$('#msisdn').keyup(function(e){
		if(e.keyCode == 13) {
			search();
		}
	});
	
	function search() {
		$('#errorSearch').text('');
		
    	var msisdn = $('#msisdn').val();
    	
    	if(msisdn == null || msisdn == '') {
	    	$('#errorSearch').text('Es requerido especificar el n\u00FAmero de la l\u00EDnea.');
    		return false;
		}
    	
    	if(msisdn.length != 10) {
    		$('#errorSearch').text('El l n\u00FAmero de la l\u00EDnea debe ser de 10 d\u00EDgitos.');
    		return false;
    	}

		$('.overlay').show();
		
		var parametros = { msisdn : msisdn };
   			
   		$.post('console/validateUser', parametros, validateUserResponse, 'json');
    };
    
    function validateUserResponse(data) {
    	switch(data.retVal) {
		  case 0:
			  	var parametros = { msisdn : $('#msisdn').val() };
		    	genericAjax(null, 'console/search', parametros, loadDetail, 'html');
		    break;
		  case 1:
			  	$('.overlay').hide();
			  	$('#modalMessage').modal('show');
	    		$('#modalMessageText').text('La l\u00EDnea no es Telcel, intenta b\u00FAscando otra l\u00EDnea.');
		    break;
		  case 2:
			  	$('.overlay').hide();
			  	$('#modalMessage').modal('show');
	    		$('#modalMessageText').text('La l\u00EDnea no es Internet en tu casa, intenta b\u00FAscando otra l\u00EDnea.');
			break;
		}
    }
    
    function loadDetail(data) {
    	$('#divSearch').hide();
		$('#divDetail').show();
		$('#divDetail').html(data);
		$('.overlay').hide();
    };
    
    function clean() {
    	$('.overlay').show();
		$('#divDetail').hide();
		$('#msisdn').val('');
		$('#divSearch').show();
		$('.overlay').hide();
    };
</script>