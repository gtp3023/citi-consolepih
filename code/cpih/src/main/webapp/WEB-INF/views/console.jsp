<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="divSearch" class="content">
    <div class="index_header text-center">
        <h2>Hola Administrador</h2>
        <form>
            <p>Para realizar una búsqueda<br /> ingresa el número de línea deseado.</p>
            <input id="msisdn" type="text" class="form_input" placeholder="Número 10 dígitos" />
            <h2 class="msg-error" id="errorSearch"></h2>
            <button id="btnSearch" type="button" class="button btn_primary">Buscar</button>
        </form>
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
    	genericAjax(null, 'console/search', parametros, self.loadDetail, 'html');
    };
    
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