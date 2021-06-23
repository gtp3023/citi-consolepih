<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<a id="btnBack" class="back_link"><em class="material-icons">keyboard_backspace</em>Buscar otro usuario</a>
<h3 class="result_title">Información de la línea <span>${userDetail.msisdn}</span></h3>

<div class="row result_content">
	<c:if test="${userDetail.fullName != null && userDetail.fullName != ''}">
	    <div class="col-12 col-md-4 col-lg-3 side_info">
	        <div class="result_box">
	            <h4>Datos del usuario</h4>
	
	            <form>
	                <div class="form_result">
	                    <label class="result_lbl">Nombre</label>
	                    <input type="text" class="result_input" value="${userDetail.fullName}" disabled />
	                </div>
	                <div class="form_result">
	                    <label class="result_lbl">Correo</label>
	                    <input type="text" class="result_input" value="${userDetail.email}" disabled />
	                </div>
	                <c:if test="${userDetail.msisdnTwo != null && userDetail.msisdnTwo != ''}">
		                <div class="form_result">
		                    <label class="result_lbl">Número secundario</label>
		                    <input type="text" class="result_input" value="${userDetail.msisdnTwo}" disabled />
		                </div>
		           	</c:if>
	            </form>
	        </div>
	    </div>
	</c:if>
    <div class="col-12 col-md-8 col-lg-9">
        <div class="result_box">
        	<c:if test="${userDetail.subscription != null && userDetail.subscription != ''}">
	            <h4>Productos activos</h4>
	
	            <table class="result_table">
	            	<caption style="display: none;">Detalle usuario</caption>
	                <tr>
	                    <th id="thProduct">Producto</th>
	                    <td>MH3</td>
	                    <td>GEOLIK</td>
	                    <td>Offer ID</td>
	                </tr>
	                <tr>
	                    <th id="thStatus">Estatus</th>
	                    <td>${userDetail.hasMh3}</td>
	                    <td>${userDetail.hasGeolk}</td>
	                    <td>${userDetail.hasOfferId}</td>
	                </tr>
	            </table>
			</c:if>
			
            <h4>Información adicional</h4>

            <form>
               	<c:choose>
               		<c:when test="${userDetail.subscription != null && userDetail.subscription != ''}">
               			<div class="form_result">
        					<label class="result_lbl">${userDetail.subscription}</label>
        				</div>
        				<div class="form_result">
		                    <label class="result_lbl">VPN Node ID</label>
		                    <input type="text" class="result_input" value="${userDetail.hasVpn}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Último cambio de ubicación con costo</label>
		                    <input type="text" class="result_input" value="${userDetail.lastChangeDate}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Con registro</label>
		                    <input type="text" class="result_input" value="${userDetail.hasRegister}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Fecha de registro</label>
		                    <input type="text" class="result_input" value="${userDetail.creationDate}" disabled />
		                </div>
        			</c:when>
        			<c:otherwise>
        				<div class="form_result">
        					<label class="result_lbl">Línea sin subscripción Telcel</label>
        				</div>
        			</c:otherwise>
        		</c:choose>
            </form>
        </div>
    </div>
</div>

<script>	
	$(document).ready(function() {		
		$('#btnBack').click(function(e) {
			self.clean();
        });
	});
    
    function clean() {
    	$('.overlay').show();
		$('#divDetail').hide();
		$('#msisdn').val('');
		$('#divSearch').show();
		$('.overlay').hide();
    };
</script>