<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<a id="btnBack" class="back_link"><em class="material-icons">keyboard_backspace</em>Buscar otro usuario</a>
<h3 class="result_title">Información de la línea <span>${userSession.msisdn}</span></h3>

<div class="row result_content">
	
	<c:if test="${userSession.fullName != null && userSession.fullName != ''}">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		    <div class="col-12 col-md-4 col-lg-3 side_info">
		        <div class="result_box">
		            <h4>Datos del usuario</h4>
		
		            <div id="search">
		                <div class="form_result">
		                    <label class="result_lbl">Nombre</label>
		                    <input type="text" class="result_input" value="${userSession.fullName}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Correo</label>
		                    <input type="text" class="result_input" value="${userSession.email}" disabled />
		                </div>
		                <c:if test="${userSession.msisdnTwo != null && userSession.msisdnTwo != ''}">
			                <div class="form_result">
			                    <label class="result_lbl">Número secundario</label>
			                    <input type="text" class="result_input" value="${userSession.msisdnTwo}" disabled />
			                </div>
			           	</c:if>
		            </div>
		        </div>
		    </div>
		</sec:authorize>
	</c:if>
	
    <div class="col-12 col-md-8 col-lg-9">
        <div class="result_box">
        	<c:if test="${userSession.subscription != null && userSession.subscription != ''}">
	            <h4>Productos activos</h4>
	
	            <table class="result_table">
	            	<caption style="display: none;">Detalle usuario</caption>
	                <tr>
	                    <th id="thProduct">Producto</th>
	                    <td>MH3</td>
	                    <td>GEOLK</td>
	                    <td>Offer ID</td>
	                </tr>
	                <tr>
	                    <th id="thStatus">Estatus</th>
	                    <td>${userSession.hasMh3}</td>
	                    <td>${userSession.hasGeolk}</td>
	                    <td>${userSession.hasOfferId}</td>
	                </tr>
	            </table>
			</c:if>
			
            <h4>Información adicional</h4>

            <form>
               	<c:choose>
               		<c:when test="${userSession.subscription != null && userSession.subscription != ''}">
               			<div class="form_result">
        					<label class="result_lbl">Perfil</label>
		                    <input type="text" class="result_input" value="${userSession.subscription}" disabled />
        				</div>
        				<div class="form_result">
		                    <label class="result_lbl">VPN Node ID=5</label>
		                    <input type="text" class="result_input" value="${userSession.hasVpn}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Último cambio de ubicación con costo</label>
		                    <input type="text" class="result_input" value="${userSession.lastChangeDate}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Con registro</label>
		                    <input type="text" class="result_input" value="${userSession.hasRegister}" disabled />
		                </div>
		                <div class="form_result">
		                    <label class="result_lbl">Fecha de registro</label>
		                    <input type="text" class="result_input" value="${userSession.creationDate}" disabled />
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