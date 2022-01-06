<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="table_responsive movil_table">
    <table class="search_results">
    	<caption></caption>
        <thead>
            <tr>
                <th id="thNombre" class="col_160 text-center">Nombre</th>
                <th id="thApellido" class="col_160 text-center">Apellido</th>
                <th id="thUsuario" class="col_160 text-center">Usuario</th>
                <th id="thRol" class="col_160 text-center">Rol</th>
                <th id="thEditar" class="no-sort col_100 text-center"></th>
            </tr>
        </thead>
        <tbody class="header_user">
        	<c:forEach items="${users}" var="user">
        		<tr class="text-center">
	                 <td>${user.name}</td>
	                 <td>${user.lastName}</td>
	                 <td>${user.username}</td>
	                 <td>
	                 	<c:choose>
	                		<c:when test="${user.role == 1}">
		        				Administrador
		        			</c:when>
		        			<c:otherwise>
		        				Lectura
		        			</c:otherwise>
		        		</c:choose>
	                 </td>                     
                   	<td class="text-center">
                        <button class="button btn_primary_line" onClick="updateRegister(${user.userId})"><em class="material-icons" data-backdrop="static" data-keyboard="false">edit</em> Editar</button>
                        <button class="button btn_primary_line" onClick="updatePassword(${user.userId})"><em class="material-icons" data-backdrop="static" data-keyboard="false">lock</em> Cambiar contraseña</button>
                        <button class="button btn_error_line" onClick="openModalDelete(${user.userId})"><em class="material-icons" data-backdrop="static" data-keyboard="false">delete</em> Eliminar</button>
                    </td>
	             </tr>
        	</c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('.search_results').dataTable( {
                "columnDefs": [ {
                      "targets": 'no-sort',
                      "orderable": false,
                } ],
                "bFilter": false,
                "dom": '<"top"fl>rt<"bottom"<"custom_pagination"ip>><"clear">',
                "language": {
                  "info": "_END_ de _TOTAL_ resultados"
                }
        } );
        
        $('.search_results tr th').removeAttr('style');
        
        $('.search_results').wrap('<div class="table_responsive"></div>');
		
    });
</script>